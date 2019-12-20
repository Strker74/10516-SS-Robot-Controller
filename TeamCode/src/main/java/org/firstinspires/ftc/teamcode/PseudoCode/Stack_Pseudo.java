package org.firstinspires.ftc.teamcode.PseudoCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Disabled
@Autonomous
public class Stack_Pseudo extends LinearOpMode {
    private void grab() {}
    private void drive(int distance) {}
    private void strafe(int distance) {}
    private void Up(int distance) {}
    private void Down(int distance) {}
    private void Return(int a) {}
    int i; // SkyStone number (1,2,3) from bridge
    int a; // Distance for lin slide up
    int b; // Distance for second stone stacking
    int c; // Distance for first stone stacking
    int d; // Distance for strafing to next tower stacking spot
    int n; // Distance to pass sky bridge
    int m; // Distance from sky bridge to foundation
    // Same Tower
    private void Tower() {
        grab(); // Pick up stone
        drive(n); // Drive past sky bridge
        Up(a); // Lift Slide
        drive(m); // Drive to foundation
        Down(c); // Stack the first stone
        Return(i + 3); // Drive commands to return to the i + 3 stone in quarry
        grab(); // Pick up stone
        drive(n + 3); // Drive past sky bridge (added 3 for the different stone)
        Up(a); // Lift Slide
        drive(m); // Drive to foundation
        Down(b); // Stack the second stone in the same tower
    }
    // Multiple Towers
    private void Stacks() {
        grab(); // Pick up stone
        drive(n); // Drive past sky bridge
        Up(a); // Lift Slide
        drive(m); // Drive to foundation
        Down(c); // Stack the first stone
        Return(i + 3); // Drive commands to return to the i + 3 stone in quarry
        grab(); // Pick up stone
        drive(n + 3); // Drive past sky bridge (added 3 for the different stone)
        Up(a); // Lift Slide
        drive(m); // Drive to foundation
        strafe(d); // Strafe to next column (not touching for additional tower bonuses)
        Down(c); // Stack the second stone in a new tower
    }
    @Override
    public void runOpMode() {

    }
}
