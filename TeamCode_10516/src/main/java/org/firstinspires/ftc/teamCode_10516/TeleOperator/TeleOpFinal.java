package org.firstinspires.ftc.teamCode_10516.TeleOperator;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamCode_10516.Robot;

@TeleOp(name="TeleOp Final", group = "TeleOp")
public class TeleOpFinal extends OpMode {
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

        robot.setLift(gamepad2.right_stick_y/4);

        if (gamepad2.a) {
            robot.grab();
        }

        if (gamepad2.b) {
            robot.drop();
        }

        if (gamepad2.x) {
            robot.pOR.setPosition(robot.pOR.getPosition() + 0.1);
        }
        if (gamepad2.y) {
            robot.pOR.setPosition(robot.pOR.getPosition() - 0.1);
        }

    }
}