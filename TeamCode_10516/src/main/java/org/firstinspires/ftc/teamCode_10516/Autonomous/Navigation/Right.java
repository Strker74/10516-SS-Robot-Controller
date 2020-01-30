package org.firstinspires.ftc.teamCode_10516.Autonomous.Navigation;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamCode_10516.Robot;

@Autonomous(name = "Navigate Right", group = "Navigation")
public class Right extends LinearOpMode {
    // Creating the Robot Object
    private Robot robot = new Robot();

    @Override
    public void runOpMode() {
        // Setting Up Robot Hardware
        robot.init(hardwareMap);
        // Waiting for Start Button
        waitForStart();
        // Strafe Right Under SkyBridge
        robot.strafe(1,500);
    }

}
