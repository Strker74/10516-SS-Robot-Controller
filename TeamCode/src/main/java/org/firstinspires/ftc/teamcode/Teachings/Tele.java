package org.firstinspires.ftc.teamcode.Teachings;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Test", group = "TeleOp")
public class Tele extends OpMode {
    private DcMotor left, right;
    @Override
    public void init() {
        left = hardwareMap.get(DcMotor.class, "Left");
        right = hardwareMap.get(DcMotor.class, "Right");
    }
    @Override
    public void init_loop() {
        right.setDirection(DcMotor.Direction.REVERSE);
        left.setDirection(DcMotor.Direction.FORWARD);
    }
    @Override
    public void loop() {
        left.setPower(-gamepad1.left_stick_y);
        right.setPower(-gamepad1.right_stick_y);
    }
}
