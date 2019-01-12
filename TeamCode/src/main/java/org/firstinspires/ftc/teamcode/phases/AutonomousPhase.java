package org.firstinspires.ftc.teamcode.phases;

import android.util.Pair;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;

import java.util.LinkedList;

public interface AutonomousPhase {
    Pair<Boolean, LinkedList<AutonomousPhase>> process(RobotHardware robot, Telemetry telemetry);
}
