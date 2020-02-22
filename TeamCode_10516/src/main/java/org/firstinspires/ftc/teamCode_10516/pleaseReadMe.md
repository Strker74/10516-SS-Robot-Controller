# FTC Team 10516 High Ground Team Code

Welcome!

As mentioned in our engineering notebook, our programming team uses GitHub to effectively communicate with each other concerning programs. Our programming structure was also quite different in comparison to many other teams' code. As an overview, this directory holds 4 contents: this file (pleaseReadMe.md), the folder for autonomous programs (Autonomous), the folder for driver-controlled programs (TeleOerator), and our Robot class (Robot.java). Below, each content is expanded on:

## Robot.java

Robot.java was our primary class where we stored the hardare for our robot. Along with this, we also had our methods, for both autonomous and teleOperator. In every other OpMode, we imported this class and created a new instance of the robot. This is shown in the line below (per OpMode, not Robot.java):

```
Robot robot = new Robot();
```

## Autonomous Programs

Our autonomous programs consisted of four categories: Repositioning the Foundation, Delivering Stones, Stacking Stones, and Navigating the Playing Field.

### Repositioning the Foundation

Repositioning was our second-most focused initiative for autonomous. Our programs for repositioning the foundation all contain the following steps:
1) Approach the foundation
2) Close the "Jacket"
3) Pull the foundation backwards
4) release the foundation
5) Strafe till parked

We used sensors for navigation in this process and the servos for our "Jacket" placed at certain radial positions.

### Delivering Stones

Stone delivery was our third-most focused objective for autonomous. Most of our stone delivery plans involed sucha process where the robot would:
1) Attain a stone (through either a preset mark or vision processing)
2) Navigate the playing field (thus, delivering the stone)
3) Park under the bridge

This initiative also used the "Jacket" attachment in coupling with sensors for pure pursuit and accurate movement.

### Stacking Stones

The last-mostfocused objective ws stacking stones. This was primarily because the attachment for this subsystem was delayed quite a bit. As a result, there isn't much perfected, or even viewable, code at all. We haven't uploaded any new programs for it yet as we will do so upon testing them. Overall though, it seems to incorporate the same delivery set of initiatives, but rather than navigating the playing field at step 2, it instead places the stone on the foundation additionally.

### Navigating the Playing Field

This was our most focused autonomous objective which was also the most efficient, accurate, effecive, and first to be completed. Our current navigation profile consists of navigation to any part of the bridge from any starting position on the field. We have both time-based and encoder/gyro/accelerometer code implemented for this.

## Driver-Controlled Programs

Our main drive-controlled method was the TeleOpFinal.java OpMode. The IMU Testing and Vuforia Test files were for stimulating autonomous programs. The final file - Test_Chassis.java - is a program for our primary robot's chassis and our secondary test chassis.
