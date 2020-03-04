package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "Left")
public class Toasty_Auto_Left extends LinearOpMode {

    Robot robot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        robot.init(hardwareMap);
        robot.strafe(-0.5,700);
        robot.Stop();
        sleep(300);
        robot.forward(-0.5, 500);

    }
}
