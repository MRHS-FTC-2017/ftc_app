package org.firstinspires.ftc.teamcode.phases;

import android.util.Pair;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;

import java.util.LinkedList;


public class MarkerDropPhase implements AutonomousPhase {

    private ElapsedTime runtime = new ElapsedTime();
    private boolean isInitialized = false;
    private boolean isComplete = false;

    /**
     * This method processes one op-mode process call with the intent of executing the forward phase
     *
     * @param robot The robot configuration (provided by the op-mode class)
     * @return True if the forward phase is complete, false if there's more to do.
     */
    @Override
    public Pair<Boolean, LinkedList<AutonomousPhase>> process(RobotHardware robot, Telemetry telemetry) {

        if (!isInitialized) {
            runtime.reset();
            isComplete = false;
            isInitialized = true;
        }

        if (runtime.milliseconds() < 1000) {
            robot.collector.setPower(1);
        }
        else if (runtime.milliseconds() > 1000) {
            robot.collector.setPower(0);
            isComplete = true;
        }

        return new Pair<>(isComplete, null);
    }
}
