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

        robot.open();

        robot.drive(1, 600);

        robot.drive(0, 1000);

        robot.close();

        robot.drive(0, 1000);

        robot.drive(-0.4, 6000);

        robot.drive(0, 1000);

        robot.open();

        robot.drive(0, 1000);

        robot.strafe(-0.75, 1000);
    }

}
