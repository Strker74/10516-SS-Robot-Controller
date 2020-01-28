package org.firstinspires.ftc.teamCode_10516.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamCode_10516.Robot;

@Disabled
@Autonomous(name = "Base Autonomous Code", group = "Autonomous")
public class AutoBase extends LinearOpMode {
    // Creating the Robot Object
    Robot robot = new Robot();

    @Override
    public void runOpMode() {
        // Setting Up Robot Hardware
        robot.init(hardwareMap);
        // Waiting for Start Button
        waitForStart();
        // Command Line (What the robot does)
    }

}
