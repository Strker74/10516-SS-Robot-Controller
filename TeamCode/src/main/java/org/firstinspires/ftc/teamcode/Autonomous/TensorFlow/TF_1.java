/* Copyright (c) 2019 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Autonomous(name = "TensorFlow Delivery 1", group = "Concept")
public class TF_1 extends LinearOpMode {

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

    // TensorFlow/Vuforia Setup
    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "Skystone";
    private static final String VUFORIA_KEY = "ASmSKXH/////AAABmY7TDiIBfE/Av2RS6r/Ql+6C7BKftNanAIQQf5MjM39GPcQolwWYbydGb6KjVANM79PgnHGC8qrWVJUonUORcT5MOP+bEk6JYtlhg5X7peKAfdUUjTGkfSn/EtSNBy0sAKKsYiggwAe3xmjuI6K/89xVmRuqhWEh/u+UZGyvgDyTNSZDbEFOJzM9ZBUAM7XZWfqeYXQlOUdxNfQars+PuHUjii8WPYSB1RnrpYtVjKI8dbcZOuHuvBggD0itrnESyqu6LAam5UX9ZOIxr30txWTzsk7uIbyuKATn7hp/KeY9cW2NilD4HurRj3a0I3OV41DbGRXEf3XpabcMTlM515t8epAUXQeg77HynQ17WmAr";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    // Drive Methods
    private void drive(double p1, double p2, double p3, double p4, long time) {
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
    } // + powers are forward
    private void drive(double p, long t) {drive(p, p, p, p, t);} // + powers are forward
    private void strafe(double p, long t) {drive(p, -p, -p, p, t);} // + powers are right
    private void rotate(double p, long t) {drive(p, -p, -p, p, t);} // + powers are right

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
    private void lift(double p, long time) {
        ElapsedTime timer = new ElapsedTime();
        linSlide.setPower(p);
        while (timer.milliseconds() < time);
        linSlide.setPower(0);
    } // + powers are up
    private void left() {arm.setPosition(1);}
    private void right() {arm.setPosition(0);}

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
        initialize();
        initVuforia();
        initTfod();
        strafe(-0.25,2000); // Approach the quarry sideways
    }

    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.8;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
        tfod.activate();
    }

    private void initialize() {
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
    }
}
