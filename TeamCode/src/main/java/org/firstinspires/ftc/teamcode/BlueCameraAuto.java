package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import java.util.Locale;

@Autonomous(group="DogeCV")
public class BlueCameraAuto extends LinearOpMode {
    OpenCvCamera phoneCam;
    ActualPipeline visionPipeLine;
    Robot robot = new Robot();


    @Override
    public void runOpMode() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        phoneCam.openCameraDevice();

        visionPipeLine = new ActualPipeline();

        phoneCam.setPipeline(visionPipeLine);

        phoneCam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);

        waitForStart();
        robot.hardwareMap(hardwareMap);

        while (opModeIsActive()) {

            robot.forward(-0.25, 300);
            robot.forward(0,100);

            telemetry.addData("Position", visionPipeLine.getSkystonePosition());
            telemetry.update();
            sleep(1000);
            if (visionPipeLine.getSkystonePosition() == ActualPipeline.SkystonePosition.LEFT_STONE) {
               phoneCam.stopStreaming();
                robot.forward(0.45, 350);
                robot.strafe(-0.45, 725);
                robot.TurnLeft(0.45, 1680);
                robot.strafe(0.45, 950);
                robot.Intake(-0.5, 750);
                robot.forward(0.45, 650);
                robot.Intake(0,100);
                robot.strafe(-0.45, 1050);
                robot.forward(0.45,900);
                robot.Intake(0.45, 500);
                robot.Intake(0, 100);
                robot.forward(-0.45,600);
                stop();
                /*robot.TurnRight(0.45, 950);
                robot.strafe(0.7, 1000);
                robot.TurnRight(0.45, 500);
                robot.TurnRight(0,100);
                robot.forward(0.45, 250);
                robot.Intake(-0.75, 1000);
                robot.Intake(0,100);
                robot.TurnLeft(0.45, 300);
                robot.forward(-0.45,1000);
                robot.strafe(-0.45,1800);
                robot.Intake(0.45,300);
                robot.strafe(0.45,750);
                stop();*/

            }
            else if (visionPipeLine.getSkystonePosition() == ActualPipeline.SkystonePosition.RIGHT_STONE) {
                phoneCam.stopStreaming();
                robot.forward(-0.45, 100);
                robot.strafe(-0.45,1750);
                robot.Intake(-0.5,400);
                robot.forward(0.45,400);
                robot.forward(0,100);
                robot.Intake(0,100);
                robot.strafe(0.45,800);
                robot.forward(-0.45,1800);
                robot.TurnLeft(0.45,600);
                robot.Intake(0.45, 500);
                robot.Intake(0, 100);
                robot.TurnRight(0.45,1125);
                robot.forward(0.45,500);
                stop();

                }
            else if (visionPipeLine.getSkystonePosition() == ActualPipeline.SkystonePosition.CENTER_STONE) {
                phoneCam.stopStreaming();
                robot.forward(-0.45, 350);
                robot.strafe(-0.45,1825);
                robot.Intake(-0.50,400);
                robot.forward(0.45,450);
                robot.forward(0,100);
                robot.Intake(0,100);
                robot.strafe(0.45,750);
                robot.forward(-0.45,1500);
                robot.TurnLeft(0.45,600);
                robot.Intake(0.45, 500);
                robot.Intake(0, 100);
                robot.TurnRight(0.45,1125);
                robot.forward(0.45,350);
                stop();


            }
        }
    }
}