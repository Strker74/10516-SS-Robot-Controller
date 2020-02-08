package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(name = "TeleOp_1", group = "TeleOp")
public class TeleOp_1 extends OpMode {
    // Drive
    private DcMotor frontLeft, backLeft, backRight, frontRight;
    // Flywheels
    private DcMotor leftFly, rightFly;
    // Linear Slide
    private DcMotor linSlide;
    // Claw
    private Servo leftClaw, rightClaw;
    // Arm Rotation
    private Servo arm;
    // Variables
    private double posLClaw;
    private double posRClaw;
    private double posArm;
    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class,"Front Left");
        backLeft = hardwareMap.get(DcMotor.class,"Back Left");
        backRight = hardwareMap.get(DcMotor.class,"Back Right");
        frontRight = hardwareMap.get(DcMotor.class,"Front Right");
        // Flywheels
        leftFly = hardwareMap.get(DcMotor.class, "Left Fly");
        rightFly = hardwareMap.get(DcMotor.class, "Right Fly");
        // Lift
        linSlide = hardwareMap.get(DcMotor.class, "Linear Slide");
        arm = hardwareMap.get(Servo.class, "Arm");
        // Claw
        leftClaw = hardwareMap.get(Servo.class, "Left Claw");
        rightClaw = hardwareMap.get(Servo.class, "Right Claw");
    }
    @Override
    public void init_loop() {
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        rightFly.setDirection(DcMotor.Direction.REVERSE);
        linSlide.setDirection(DcMotor.Direction.REVERSE);
        rightClaw.setDirection(Servo.Direction.REVERSE);
        arm.setDirection(Servo.Direction.REVERSE);
        posLClaw = leftClaw.getPosition();
        posRClaw = leftClaw.getPosition();
        posArm = arm.getPosition();
    }
    @Override
    public void loop() {
        // Updates
        telemetry.addData("left claw servo position", leftClaw.getPosition());
        telemetry.addData("left claw servo position", posLClaw);
        telemetry.addData("right claw servo position", rightClaw.getPosition());
        telemetry.addData("right claw servo position", posRClaw);
        telemetry.addData("arm servo position", arm.getPosition());
        telemetry.addData("arm servo position", posArm);
        telemetry.update();

        // Wheels
        final double v1 = ((-gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x)/2.5);
        final double v2 = ((-gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x)/2.5);
        final double v3 = ((-gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x)/2.5);
        final double v4 = ((-gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x)/2.5);

        frontLeft.setPower(v1);
        backLeft.setPower(v2);
        backRight.setPower(v3);
        frontRight.setPower(v4);

        // Flywheels
        leftFly.setPower(100*gamepad1.right_stick_y);
        rightFly.setPower(100*gamepad1.right_stick_y);

        // Linear Slide
        linSlide.setPower(gamepad2.left_stick_y/2);

        // Claw
        if (gamepad2.b) {
            posLClaw += 0.05;
            posRClaw += 0.05;
            leftClaw.setPosition(posLClaw);
            rightClaw.setPosition(posRClaw);
            telemetry.addData("left claw Servo Position", leftClaw.getPosition());
            telemetry.addData("right claw Servo Position", rightClaw.getPosition());
            telemetry.update();
        }
        if (gamepad2.x) {
            posLClaw -= 0.1;
            posRClaw -= 0.1;
            leftClaw.setPosition(posLClaw);
            rightClaw.setPosition(posRClaw);
            telemetry.addData("left claw Servo Position", leftClaw.getPosition());
            telemetry.addData("right claw Servo Position", rightClaw.getPosition());
            telemetry.update();
        }
        if (gamepad2.a) {
            posArm += 0.05;
            arm.setPosition(posArm);
            telemetry.addData("arm Servo Position", arm.getPosition());
            telemetry.update();
        }
        if (gamepad2.y) {
            posArm -= 0.05;
            arm.setPosition(posArm);
            telemetry.addData("arm Servo Position", arm.getPosition());
            telemetry.update();
        }
    }
}
