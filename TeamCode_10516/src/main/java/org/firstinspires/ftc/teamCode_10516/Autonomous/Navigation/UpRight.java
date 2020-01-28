package org.firstinspires.ftc.teamCode_10516.Autonomous.Navigation;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamCode_10516.Robot;

@Autonomous(name = "Navigate Up Right", group = "Navigation")
public class UpRight extends LinearOpMode {
    // Creating the Robot
    Robot robot = new Robot();

    @Override
    public void runOpMode() {
        // Setting Up Robot Hardware
        robot.init(hardwareMap);
        // Waiting for Start Button
        waitForStart();
        // Drive Forward To SkyBridge
        robot.drive(1, 500);
        // Strafe Right Under SkyBridge
        robot.strafe(1,500);

    }

}