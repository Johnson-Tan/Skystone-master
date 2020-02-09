package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import java.util.Locale;

@Autonomous(group="DogeCV")
public class RedCameraAuto extends LinearOpMode {
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

            robot.forward(-0.25, 650);
            robot.forward(0,100);

            telemetry.addData("Position", visionPipeLine.getSkystonePosition());
            telemetry.update();
            sleep(1000);
            if (visionPipeLine.getSkystonePosition() == ActualPipeline.SkystonePosition.LEFT_STONE) {
               phoneCam.stopStreaming();
                robot.forward(-0.5, 689);
                robot.strafe(-0.5,1650);
                robot.Intake(-0.70,450);
                robot.forward(0.5,600);
                robot.forward(0,100);
                robot.Intake(0,100);
                robot.strafe(0.5,700);
                robot.forward(0.5,1400);
                robot.Intake(0.5, 500);
                robot.Intake(0, 100);
                robot.forward(-0.5,300);
                stop();

            }
            else if (visionPipeLine.getSkystonePosition() == ActualPipeline.SkystonePosition.RIGHT_STONE) {
                phoneCam.stopStreaming();
                //robot.forward(-0.5, 100);
                robot.strafe(-0.5,1650);
                robot.Intake(-0.70,450);
                robot.forward(0.5,669);
                robot.forward(0,100);
                robot.Intake(0,100);
                robot.strafe(0.5,700);
                robot.forward(0.5,1100);
                robot.Intake(0.5,500);
                robot.forward(-0.5,510);
                stop();
                /*
                robot.strafe(-0.5,700);
                robot.Intake(-0.70,450);
                robot.forward(0.5,669);
                robot.forward(0,100);
                robot.Intake(0,100);
                robot.strafe(0.5,700);
                robot.forward(0.5,1169);
                robot.Intake(0.5,500);
                robot.forward(-0.5,500);*/


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
            else if (visionPipeLine.getSkystonePosition() == ActualPipeline.SkystonePosition.CENTER_STONE) {
                phoneCam.stopStreaming();
                robot.forward(-0.5, 350);
                robot.strafe(-0.5,1700);
                robot.Intake(-0.70,450);
                robot.forward(0.5,500);
                robot.forward(0,100);
                robot.Intake(0,100);
                robot.strafe(0.5,750);
                robot.forward(0.5,1300);
                robot.Intake(0.5,500);
                robot.forward(-0.5,525);

                stop();


            }
        }
    }
}