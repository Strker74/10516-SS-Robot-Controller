package org.firstinspires.ftc.teamCode_10516.Autonomous.Repostioning;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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
        // Open the "Jacket"
        //robot.open();
        // Drive Forward to Foundation
        //robot.drive(1, 600);
        // Close the "Jacket" grabbing the foundation
        //robot.close();
        // Drive backwards pulling the foundation
        robot.drive(-0.4, 6000);
        // Open the "Jacket" releasing the foundation
        //robot.open();
        // Strafe out the foundation opening to park
        //robot.strafe(-0.75, 1000);
    }

}
