package org.firstinspires.ftc.teamcode.PseudoCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Autonomous(name = "EN Pseudo Code [Don't Run]", group = "Pseudo Code")
@Disabled
public class VuePseudo extends LinearOpMode {
    private void Grab() {}
    private void Initialize() {}
    private void Deliver() {}
    private void Return(int a) {}
    private void Park() {}
    private void Next() {}
    private void Orient() {}
    private boolean Search(String target) {
        // int n = VuforiaTrackables.get(target);

        if (/*VuforiaTrackables.IsVisible(n)*/ true) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void runOpMode() {
        Initialize(); // Setting Up the Hardware-Software Links
        Orient(); // Drive commands to set the robot facing stones
        for (int i = 0; i < 4; i++) { // Checking for one of the three stone orientations
            if(Search("SkyStone")) {
                Grab(); // Lift/Intake commands to pick up stones
                Deliver(); // Drive commands for transport/delivery
                Return(i + 3); // Drive commands to return to the i + 3 stone in quarry
                Grab();
                Deliver();
                Park(); // Drive commands to navigate using vuforia targets
                break;
            } else {
                Next(); // Drive commands to move the robot to the next stone to track
            }
        }
    }
}
