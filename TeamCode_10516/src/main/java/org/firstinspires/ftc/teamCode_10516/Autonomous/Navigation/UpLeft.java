package org.firstinspires.ftc.teamCode_10516.Autonomous.Navigation;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamCode_10516.Robot;

@Disabled
@Autonomous(name = "Navigate Up Left", group = "Navigation")
public class UpLeft extends LinearOpMode {
    // Creating the Robot
    private Robot robot = new Robot();

    @Override
    public void runOpMode() {
        // Setting Up Robot Hardware
        robot.init(hardwareMap);
        // Waiting for Start Button
        waitForStart();
        // Drive Forward To SkyBridge
        robot.drive(0.25, 500);
        // Strafe Left Under SkyBridge
        robot.strafe(-0.25,500);
    }
}
