package org.firstinspires.ftc.teamCode_10516.Autonomous.Delivery;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamCode_10516.Robot;

@Autonomous(name = "Delivery Test", group = "Test")
public class Test_Del extends LinearOpMode {
    // Creating the Robot
    Robot robot = new Robot();

    @Override
    public void runOpMode() {
        // Setting Up Robot Hardware
        robot.init(hardwareMap);
        // Waiting for Start Button
        waitForStart();
        // Open Jacket
        robot.open();
        // Drive Forward Into Stone Quarry
        robot.drive(0.5, 2000);
        // Close Jacket
        robot.drive(0, 2000);

        robot.close();

        robot.drive(0, 2000);
        // Drive Backwards (w/ stone)
        robot.drive(-0.5, 1000);
        // Drive Left (w/ stone)
        robot.strafe(-1,2000);
        // Release Stone
        // robot.open();
        // Drive Backwards (away from stone)
        // robot.drive(-0.5, 1000);
        // Strafe Right To initial Autonomous Position
        // robot.strafe(0.5,4000);
    }
}
