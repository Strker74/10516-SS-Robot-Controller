package org.firstinspires.ftc.teamCode_10516;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;


public class Robot {
    // Public Members
    public DcMotor frontLeft, frontRight, backLeft, backRight;
    public Servo leftJacket, rightJacket;
    BNO055IMU imu;
    Orientation angles;
    Acceleration gravity;
    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

    // Initialization
    public void init(HardwareMap hMap) {
        frontLeft = hMap.get(DcMotor.class,"Front Left");
        backLeft = hMap.get(DcMotor.class,"Back Left");
        backRight = hMap.get(DcMotor.class,"Back Right");
        frontRight = hMap.get(DcMotor.class,"Front Right");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        leftJacket = hMap.get(Servo.class, "Left Jacket");
        rightJacket = hMap.get(Servo.class, "Right Jacket");

        rightJacket.setDirection(Servo.Direction.REVERSE);

        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }
    // Autonomous Methods
    public void drive(double p1, double p2, double p3, double p4, long time) { // + powers are forward
        ElapsedTime timer = new ElapsedTime();
        frontLeft.setPower(p1/2);
        backLeft.setPower(p2/2);
        frontRight.setPower(p3);
        backRight.setPower(p4/2);
        while (timer.milliseconds() < time);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }
    public void drive(double p, long t) {
        // + powers are forward
        drive(p, p, p, p, t);
    }
    public void strafe(double p, long t) {
        // + powers are right
        drive(p, -p, -p, p, t);
    }
    public void rotate(double p, long t) {
        // + powers are right
        drive(p, -p, -p, p, t);
    }
    public void open() {
        leftJacket.setPosition(0.75);
        rightJacket.setPosition(1);
    }
    public void close() {
        leftJacket.setPosition(0.25);
        rightJacket.setPosition(0.5);
    }
    // Driver-Controlled Methods
    /**
     * Mecanum Drivetrain TeleOp Code
     * @param y Forward/Backward Force (GamePad Left Stick y)
     * @param x Rotational Force (GamePad Right Stick x)
     * @param z Left/Right (Strafe) Force (GamePad Left Stick x)
     */
    public void mecanumDrive(double y, double x, double z) {
        final double v1 = ((y - x - z)/2);
        final double v2 = ((y - x + z)/2);
        final double v3 = ((y + x - z)/2);
        final double v4 = (y + x + z);

        frontLeft.setPower(-v1);
        backLeft.setPower(-v2);
        backRight.setPower(-v3);
        frontRight.setPower(-v4);
    }
    /**
     * Mecanum Drivetrain TeleOp Code
     * @param y Forward/Backward Force (GamePad Left Stick y)
     * @param x Rotational Force (GamePad Right Stick x)
     * @param z Left/Right (Strafe) Force (GamePad Left Stick x)
     */
    public void trigMecDrive(double y, double x, double z) {
        double r = Math.hypot(z, y);
        double robotangle = Math.atan2(y, z) - Math.PI/4;
        double rightX = x*-1;

        final double v1 = r * Math.cos(robotangle) + rightX;
        final double v2 = r * Math.sin(robotangle) - rightX;
        final double v3 = r * Math.sin(robotangle) + rightX;
        final double v4 = r * Math.cos(robotangle) - rightX;

        frontLeft.setPower(v1);
        backLeft.setPower(v2);
        backRight.setPower(v3);
        frontRight.setPower(v4);
    }
}
