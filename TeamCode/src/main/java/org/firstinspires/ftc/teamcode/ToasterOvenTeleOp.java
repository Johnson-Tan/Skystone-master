package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name= "GhostToast")
public class ToasterOvenTeleOp extends OpMode {

    Robot robot = new Robot();


    @Override
    public void init() {
        gamepad1.setJoystickDeadzone(0.1f);
        gamepad2.setJoystickDeadzone(0.1f);

        robot.hardwareMap(hardwareMap);

        telemetry.addData("Joystick X:",gamepad1.left_stick_x);
        telemetry.addData("Joystick Y:",gamepad1.left_stick_y);
        telemetry.addData("FrontLeft Position", robot.frontLeft.getCurrentPosition());
        telemetry.addData("BackLeft Position", robot.backLeft.getCurrentPosition());
        telemetry.addData("FrontRight Position", robot.frontRight.getCurrentPosition());
        telemetry.addData("BackRight Position", robot.backRight.getCurrentPosition());
        telemetry.addData("IntakeL Position", robot.intakeL.getPower());
        telemetry.addData("IntakeR Position", robot.intakeR.getPower());
    }

    @Override
    public void loop() {
        //elevator.update();
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double z = gamepad1.right_stick_x;
        double m; // modifier

        if (gamepad1.a) {
            m = 3;
        } else if (gamepad1.y) {
            m = 1;
        } else {
            m = 1.2;
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
            robot.grabberL.setPosition(0);
            robot.grabberR.setPosition(0);
        } else if (gamepad1.right_bumper) {
            robot.grabberL.setPosition(1);
            robot.grabberR.setPosition(1);
        }
        // robot.elevator.setVelocity((double)(gamepad2.left_trigger - gamepad2.right_trigger));

        if (gamepad2.x) {
            robot.rotate(0.75);
        } else if (gamepad2.b) {
            robot.rotate(-0.75);
        } else {
            robot.rotate(0);
        }

        if (gamepad2.y) {
            robot.toggleGripper();
            telemetry.addLine("you just got toggled");
            telemetry.addData("Servo Position",robot.gripper.getPosition());
        }

    }
}

