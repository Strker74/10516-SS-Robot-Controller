package org.firstinspires.ftc.teamcode.Testing.TeleOperator;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import java.lang.Math;

@TeleOp(name = "Test_Chassis_10516",group = "TeleOp")
public class Test_Chassis_1 extends OpMode {
    private DcMotor fl, fr, bl, br;
    private Servo leftJacket;
    private Servo rightJacket;
    private double jPos;
    @Override
    public void init() {
        fl = hardwareMap.get(DcMotor.class, "Front Left");
        fr = hardwareMap.get(DcMotor.class, "Front Right");
        bl = hardwareMap.get(DcMotor.class, "Back Left");
        br = hardwareMap.get(DcMotor.class, "Back Right");

        fr.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);

        leftJacket = hardwareMap.get(Servo.class, "Left Jacket");
        rightJacket = hardwareMap.get(Servo.class, "Right Jacket");

        rightJacket.setDirection(Servo.Direction.REVERSE);

    }
    @Override
    public void init_loop() {
        jPos = leftJacket.getPosition();
    }
    @Override
    public void loop() {
        telemetry.addData("Left Jacket Position", leftJacket.getPosition());
        telemetry.addData("Right Jacket Position", rightJacket.getPosition());
        telemetry.addData("JPOS", jPos);

        if (gamepad1.a) {
            leftJacket.setPosition(0.25);
            rightJacket.setPosition(0.50);
        }
        if (gamepad1.b) {
            jPos -= 0.05;
            leftJacket.setPosition(1);
            rightJacket.setPosition(1);
        }

        // Wheels
        final double v1 = ((gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x)/3);
        final double v2 = ((gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x)/3);
        final double v3 = ((gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x)/3);
        final double v4 = ((gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x)/3);

        fl.setPower(v1);
        bl.setPower(v2);
        br.setPower(v3);
        fr.setPower(v4);
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
    }
}
