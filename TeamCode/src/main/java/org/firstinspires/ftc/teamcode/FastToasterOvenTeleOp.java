package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name= "HypeToast")
@Config
public class FastToasterOvenTeleOp extends OpMode {

    Robot robot = new Robot();

    @Override
    public void init() {
        gamepad1.setJoystickDeadzone(0.1f);
        gamepad2.setJoystickDeadzone(0.1f);

        robot.hardwareMap(hardwareMap);

        robot.right.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        robot.left.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        robot.frontRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        robot.frontLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        robot.backRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        robot.backLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        robot.intakeR.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        robot.intakeL.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        robot.left.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.right.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        robot.right.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        robot.right.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        robot.gripper.setPosition(1);

    }

    @Override
    public void loop() {

        telemetry.addData("left position",robot.left.getCurrentPosition());
        telemetry.addData("right position",robot.right.getCurrentPosition());
        telemetry.addData("FrontLeft Position", robot.frontLeft.getCurrentPosition());
        telemetry.addData("BackLeft Position", robot.backLeft.getCurrentPosition());
        telemetry.addData("FrontRight Position", robot.frontRight.getCurrentPosition());
        telemetry.addData("BackRight Position", robot.backRight.getCurrentPosition());
        telemetry.addData("IntakeL Position", robot.intakeL.getPower());
        telemetry.addData("IntakeR Position", robot.intakeR.getPower());

        telemetry.update();

        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double z = gamepad1.right_stick_x;
        double m; // modifier
        boolean fastTriggered = false;
        boolean slowTriggered = false;

        if(gamepad1.a){

            m = 3;

        } else if (gamepad1.y){

            m = 4/3;

        }

        else{

            m = 1;

        }


        if (y > 0 && -0.3 < x && x < 0.3) { // forward region
            robot.frontLeft.setPower((y - z) / m);
            robot.backLeft.setPower((y - z) / m);
            robot.frontRight.setPower((y + z) / m);
            robot.backRight.setPower((y + z) / m);
        } else if (y < 0 && -0.3 < x && x < 0.3) { // backward region
            robot.frontLeft.setPower((y - z) / m);
            robot.backLeft.setPower((y - z) / m);
            robot.frontRight.setPower((y + z) / m);
            robot.backRight.setPower((y + z) / m);
        } else if (x > 0 && -0.3 < y && y < 0.3) {
            // right region
            robot.frontLeft.setPower((-x - z) / m);
            robot.backLeft.setPower((x - z) / m);
            robot.frontRight.setPower((x + z) / m);
            robot.backRight.setPower((-x + z) / m);
        } else if (x < 0 && -0.3 < y && y < 0.3) {
            // left position
            robot.frontLeft.setPower((-x - z) / m);
            robot.backLeft.setPower((x - z) / m);
            robot.frontRight.setPower((x + z) / m);
            robot.backRight.setPower((-x + z) / m);
        } else { // diagonals
            robot.frontLeft.setPower((y - x - z) / m);
            robot.backLeft.setPower((y + x - z) / m);
            robot.frontRight.setPower((y + x + z) / m);
            robot.backRight.setPower((y - x + z) / m);
        }

        if (gamepad2.right_bumper){ //intake
            robot.intakeL.setPower(0.75);
            robot.intakeR.setPower(0.75);
        }else if (gamepad2.left_bumper){ //outtake
            robot.intakeL.setPower(-0.5);
            robot.intakeR.setPower(-0.5);
        }else {
            robot.intakeL.setPower(0);
            robot.intakeR.setPower(0);
        }

        if (gamepad1.left_bumper) {
            while (robot.grabberL.getPosition() != 0) {
                robot.grabberL.setPosition(0);
                robot.grabberR.setPosition(0);
            }
        } else if (gamepad1.right_bumper) {
            robot.grabberL.setPosition(1);
            robot.grabberR.setPosition(1);
        }
/*
     slide diameter = 1.3 in
     full rotation = 145.6 ticks
     max stack height = 27 in
     ticks til max height = 962
     circumference = 4.08in
*/

        if (gamepad2.right_trigger > 0.05 || gamepad2.left_trigger > 0.05) {


            if (robot.left.getCurrentPosition() < 2000 || robot.right.getCurrentPosition() < 2000) {
                robot.left.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
                robot.right.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
            }else {
                robot.left.setPower(0);
                robot.right.setPower(0);
            }

        }

        if (gamepad2.x) {
            robot.rotate(0.75);
        } else if (gamepad2.b) {
            robot.rotate(-0.75);
        } else {
            robot.rotate(0);
        }

        if (gamepad2.y) {
            robot.toggleGripper();
            robot.linearOpMode.sleep(250);
        }

    }
}

