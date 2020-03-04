package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "SlideTest")
@Config
public class SlideTest extends LinearOpMode {

    Robot robot = new Robot();

    public static double i , d, f = 0;
    public static double p = 1;
    public static double leftActual, rightActual, Desired;

    public static ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.hardwareMap(hardwareMap);
        robot.left.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        robot.right.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        FtcDashboard Dash = FtcDashboard.getInstance();
        telemetry = Dash.getTelemetry();

        waitForStart();
        timer.reset();
        timer.startTime();
        telemetry.addData("Milliseconds", timer.milliseconds());
        telemetry.update();
        while(timer.milliseconds() <= 1000) {

            robot.right.setVelocity(600);
            robot.left.setVelocity(600);
            robot.left.setVelocityPIDFCoefficients(p, i, d, f);
            robot.right.setVelocityPIDFCoefficients(p, i, d, f);
            leftActual = robot.left.getVelocity();
            rightActual = robot.right.getVelocity();
            Desired = 600;
            telemetry.addData("Desired", Desired);
            telemetry.addData("LeftVelocity", leftActual);
            telemetry.addData("RightVelocity", rightActual);
            telemetry.addData("Milliseconds", timer.milliseconds());
            telemetry.update();
        }

        robot.left.setVelocity(0);
        robot.right.setVelocity(0);
        sleep(3000);
        stop();
    }
}
