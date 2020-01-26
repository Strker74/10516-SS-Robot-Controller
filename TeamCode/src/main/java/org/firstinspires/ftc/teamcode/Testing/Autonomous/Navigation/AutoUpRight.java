package org.firstinspires.ftc.teamcode.Testing.Autonomous.Navigation;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Auto Navigate [Up Right]", group = "Autonomous")
public class AutoUpRight extends LinearOpMode {
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
    private void drive(double p1, double p2, double p3, double p4, long time) { // + powers are forward
        ElapsedTime timer = new ElapsedTime();
        frontLeft.setPower(p1);
        backLeft.setPower(p2);
        frontRight.setPower(p3);
        backRight.setPower(p4);
        while (timer.milliseconds() < time);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }
    private void drive(double p, long t) {// + powers are forward
        drive(p, p, p, p, t);
    }
    private void strafe(double p, long t) { // + powers are right
        drive(p, -p, -p, p, t);
    }
    private void rotate(double p, long t) { // + powers are right
        drive(p, -p, -p, p, t);
    }
    // Flywheel Methods
    private void flyOn() {
        leftFly.setPower(1);
        rightFly.setPower(1);
    }
    private void flyOff() {
        leftFly.setPower(0);
        rightFly.setPower(0);
    }
    // Lift Methods
    private void lift(double p, long time) { // + powers are up
        ElapsedTime timer = new ElapsedTime();
        linSlide.setPower(p);
        while (timer.milliseconds() < time);
        linSlide.setPower(0);
    }
    private void left() {
        arm.setPosition(1);

    }
    private void right() {
        arm.setPosition(0);
    }
    // Claw Methods
    private void close() {
        leftClaw.setPosition(1);
        rightClaw.setPosition(1);
    }
    private void open() {
        leftClaw.setPosition(0);
        rightClaw.setPosition(0);
    }
    @Override
    public void runOpMode() {
        // Initialization

        // Drive
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

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        rightFly.setDirection(DcMotor.Direction.REVERSE);
        linSlide.setDirection(DcMotor.Direction.REVERSE);
        rightClaw.setDirection(Servo.Direction.REVERSE);
        arm.setDirection(Servo.Direction.REVERSE);
        // Command Line
        waitForStart();
        drive(0.25, 2000);
        strafe(1,500);
    }
}
