package org.firstinspires.ftc.teamcode;


import com.disnodeteam.dogecv.detectors.skystone.SkystoneDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import java.util.Locale;

@Autonomous(group="DogeCV")
public class CameraAuto extends LinearOpMode {
    private OpenCvCamera phoneCam;
    private SkystoneDetector skyStoneDetector;
    Robot robot = new Robot();


    @Override
    public void runOpMode() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = new OpenCvInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        phoneCam.openCameraDevice();

        skyStoneDetector = new SkystoneDetector();
        phoneCam.setPipeline(skyStoneDetector);

        phoneCam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);

        waitForStart();
        robot.hardwareMap(hardwareMap);

        while (opModeIsActive()) {


            telemetry.addData("Stone Position X", skyStoneDetector.getScreenPosition().x);
            telemetry.addData("Stone Position Y", skyStoneDetector.getScreenPosition().y);
            telemetry.addData("Frame Count", phoneCam.getFrameCount());
            telemetry.addData("FPS", String.format(Locale.US, "%.2f", phoneCam.getFps()));
            telemetry.addData("Total frame time ms", phoneCam.getTotalFrameTimeMs());
            telemetry.addData("Pipeline time ms", phoneCam.getPipelineTimeMs());
            telemetry.addData("Overhead time ms", phoneCam.getOverheadTimeMs());
            telemetry.addData("Theoretical max FPS", phoneCam.getCurrentPipelineMaxFps());
            telemetry.update();

            if (skyStoneDetector.getScreenPosition().x > 160) {
                robot.forward(-0.5, 669);
                robot.forward(-0.5, 320);
                robot.strafe(-0.5,1565);
                robot.Intake(-0.70,450);
                robot.forward(0.5,669);
                robot.forward(0,100);
                robot.Intake(0,100);
                robot.strafe(0.5,700);
                robot.forward(0.5,1000);
                robot.forward(-0.5,500);

            }
            else if (skyStoneDetector.getScreenPosition().x < 140 ) {
                robot.forward(-0.5, 320);
                robot.strafe(-0.5,1565);
                robot.Intake(-0.70,450);
                robot.forward(0.5,669);
                robot.forward(0,100);
                robot.Intake(0,100);
                robot.strafe(0.5,700);
                robot.forward(0.5,1000);
                robot.Intake(0.5,500);
                robot.forward(-0.5,500);

                /*robot.strafe(-0.5, 550);
                robot.TurnLeft(0.5, 900);
                robot.forward(0.5, 969);
                robot.TurnLeft(0.25, 175);
                robot.TurnLeft(0,100);
                robot.forward(0.25, 250);
                robot.Intake(0.70, 1000);
                robot.Intake(0,100);
                robot.forward(-0.25,250 );
                robot.TurnRight(0.5,175);
                robot.TurnRight(0,100);
                robot.forward(-0.5, 669);

                robot.strafe(0.5,1500);
                robot.Intake(-0.5, 750);
                robot.outtake(-0.5, 750);
                /*robot.TurnRight(0.5,900);
                robot.forward(-0.5, 50);
                robot.rotateout(0.5, 250);
                robot.virtualFourBar.toggleGripper();
                robot.virtualFourBar.toggleGripper();
            */
                }
            else {
                robot.forward(-0.5, 800);
                robot.forward(-0.5, 320);
                robot.strafe(-0.5,1565);
                robot.Intake(-0.70,450);
                robot.forward(0.5,669);
                robot.forward(0,100);
                robot.Intake(0,100);
                robot.strafe(0.5,700);
                robot.forward(0.5,1000);
                robot.Intake(0.5,500);
                robot.forward(-0.5,500);


            }
            robot.forward(0,1000);
        }
    }
}