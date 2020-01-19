package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Test_Chassis_10516",group = "TeleOp")
public class Test_Chassis_1 extends OpMode {
    DcMotor fl, fr, bl, br;
    @Override
    public void init() {
        fl = hardwareMap.get(DcMotor.class, "Front Left");
        fr = hardwareMap.get(DcMotor.class, "Front Right");
        bl = hardwareMap.get(DcMotor.class, "Back Left");
        br = hardwareMap.get(DcMotor.class, "Back Right");

        fr.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);
    }
    @Override
    public void init_loop() {

    }
    @Override
    public void loop() {
        // Wheels
        final double v1 = ((gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x)/2.5);
        final double v2 = ((gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x)/2.5);
        final double v3 = ((gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x)/2.5);
        final double v4 = ((gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x)/2.5);

        fl.setPower(v1);
        bl.setPower(v2);
        br.setPower(v3);
        fr.setPower(v4);
    }
}
