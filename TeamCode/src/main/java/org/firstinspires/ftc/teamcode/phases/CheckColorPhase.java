package org.firstinspires.ftc.teamcode.phases;

import android.util.Pair;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;

import java.util.LinkedList;
import java.util.List;

public class CheckColorPhase implements AutonomousPhase {


    private boolean isInitialized = false;

    private double hueMax;
    private double hueMin;
    private long maxDuration;
    private ElapsedTime runtime = new ElapsedTime();
    private LinkedList<AutonomousPhase> injectedPhases;


    /**
     * This phase will move the robot until it sees a color in the specific hue range.
     * @param hueMax Maximum hue to recognize as target color
     * @param hueMin Minimum hue to recognize as target color
     */
    public CheckColorPhase(double hueMax, double hueMin, long maxDuration) {
        this.hueMax = hueMax;
        this.hueMin = hueMin;
        this.maxDuration = maxDuration;
        this.injectedPhases = null;
    }

    /**
     * This method processes one op-mode process call with the intent of executing the forward phase
     *
     * @param robot The robot configuration (provided by the op-mode class)
     * @return True if the forward phase is complete, false if there's more to do.
     */
    @Override
    public Pair<Boolean, LinkedList<AutonomousPhase>> process(RobotHardware robot, Telemetry telemetry) {
        boolean isComplete = false;

        if (!isInitialized) {
            robot.decision = false;
            runtime.reset();
            isInitialized = true;

        }

        if (robot.getColorFrontHue() <= hueMax && robot.getColorFrontHue() >= hueMin) {
            isComplete = true;
            robot.decision = true;
        }
        else if (robot.getColorFrontHue() > hueMax){
            isComplete = true;
        }
        if (runtime.milliseconds() >= maxDuration) {
            isComplete = true;
        }

        return new Pair<Boolean, LinkedList<AutonomousPhase>>(isComplete, null);
    }


    /**
     * This method initializes a single motor in forward mode using run-to-position encoder mode.
     *
     * @param motor The motor to initialize
     * @param power The power level to assign to the motor
     */

}
