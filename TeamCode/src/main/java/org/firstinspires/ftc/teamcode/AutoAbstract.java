package org.firstinspires.ftc.teamcode;

import android.util.Pair;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.phases.AutonomousPhase;

import java.util.LinkedList;

public abstract class AutoAbstract extends OpMode {
    private RobotHardware robot = new RobotHardware();
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
            Pair<Boolean, AutonomousPhase> result = currentPhase.process(robot, telemetry);
            boolean phaseCompleted = result.first;
            if (phaseCompleted) {
                AutonomousPhase injectedPhase = result.second;
                if (injectedPhase == null) {
                    currentPhase = phaseList.pop();
                } else {
                    currentPhase = injectedPhase;
                }
            }
        }
    }
}
