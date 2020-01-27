package org.firstinspires.ftc.teamCode_10516.Autonomous.Navigation;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamCode_10516.Robot;

@Autonomous(name="Navigate Up Left", group = "Navigation")
public class UpLeft extends LinearOpMode {
    // Creating the Robot
    Robot robot = new Robot();

    @Override
    public void runOpMode() {
        // Setting Up Robot Hardware
        robot.init(hardwareMap);
        // Waiting for Start Button
        waitForStart();
        // Drive Forward Into Stone Quarry
        robot.drive(0.25, 2000);
        // Strafe Left To initial Autonomous Position
        robot.strafe(-0.25,2000);

    }
}
