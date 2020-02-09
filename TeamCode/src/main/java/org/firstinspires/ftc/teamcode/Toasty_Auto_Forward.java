package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "Forward")
public class Toasty_Auto_Forward extends LinearOpMode {

    Robot robot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        //robot.hardwareMap(hardwareMap);
        robot.init(hardwareMap);
        robot.Forward(0.25);
        sleep(1200);

    }
}
