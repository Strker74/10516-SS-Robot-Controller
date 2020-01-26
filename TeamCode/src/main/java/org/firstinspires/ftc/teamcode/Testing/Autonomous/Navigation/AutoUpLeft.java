package org.firstinspires.ftc.teamcode.Testing.Autonomous.Navigation;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Auto Navigate [Up Left]", group = "Autonomous")
public class AutoUpLeft extends LinearOpMode {
    private DcMotor fl, fr, bl, br;
    private Servo leftJacket;
    private Servo rightJacket;
    private double jPos;

    private void Initialize () {
        fl = hardwareMap.get(DcMotor.class,"Front Left");
        bl = hardwareMap.get(DcMotor.class,"Back Left");
        br = hardwareMap.get(DcMotor.class,"Back Right");
        fr = hardwareMap.get(DcMotor.class,"Front Right");

        fr.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);

        leftJacket = hardwareMap.get(Servo.class, "Left Jacket");
        rightJacket = hardwareMap.get(Servo.class, "Right Jacket");

        rightJacket.setDirection(Servo.Direction.REVERSE);
    }
    private void drive(double p1, double p2, double p3, double p4, long time) { // + powers are forward
        ElapsedTime timer = new ElapsedTime();
        fl.setPower(p1);
        bl.setPower(p2);
        fr.setPower(p3*1.7);
        br.setPower(p4);
        while (timer.milliseconds() < time);
        fl.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
    }
    private void drive(double p, long t) { // + powers are forward
        drive(-p, -p, -p, -p, t);
    }
    private void strafe(double p, long t) { // + powers are right
        drive(-p, p, p, -p, t);
    }
    private void rotate(double p, long t) { // + powers are right
        drive(p, -p, -p, p, t);
    }

    @Override
    public void runOpMode() {
        Initialize();

        // Command Line
        waitForStart();
        drive(0.25, 2000);
        strafe(-0.25,2000);
    }
}
