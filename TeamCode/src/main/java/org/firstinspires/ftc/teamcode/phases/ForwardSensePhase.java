package org.firstinspires.ftc.teamcode.phases;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;

public class ForwardSensePhase implements AutonomousPhase {


    private boolean isInitialized = false;
    private double targetDistance;
    private double power;
    private boolean strafe;
    private double backOff;
    private double leadDistance;

    /**
     * This instantiates the phase with a given target position (positive is forward (or left in strafe mode)) and power
     *
     * @param targetDistance Distance from object at which the robot will stop
     * @param power          Target power level for strafe movement
     */
    public ForwardSensePhase(double targetDistance, double power, boolean strafe, double leadDistance, double backOff) {
        this.targetDistance = targetDistance;
        this.power = power;
        this.strafe = strafe;
        this.backOff = backOff;
        this.leadDistance = leadDistance;
    }

    /**
     * This method processes one op-mode process call with the intent of executing the forward phase
     *
     * @param robot The robot configuration (provided by the op-mode class)
     * @return True if the forward phase is complete, false if there's more to do.
     */
    @Override
    public boolean process(RobotHardware robot, Telemetry telemetry) {
        boolean isComplete = false;

        if (!isInitialized) {
            setMotors(robot, power);
            isInitialized = true;
        }

        double distance;
        if (!strafe) {
            distance = robot.getNearestObject();
        } else {
            if (power > 0) {
                distance = robot.getDistOR();
            } else {
                distance = robot.getDistOL();
            }
        }

        if (distance <= targetDistance + leadDistance) {
            setMotors(robot, power - ((leadDistance + targetDistance - distance) * (power * backOff / leadDistance)));
        }

        if (distance <= targetDistance) {
            robot.leftFront.setPower(0.0f);
            robot.rightFront.setPower(0.0f);
            robot.leftBack.setPower(0.0f);
            robot.rightBack.setPower(0.0f);
            isComplete = true;
        }

        return isComplete;
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
