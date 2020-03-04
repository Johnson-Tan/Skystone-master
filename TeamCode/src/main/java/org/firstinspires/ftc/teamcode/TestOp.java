package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import java.util.List;
import java.util.ArrayList;


@TeleOp(name = "TestTele")
public class TestOp extends OpMode {

    Robot robot = new Robot();

    @Override
    public void init() {
        gamepad1.setJoystickDeadzone(0.05f);
        gamepad2.setJoystickDeadzone(0.05f);

        robot.hardwareMap(hardwareMap);
    }

    @Override
    public void loop() {
        double y = gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double z = gamepad1.right_stick_x;
        int toggle = 0;

        final List<Double> motorValues = joystickToDriveControl(y, x, z);
        robot.frontLeft.setPower(motorValues.get(0));
        robot.backLeft.setPower(motorValues.get(1));
        robot.frontRight.setPower(motorValues.get(2));
        robot.backRight.setPower(motorValues.get(3));

    }

    public static List<Double> joystickToDriveControl(double y, double x, double z) {
        final List<Double> motorValues = new ArrayList<>();
        if (y > 0 && -0.3 < x && x < 0.3) { // forward region
            motorValues.add((y - z) * 0.75);
            motorValues.add((y - z) * 0.75);
            motorValues.add((y + z) * 0.75);
            motorValues.add((y + z) * 0.75);

        } else if (y < 0 && -0.3 < x && x < 0.3) { // backward region

            motorValues.add((y - z) * 0.75);
            motorValues.add((y - z) * 0.75);
            motorValues.add((y + z) * 0.75);
            motorValues.add((y + z) * 0.75);

        } else if (x > 0 && -0.3 < y && y < 0.3) { // right region

            motorValues.add((-x - z) * 0.75);
            motorValues.add((x - z) * 0.75);
            motorValues.add((x + z) * 0.75);
            motorValues.add((-x + z) * 0.75);

        } else if (x < 0 && -0.3 < y && y < 0.3) { // left region

            motorValues.add((-x - z) * 0.75);
            motorValues.add((x - z) * 0.75);
            motorValues.add((x + z) * 0.75);
            motorValues.add((-x + z) * 0.75);

        } else { //diagonals

            motorValues.add((y - x - z) * 0.75);
            motorValues.add((y + x - z) * 0.75);
            motorValues.add((y + x + z) * 0.75);
            motorValues.add((y - x + z) * 0.75);

        }
//
//        if (gamepad1.left_stick_button) {
//
//            toggle++;
//
//        }
//        else if (gamepad1.right_stick_button) {
//
//            toggle--;
//
//        }
//
//        if (toggle > 0){
//
//            motorValues.add(-x);
//            motorValues.add(x);
//            motorValues.add(x);
//            motorValues.add(-x);
//
//        }
        return motorValues;
    }
}