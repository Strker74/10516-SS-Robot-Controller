package org.firstinspires.ftc.teamCode_10516.Autonomous.Repostioning;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamCode_10516.Robot;

@Autonomous(name = "Repositioning Test", group = "Test")
public class Rep_Test extends LinearOpMode {
    // Creating the Robot Object
    private Robot robot = new Robot();

    @Override
    public void runOpMode() {
        // Setting Up Robot Hardware
        robot.init(hardwareMap);
        // Waiting for Start Button
        waitForStart();
        // Approaches Foundation
        robot.drive(0.5, 1500);
        // Closes the jacket (Gripping the Foundation)
        robot.open();
        robot.drive(0, 2000);
        // Pulls foundation backwards
        robot.drive(-0.3, 600);
        // Release foundation
        robot.close();
        // Strafes out the side of the foundation
        robot.strafe(-0.75, 500);
    }

}
