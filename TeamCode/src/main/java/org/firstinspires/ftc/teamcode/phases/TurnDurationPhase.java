package org.firstinspires.ftc.teamcode.phases;

import android.util.Pair;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;

import java.util.LinkedList;

public class TurnDurationPhase implements AutonomousPhase {
    private boolean isInitialized = false;
    private long duration;
    private double power;

    private ElapsedTime runtime = new ElapsedTime();

    /**
     * This instantiates the phase with a given target position (positive is forward (or left in strafe mode)) and power
     *
     * @param duration Length of time for wheel rotation to strafe leftward
     * @param power Target power level for strafe movement
     */
    public TurnDurationPhase(long duration, double power) {
        this.duration = duration;
        this.power = power;
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
            initMotorStrafe(robot.leftFront, -power);
            initMotorStrafe(robot.rightFront, power);
            initMotorStrafe(robot.leftBack, -power);
            initMotorStrafe(robot.rightBack, power);
            runtime.reset();
            isInitialized = true;
        }

        if (runtime.milliseconds() >= duration) {
            robot.leftFront.setPower(0.0f);
            robot.rightFront.setPower(0.0f);
            robot.leftBack.setPower(0.0f);
            robot.rightBack.setPower(0.0f);
            isComplete = true;
        }


        return new Pair<>(isComplete, null);
    }

    /**
     * This method initializes a single motor in forward mode using run-to-position encoder mode.
     *
     * @param motor The motor to initialize
     * @param power The power level to assign to the motor
     */
    private void initMotorStrafe(DcMotor motor, double power) {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setPower(power);

    }
}
