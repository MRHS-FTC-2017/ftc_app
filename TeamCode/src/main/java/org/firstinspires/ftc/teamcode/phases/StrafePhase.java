package org.firstinspires.ftc.teamcode.phases;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;

public class StrafePhase implements AutonomousPhase {
    private boolean isInitialized = false;
    private int targetPosition;
    private double power;

    /**
     * This instantiates the phase with a given target position (positive is left) and power
     *
     * @param targetPosition Target position of wheel rotation to strafe leftward
     * @param power Target power level for strafe movement
     */
    public StrafePhase(int targetPosition, double power) {
        this.targetPosition = targetPosition;
        this.power = power;
    }

    /**
     * This method processes one op-mode process call with the intent of executing the strafe phase
     *
     * @param robot The robot configuration (provided by the op-mode class)
     * @return True if the strafe phase is complete, false if there's more to do.
     */
    @Override
    public boolean process(RobotHardware robot, Telemetry telemetry) {
        if (!isInitialized) {
            initMotorStrafe(robot.leftFront, -power);
            initMotorStrafe(robot.rightFront, power);
            initMotorStrafe(robot.leftBack, power);
            initMotorStrafe(robot.rightBack, -power);
            isInitialized = true;
        }

        return checkMotorStrafe(robot.leftFront) &&
                checkMotorStrafe(robot.rightFront) &&
                checkMotorStrafe(robot.leftBack) &&
                checkMotorStrafe(robot.rightBack);
    }

    /**
     * This method initializes a single motor in strafe mode using run-to-position encoder mode.
     *
     * @param motor The motor to initialize
     * @param power The power level to assign to the motor
     */
    private void initMotorStrafe(DcMotor motor, double power) {
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
    private boolean checkMotorStrafe(DcMotor motor) {
        if (!motor.isBusy()) {
            motor.setPower(0);
            return true;
        }
        return false;
    }
}
