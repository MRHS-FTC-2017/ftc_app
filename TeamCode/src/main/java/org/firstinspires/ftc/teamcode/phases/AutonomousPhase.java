package org.firstinspires.ftc.teamcode.phases;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;

public interface AutonomousPhase {
    boolean process(RobotHardware robot, Telemetry telemetry);
}
