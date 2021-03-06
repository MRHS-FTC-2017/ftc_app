package org.firstinspires.ftc.teamcode.phases;

import android.util.Pair;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;

import java.util.LinkedList;

public class ForwardColorCraterAndClaimingPhase implements AutonomousPhase {


    private boolean isInitialized = false;
    private double power;
    private boolean strafe;
    private double hueMax;
    private double hueMin;
    private long maxDuration;
    private double cutOff;
    boolean seen;
    private ElapsedTime runtime = new ElapsedTime();
    private LinkedList<AutonomousPhase> injectedPhases;
    private int elementsPassed;


    /**
     * This phase will move the robot until it sees a color in the specific hue range.
     *
     * @param power  Power to assign for movement (forrward and left (strafe) are positive)
     * @param strafe Set to true if robot should strafe
     * @param hueMax Maximum hue to recognize as target color
     * @param hueMin Minimum hue to recognize as target color
     */
    public ForwardColorCraterAndClaimingPhase(double power, boolean strafe, double hueMax, double hueMin, long maxDuration, double cutOff) {
        this.power = power;
        this.strafe = strafe;
        this.hueMax = hueMax;
        this.hueMin = hueMin;
        this.maxDuration = maxDuration;
        this.cutOff = cutOff;
        this.seen = false;
        this.injectedPhases = null;
        this.elementsPassed = 0;
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
            setMotors(robot, power);
            runtime.reset();
            isInitialized = true;

        }


        //////////
        //Looks for spikes in distance
        //////////

        if (robot.getDistIL() <= cutOff && !seen) {
            elementsPassed += 1;
            seen = true;
        }

        if (robot.getDistIL() > cutOff && seen) {
            seen = false;
        }

        if (robot.getColorFrontHue() <= hueMax && robot.getColorFrontHue() >= hueMin) {
            setMotors(robot, 0);
            isComplete = true;
        }

        if (runtime.milliseconds() >= maxDuration) {
            setMotors(robot, 0);
            isComplete = true;
        }

        if (isComplete) {
            if (elementsPassed == 1) {
                injectedPhases = new LinkedList<>();
                //adjust to push mineral
                injectedPhases.add(new ForwardDurationPhase(200, 1, true));
                //push gold mineral
                injectedPhases.add(new ForwardDurationPhase(150, 1, false));
                //backup
                injectedPhases.add(new ForwardDurationPhase(200, -1, false));
                //strafe towards depot
                injectedPhases.add(new ForwardDurationPhase(1400, 1, true));
                //spin to face depot
                injectedPhases.add(new TurnDurationPhase(1100, 1));
                //drive into depot
                injectedPhases.add(new ForwardDurationPhase(1400, 1, false));
                //drop marker
                injectedPhases.add(new MarkerDropPhase(1,5000));

            } else if (elementsPassed == 2) {
                injectedPhases = new LinkedList<>();
                //adjust to push mineral
                injectedPhases.add(new ForwardDurationPhase(200, 1, true));
                //push gold mineral
                injectedPhases.add(new ForwardDurationPhase(150, 1, false));
                //backup
                injectedPhases.add(new ForwardDurationPhase(150, -1, false));
                //strafe towards depot
                injectedPhases.add(new ForwardDurationPhase(1950, 1, true));
                //spin to face depot
                injectedPhases.add(new TurnDurationPhase(1150, 1));
                //drive into depot
                injectedPhases.add(new ForwardDurationPhase(1400, 1, false));
                //drop marker
                injectedPhases.add(new MarkerDropPhase(1,5000));
            } else if (elementsPassed == 3) {
                injectedPhases = new LinkedList<>();
                //adjust to push mineral
                injectedPhases.add(new ForwardDurationPhase(200, 1, true));
                //push gold mineral
                injectedPhases.add(new ForwardDurationPhase(150, 1, false));
                //backup
                injectedPhases.add(new ForwardDurationPhase(200, -1, false));
                //strafe towards depot
                injectedPhases.add(new ForwardDurationPhase(2300, 1, true));
                //spin to face depot
                injectedPhases.add(new TurnDurationPhase(1050, 1));
                //drive into depot
                injectedPhases.add(new ForwardDurationPhase(1400, 1, false));
                //drop marker
                injectedPhases.add(new MarkerDropPhase(1,5000));
            }
        }

        return new Pair<Boolean, LinkedList<AutonomousPhase>>(isComplete, injectedPhases);
    }

    /**
     * This method initializes a single motor in forward mode using run-to-position encoder mode.
     *
     * @param motor The motor to initialize
     * @param power The power level to assign to the motor
     */
    private void initMotor(DcMotor motor, double power) {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setPower(power);
    }

    private void setMotors(RobotHardware robot, double power) {
        initMotor(robot.leftFront, power * (strafe ? -1 : 1));
        initMotor(robot.rightFront, power);
        initMotor(robot.leftBack, power);
        initMotor(robot.rightBack, power * (strafe ? -1 : 1));
    }
}
