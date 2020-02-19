package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "Testing Trigonometric Toast")
public class TrigDriveV2 extends OpMode {

    Robot robot = new Robot();

    @Override
    public void init() {
        gamepad1.setJoystickDeadzone(0.1f);
        gamepad2.setJoystickDeadzone(0.1f);

        robot.hardwareMap(hardwareMap);

    }

    @Override
    public void loop() {
        //variables
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double z = Math.sqrt(2) * gamepad1.left_stick_x;

        double Z = Math.abs(z);

        //distance from joystick origin
        double r = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));



        double m ;

        if (gamepad1.left_bumper) {
            m = 3;
        } else if (gamepad1.right_bumper) {
            m = 1/3;
        } else {
            m = 1;
        }


        double a;

        if (x == 0 && y == 0) {
            a = 0;
        } else {
            a = Math.atan2(y,x);
        }

        if (a < 0) {
            a = a + 2 * Math.PI;
        }

        double R = Math.sqrt(2);

        if ((a > Math.PI / 4 && a < 3 * Math.PI / 4) || (a > 5*Math.PI / 4 && a < 7 * Math.PI / 4)){
            R = Math.abs(1 / Math.sin(a));
        } else if (r == 0) {
            R = 0;
        } else if (Math.abs(Math.tan(a)) != 1 ) {
            R = Math.abs(1 / Math.cos(a));
        }

        double b = a - Math.PI / 4;

        double flPow = Math.pow((r * Math.cos(b) + 2*z) / (R + Z),m);
        double frPow = Math.pow((r * Math.sin(b) - 2*z) / (R + Z),m);
        double blPow = Math.pow((r * Math.sin(b) + 2*z) / (R + Z),m);
        double brPow = Math.pow((r * Math.cos(b) - 2*z) / (R + Z),m);

        robot.frontLeft.setPower(flPow);
        robot.frontRight.setPower(frPow);
        robot.backLeft.setPower(blPow);
        robot.backRight.setPower(brPow);



    }
}