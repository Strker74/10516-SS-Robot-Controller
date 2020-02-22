package org.firstinspires.ftc.teamCode_10516.Autonomous.Delivery;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamCode_10516.Robot;

@Autonomous
public class Del_Test_2 extends LinearOpMode {
    Robot robot = new Robot();
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.driveMotors = new DcMotor[]{robot.frontLeft, robot.frontRight, robot.backLeft, robot.backRight};
        robot.encoderDrive(0.5,1000);
        robot.drive(0, 500);
        robot.close();
        robot.drive(0, 500);
        robot.encoderDrive(-0.5, -1000);
        robot.drive(0, 500);
        robot.rotate(-1, 5000);
        //robot.rotateGyro(-0.5, -90);
        //robot.drive(0, 500);
    }
}
