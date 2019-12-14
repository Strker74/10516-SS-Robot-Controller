package org.firstinspires.ftc.teamcode.Autonomous.Vuforia;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

@TeleOp(name = "Vuforia [Custom]", group = "Concept")
@Disabled
public class CustomBuild extends LinearOpMode {
    private static final String VUFORIA_KEY = "ASmSKXH/////AAABmY7TDiIBfE/Av2RS6r/Ql+6C7BKftNanAIQQf5MjM39GPcQolwWYbydGb6KjVANM79PgnHGC8qrWVJUonUORcT5MOP+bEk6JYtlhg5X7peKAfdUUjTGkfSn/EtSNBy0sAKKsYiggwAe3xmjuI6K/89xVmRuqhWEh/u+UZGyvgDyTNSZDbEFOJzM9ZBUAM7XZWfqeYXQlOUdxNfQars+PuHUjii8WPYSB1RnrpYtVjKI8dbcZOuHuvBggD0itrnESyqu6LAam5UX9ZOIxr30txWTzsk7uIbyuKATn7hp/KeY9cW2NilD4HurRj3a0I3OV41DbGRXEf3XpabcMTlM515t8epAUXQeg77HynQ17WmAr";
    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final float mmPerInch = 25.4f;
    private static final float mmTargetHeight = (6) * mmPerInch; // the height of the center of the target image above the floor
    // Constant for Stone Target
    private static final float stoneZ = 2.00f * mmPerInch;
    // Constants for the center support targets
    private static final float bridgeZ = 6.42f * mmPerInch;
    private static final float bridgeY = 23 * mmPerInch;
    private static final float bridgeX = 5.18f * mmPerInch;
    private static final float bridgeRotY = 59; // Units are degrees
    private static final float bridgeRotZ = 180;
    // Constants for perimeter targets
    private static final float halfField = 72 * mmPerInch;
    private static final float quadField  = 36 * mmPerInch;
    // Class Members
    private OpenGLMatrix lastLocation = null;
    private VuforiaLocalizer vuforia = null;
    private boolean targetVisible = false;
    @Override
    public void runOpMode() {
        initVuforia();


    }
    public void initVuforia() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CAMERA_CHOICE;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }
    public void initHardware() {

    }
}
