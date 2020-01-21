package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import java.lang.Math;

@TeleOp(name = "Test_Chassis_10516",group = "TeleOp")
public class Test_Chassis_1 extends OpMode {
    private DcMotor fl, fr, bl, br;
    private Servo jacket;
    @Override
    public void init() {
        fl = hardwareMap.get(DcMotor.class, "Front Left");
        fr = hardwareMap.get(DcMotor.class, "Front Right");
        bl = hardwareMap.get(DcMotor.class, "Back Left");
        br = hardwareMap.get(DcMotor.class, "Back Right");

        fr.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);

        jacket = hardwareMap.get(Servo.class, "Jacket");

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

        /*
        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotangle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI/4;
        double rightX = gamepad1.right_stick_x*-1;
        final double v1 = r * Math.cos(robotangle) + rightX;
        final double v2 = r * Math.sin(robotangle) - rightX;
        final double v3 = r * Math.sin(robotangle) + rightX;
        final double v2 = r * Math.cos(robotangle) - rightX;
        */

        if (gamepad1.a) {
            jacket.setPosition(jacket.getPosition() + 0.1);
        }
        if (gamepad1.b) {
            jacket.setPosition(jacket.getPosition() - 0.1);
        }
    }
}
