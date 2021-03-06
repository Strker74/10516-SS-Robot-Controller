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

import java.lang.reflect.Array;


public class Robot {
    // Public Members
    public DcMotor frontLeft, frontRight, backLeft, backRight;
    public Servo leftJacket, rightJacket;
    public DcMotor lift;
    public Servo pOR; // Point of Rotation
    public Servo leftClaw, rightClaw;
    BNO055IMU imu;
    Orientation angles;
    Acceleration gravity;
    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

    public DcMotor[] driveMotors;

    // Initialization
    public void init(HardwareMap hMap) {
        frontLeft = hMap.get(DcMotor.class,"Front Left");
        backLeft = hMap.get(DcMotor.class,"Back Left");
        backRight = hMap.get(DcMotor.class,"Back Right");
        frontRight = hMap.get(DcMotor.class,"Front Right");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        leftJacket = hMap.get(Servo.class, "Left Jacket");
        rightJacket = hMap.get(Servo.class, "Right Jacket");

        rightJacket.setDirection(Servo.Direction.REVERSE);

        lift = hMap.get(DcMotor.class, "Lift");

        pOR = hMap.get(Servo.class,"Rotation");


        leftClaw = hMap.get(Servo.class, "Left Claw");
        rightClaw = hMap.get(Servo.class, "Right Claw");

        rightClaw.setDirection(Servo.Direction.REVERSE);

        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        gravity  = imu.getGravity();

        angles.firstAngle *= -1;
        angles.secondAngle *= -1;
        angles.thirdAngle *= -1;
    }
    // Autonomous Methods
    public void drive(double p1, double p2, double p3, double p4, long time) { // + powers are forward
        ElapsedTime timer = new ElapsedTime();
        frontLeft.setPower(p1);
        backLeft.setPower(p2);
        frontRight.setPower(p3);
        backRight.setPower(p4);
        while (timer.milliseconds() < time);
        for (DcMotor i : driveMotors) {
            i.setPower(0);
        }
    }

    public void drive(double p, long t) {
        // + powers are forward
        drive(p, p, p, p, t);
        drive(0, 0, 0, 0,250);
    }

    public void strafe(double p, long t) {
        // + powers are right
        drive(p, -p, -p, p, t);
        drive(0, 0, 0, 0,250);
    }

    public void rotate(double p, long t) {
        // + powers are right
        drive(p, p, -p, -p, t);
        drive(0, 0, 0, 0,250);
    }

    public void setDriveMotors(double power) {
        for (DcMotor i : driveMotors) {
            i.setPower(power);
        }
    }

    public void setStrafeMotors(double power) {
        frontLeft.setPower(power);
        backLeft.setPower(-power);
        frontRight.setPower(-power);
        backRight.setPower(power);
    }

    public void setRotateMotors(double power) {
        frontLeft.setPower(power);
        backLeft.setPower(power);
        frontRight.setPower(-power);
        backRight.setPower(-power);
    }

    public void encoderDrive(double power, int distance) {

        for (DcMotor i : driveMotors) {
            i.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        for (DcMotor i : driveMotors) {
            i.setTargetPosition(distance);
        }
        for (DcMotor i : driveMotors) {
            i.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        setDriveMotors(power);

        while (frontRight.isBusy() && frontLeft.isBusy() && backRight.isBusy() && backLeft.isBusy());

        setDriveMotors(0);
    }

    public void encoderStrafe(double power, int distance) {

        for (DcMotor i : driveMotors) {
            i.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        frontLeft.setTargetPosition(distance);
        backLeft.setTargetPosition(-distance);
        frontRight.setTargetPosition(-distance);
        backRight.setTargetPosition(distance);

        for (DcMotor i : driveMotors) {
            i.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        setStrafeMotors(power);

        while (frontRight.isBusy() && frontLeft.isBusy() && backRight.isBusy() && backLeft.isBusy());

        setDriveMotors(0);
    }


    public void rotateGyro(double power, double heading) {
        if (heading < 0) {
            while (angles.firstAngle < heading) {
                setRotateMotors(-power);
            }
            setDriveMotors(0);
        } else if (heading > 0) {
            while (angles.firstAngle > heading) {
                setRotateMotors(power);
            }
            setDriveMotors(0);
        }
    }

    public void open() {
        leftJacket.setPosition(0.25);
        rightJacket.setPosition(0.25);
    }

    public void close() {
        leftJacket.setPosition(1);
        rightJacket.setPosition(1);
    }

    public void grab() {
        leftClaw.setPosition(0.625);
        rightClaw.setPosition(0.625);
    }

    public void drop() {
        leftClaw.setPosition(0);
        rightClaw.setPosition(0);
    }

    // Driver-Controlled Methods
    /**
     * Tank Drive TeleOp Code
     * @param left Left Wheel Powers
     * @param right Right Wheel Powers
     */
    public void tankDrive(double left, double right) {
        frontLeft.setPower(left);
        backLeft.setPower(left);
        frontRight.setPower(right);
        backRight.setPower(right);
    }

    /**
     * Mecanum Drivetrain TeleOp Code
     * @param y Forward/Backward Force (GamePad Left Stick y)
     * @param x Rotational Force (GamePad Right Stick x)
     * @param z Left/Right (Strafe) Force (GamePad Left Stick x)
     */
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

    public void setLift(double power) {
        lift.setPower(power);
    }
}
