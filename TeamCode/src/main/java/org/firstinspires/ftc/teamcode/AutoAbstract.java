package org.firstinspires.ftc.teamcode;

import android.util.Pair;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.phases.AutonomousPhase;

import java.util.LinkedList;
import java.util.List;

public abstract class AutoAbstract extends OpMode {
    public RobotHardware robot = new RobotHardware();
    private LinkedList<AutonomousPhase> phaseList = null;
    private AutonomousPhase currentPhase = null;

    abstract void setPhases(LinkedList<AutonomousPhase> phaseList);

    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "BE2018 Auto Initialized");
    }

    @Override
    public void start() {
        phaseList = new LinkedList<>();
        setPhases(phaseList);
        currentPhase = phaseList.pop();
    }

    @Override
    public void loop() {
        // compute and emit sensor data and telemetry
        robot.computeTelemetry(telemetry, currentPhase);

        // process the current phase, if it indicates it has completed, pop the next phase from list
        if (currentPhase != null) {
            Pair<Boolean, LinkedList<AutonomousPhase>> result = currentPhase.process(robot, telemetry);
            boolean phaseCompleted = result.first;
            if (phaseCompleted) {
                LinkedList<AutonomousPhase> injectedPhases = result.second;
                if (injectedPhases != null) {
                    phaseList.addAll(0, injectedPhases);
                }
                currentPhase = phaseList.pop();
            }
        }
    }
}
