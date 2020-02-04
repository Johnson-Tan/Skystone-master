package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name= "TestTele2")
public class TestOp2 extends OpMode {
    //Robot robot = new Robot();
    Robot robot = new Robot();
    double milliTime = time*1000;
    @Override
    public void init() {

        robot.init(hardwareMap);


    }

    @Override
    public void loop() {
        if (gamepad1.y){
            resetStartTime();
            while (gamepad1.y) {
                robot.Forward(0.5);
                telemetry.addData("Forward Time",milliTime);
                telemetry.update();
            }
        } else if (gamepad1.a){
            resetStartTime();
            while (gamepad1.a) {
                robot.Forward(-0.5);
                telemetry.addData("Backward Time",milliTime);
                telemetry.update();
            }
        } else {
            robot.Stop();
        }

        if (gamepad1.x){
            resetStartTime();
            while (gamepad1.x) {
                robot.Strafe(-0.5);
                telemetry.addData("Strafe Time",milliTime);
                telemetry.update();
            }
        } else if (gamepad1.b){
            resetStartTime();
            while (gamepad1.b) {
                robot.Strafe(0.5);
                telemetry.addData("Strafe Time",milliTime);
                telemetry.update();
            }
        } else{
            robot.Stop();
        }


    }
}