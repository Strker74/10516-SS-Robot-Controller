package org.firstinspires.ftc.teamCode_10516.TeleOperator;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Test_Chassis extends OpMode {
    DcMotor frontLeft,backLeft, backRight,frontRight;

    public void mecanumDrive(double y, double x, double z) {
        final double v1 = (y - x - z);
        final double v2 = (y - x + z);
        final double v3 = (y + x - z);
        final double v4 = (y + x + z);

        frontLeft.setPower(-3*v1/4);
        backLeft.setPower(-3*v2/4);
        backRight.setPower(-3*v3/4);
        frontRight.setPower(-3*v4/4);
    }

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class,"Front Left");
        backLeft = hardwareMap.get(DcMotor.class,"Back Left");
        backRight = hardwareMap.get(DcMotor.class,"Back Right");
        frontRight = hardwareMap.get(DcMotor.class,"Front Right");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        mecanumDrive(gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
    }
}
