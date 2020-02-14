package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Robot {


    DcMotorEx frontLeft;
    DcMotorEx backLeft;
    DcMotorEx frontRight;
    DcMotorEx backRight;

    DcMotorEx left;
    DcMotorEx right;

    CRServo srvLeft;
    CRServo srvRight;
    Servo gripper;
    private boolean srvLeftReversed = false;
    private boolean srvRightReversed = true;


    LinearOpMode linearOpMode = new LinearOpMode() {
        @Override
        public void runOpMode() throws InterruptedException {

        }
    };

    DcMotorEx intakeL;
    DcMotorEx intakeR;

    Servo grabberL;
    Servo grabberR;

    double dbPos = 0;

    boolean fastMode = true;



    public void init(HardwareMap hardwareMap) {
        hardwareMap(hardwareMap);

    }

    public void hardwareMap(HardwareMap hardwareMap) {
        srvLeft = hardwareMap.get(CRServo.class,"vFourBarL");
        srvRight = hardwareMap.get(CRServo.class,"vFourBarR");
        srvRight.setDirection(CRServo.Direction.REVERSE);

        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        frontLeft.setDirection(DcMotorEx.Direction.REVERSE);
        backLeft.setDirection(DcMotorEx.Direction.REVERSE);

        intakeL = hardwareMap.get(DcMotorEx.class, "intakeL");
        intakeR = hardwareMap.get(DcMotorEx.class, "intakeR");

        intakeL.setDirection(DcMotorEx.Direction.REVERSE);


        grabberL = hardwareMap.get(Servo.class, "grabberL");
        grabberR = hardwareMap.get(Servo.class, "grabberR");
        grabberL.setPosition(1-dbPos);
        grabberR.setPosition(dbPos);

        gripper = hardwareMap.get(Servo.class,"gripper");

        left = hardwareMap.get(DcMotorEx.class, "left");
        right = hardwareMap.get(DcMotorEx.class, "right");

        double dbPos = 0;

    }

    public void motorsPower(double power){
        frontLeft.setPower(power);
        backLeft.setPower(power);
    }

    public void rotate(double power){
        srvLeft.setPower(power);
        srvRight.setPower(power);
    }

    public void openGripper(){
        gripper.setPosition(0);
    }

    public void closeGripper(){

        gripper.setPosition(1);
    }

    public void toggleGripper(){
        if (gripper.getPosition() == 1) {
            openGripper();
        } else {
            closeGripper();
        }
    }

    public void encodersForward(double inches, double power){
        frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setTargetPosition(inchesToTick(inches));
        frontRight.setTargetPosition(inchesToTick(inches));
        backLeft.setTargetPosition(inchesToTick(inches));
        backRight.setTargetPosition(inchesToTick(inches));

        frontLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        Forward(power);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()){
        }

        Stop();

    }

    public void Forward (double Power){
        frontLeft.setPower(Power);
        frontRight.setPower(Power);
        backLeft.setPower(Power);
        backRight.setPower(Power);

    }
    public void forward (double Power, int milliseconds){
        Forward(Power);

        linearOpMode.sleep(milliseconds);
    }
    public void strafe(double Power, int milliseconds) {
        Strafe(Power);

        linearOpMode.sleep(milliseconds);
    }

    public void Strafe (double Power){
        frontLeft.setPower(-Power);
        frontRight.setPower(Power);
        backLeft.setPower(Power);
        backRight.setPower(-Power);
    }

    public void Intake(double Power, int milliseconds) {
        intake(Power);

        linearOpMode.sleep(milliseconds);

    }

    public void intake (double Power) {
        intakeR.setPower(-Power);
        intakeL.setPower(-Power);

    }

    public void outtake (double Power, int milliseconds) {
        intakeL.setPower(Power);
        intakeR.setPower(Power);

        linearOpMode.sleep(milliseconds);
    }

    public void TurnLeft (double Power, int milliseconds) {
        frontLeft.setPower(-Power);
        frontRight.setPower(Power);
        backLeft.setPower(-Power);
        backRight.setPower(Power);

        linearOpMode.sleep(milliseconds);
    }

    public void TurnRight (double Power, int milliseconds) {
        frontLeft.setPower(Power);
        frontRight.setPower(-Power);
        backLeft.setPower(Power);
        backRight.setPower(-Power);

        linearOpMode.sleep(milliseconds);
    }

    public void rotateout (double Power, int milliseconds) {
        rotate(Power);

        linearOpMode.sleep(milliseconds);
    }

    public void rotatein (double Power, int milliseconds) {
        rotate(-Power);

        linearOpMode.sleep(milliseconds);
    }

    public void Stop (){
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        srvLeft.setPower(0);
        srvRight.setPower(0);
        intakeL.setPower(0);
        intakeR.setPower(0);
    }


    public int inchesToTick (double inches){
        double circumference = Math.PI*3.937;
        double fullRotation = 537.6;
        return (int) (inches * (fullRotation / circumference));
    }

    public void setGrabber() {
        if (dbPos == 0) {
            dbPos = 1;
        } else if (dbPos == 1) {
         dbPos = 0;
        }
        grabberL.setPosition(dbPos);
        grabberR.setPosition(1 - dbPos);
    }

    public static double roundHun(double input){
        double output = Math.round(input*100);
        return output/100;
    }

    public double getGrabber() {
        return dbPos;
    }

    /*
    public void Lift (double Power, int milliseconds){
        elevator.setVelocity(Power);
        linearOpMode.sleep(milliseconds);
    }*/
}
