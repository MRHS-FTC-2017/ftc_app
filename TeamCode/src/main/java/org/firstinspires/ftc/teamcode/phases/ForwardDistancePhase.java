package org.firstinspires.ftc.teamcode.phases;

import android.util.Pair;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;

import java.util.LinkedList;

public class ForwardDistancePhase implements AutonomousPhase {
    private boolean isInitialized = false;
    private int targetPosition;
    private double power;
    private boolean strafe;
    private boolean leftFrontComplete = false;
    private boolean rightFrontComplete = false;
    private boolean leftBackComplete = false;
    private boolean rightBackComplete = false;

    /**
     * This instantiates the phase with a given target position (positive is forward (or left in strafe mode)) and power
     *
     * @param targetPosition Target position of wheel rotation to strafe leftward
     * @param power Target power level for strafe movement
     */
    public ForwardDistancePhase(int targetPosition, double power, boolean strafe) {
        this.targetPosition = targetPosition;
        this.power = power;
        this.strafe = strafe;
    }

    /**
     * This method processes one op-mode process call with the intent of executing the forward phase
     *
     * @param robot The robot configuration (provided by the op-mode class)
     * @return True if the forward phase is complete, false if there's more to do.
     */
    @Override
    public Pair<Boolean, LinkedList<AutonomousPhase>> process(RobotHardware robot, Telemetry telemetry) {
        if (!isInitialized) {
            initMotor(robot.leftFront, power * (strafe ? -1 : 1));
            initMotor(robot.rightFront, power);
            initMotor(robot.leftBack, power);
            initMotor(robot.rightBack, power* (strafe ? -1 : 1));
            isInitialized = true;
        }

        if (!leftFrontComplete) {
            leftFrontComplete = checkMotor(robot.leftFront);
        }
        if (!rightFrontComplete) {
            rightFrontComplete = checkMotor(robot.rightFront);
        }
        if (!leftBackComplete) {
            leftBackComplete = checkMotor(robot.leftBack);
        }
        if (!rightBackComplete) {
            rightBackComplete = checkMotor(robot.rightBack);
        }

        return new Pair<>(leftFrontComplete && rightFrontComplete && leftBackComplete && rightBackComplete, null);
    }

    /**
     * This method initializes a single motor in forward mode using run-to-position encoder mode.
     *
     * @param motor The motor to initialize
     * @param power The power level to assign to the motor
     */
    private void initMotor(DcMotor motor, double power) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(targetPosition * (int)Math.signum(power));
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(power);
    }

    /**
     * This method checks to see if a given motor has reached its target position, and if so
     * will set the power on that motor to 0 and return true to indicate it has completed.
     *
     * @param motor The motor to check
     * @return True if the motor has reached its target position, false otherwise.
     */
    private boolean checkMotor(DcMotor motor) {
        if (!motor.isBusy()) {
            motor.setPower(0.0f);
            return true;
        }
        return false;
    }
}
