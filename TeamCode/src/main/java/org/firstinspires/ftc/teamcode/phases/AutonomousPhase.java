package org.firstinspires.ftc.teamcode.phases;

import android.util.Pair;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;

public interface AutonomousPhase {
    Pair<Boolean,AutonomousPhase> process(RobotHardware robot, Telemetry telemetry);
}
