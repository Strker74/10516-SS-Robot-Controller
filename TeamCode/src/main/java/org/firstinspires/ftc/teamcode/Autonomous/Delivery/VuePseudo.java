package org.firstinspires.ftc.teamcode.Autonomous.Delivery;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.vuforia.Vuforia;

@Autonomous(name = "EN Pseudo Code [Don't Run]", group = "Pseudo Code")
@Disabled
public class VuePseudo {
    private void grab() {
        // This is a method which is supposed to
        // program the robot's grabbing mechanism.
        // Mostly just a series of commands
    }
    private void search(String target) {
        int n = Vuforia.Trackables.find(target);
        if (Vuforia.Trackables.isVisible(n) != true) {
            return false;
        } else {
            return true;
        }
    }
    @Override
    public void runOpMode() {

    }
}
