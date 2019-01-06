package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.phases.AutonomousPhase;

import java.util.LinkedList;

public abstract class AutoAbstract extends OpMode
{
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

        // process the current phase, if it indicates it has completed, pop the next phase from list
        if (currentPhase != null) {
            boolean phaseCompleted = currentPhase.process(robot, telemetry);
            if (phaseCompleted) {
                currentPhase = phaseList.pop();
            }
        }

        // tell robot configuration to emit general telemetry
        robot.emitTelemetry(telemetry);
    }
}
