package org.firstinspires.ftc.teamcode.phases;

import android.util.Pair;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;
import java.util.LinkedList;


public class MarkerDropPhase implements AutonomousPhase {

    private double power;
    private long duration;
    private ElapsedTime runtime = new ElapsedTime();
    private boolean isInitialized = false;
    private boolean isComplete;


    public MarkerDropPhase(double power, long duration) {
        this.power = power;
        this.duration = duration;
    }

    /**
     * This method processes one op-mode process call with the intent of executing the forward phase
     *
     * @param robot The robot configuration (provided by the op-mode class)
     * @return True if the forward phase is complete, false if there's more to do.
     */
    @Override
    public Pair<Boolean, LinkedList<AutonomousPhase>> process(RobotHardware robot, Telemetry telemetry) {
        isComplete = false;

        if (!isInitialized) {
            runtime.reset();
            isInitialized = true;
        }

        robot.collector.setPower(power);

        if (runtime.milliseconds() >= duration) {
            robot.collector.setPower(0);
            isComplete = true;
        }


        return new Pair<>(isComplete, null);
    }
}
