package org.firstinspires.ftc.teamcode.Autonomous.Delivery;

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
        int n = VuforiaTrackables.get(target);
        if (VuforiaTrackables.IsVisible(n)) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public void runOpMode() {
        Initialize(); // Setting Up the Hardware-Software Links
        Orient(); // Drive commands to set the robot facing stones
        for (int i = 0; i < 4; i++) {
            if(Search("SkyStone")) {
                Grab();
                Deliver();
                Return(i + 3);
                Grab();
                Deliver();
                Park();
                break;
            } else {
                Next();
            }
        }
    }
}
