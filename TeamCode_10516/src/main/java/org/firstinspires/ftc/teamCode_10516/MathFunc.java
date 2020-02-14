package org.firstinspires.ftc.teamCode_10516;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MathFunc {
    public static double formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    public static double formatDegrees(double degrees){
        return AngleUnit.DEGREES.normalize(degrees);
    }
}
