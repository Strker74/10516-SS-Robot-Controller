package org.firstinspires.ftc.teamCode_10516.TeleOperator;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamCode_10516.Robot;

@TeleOp(name="Test Chassis 1", group = "Test")
public class Test_Chassis_1 extends OpMode {
    Robot robot = new Robot();
    @Override
    public void init() {
        robot.init(hardwareMap);
    }
    @Override
    public void init_loop() {

    }
    @Override
    public void loop() {

        if (gamepad1.a) {
            robot.close();
        }

        if (gamepad1.b) {
            robot.open();
        }

        robot.mecanumDrive(gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
    }
}