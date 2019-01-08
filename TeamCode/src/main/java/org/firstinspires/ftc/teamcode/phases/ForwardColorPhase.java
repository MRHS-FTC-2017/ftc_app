package org.firstinspires.ftc.teamcode.phases;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;

public class ForwardColorPhase implements AutonomousPhase {


    private boolean isInitialized = false;
    private double targetDistance;
    private double power;
    private boolean strafe;
    private double hueMax;
    private double hueMin;

    /**
     * This instantiates the phase with a given target position (positive is forward (or left in strafe mode)) and power
     *
     * @param targetDistance Distance from object at which the robot will stop
     * @param power          Target power level for strafe movement
     */
    public ForwardColorPhase(double targetDistance, double power, boolean strafe, double hueMax, double hueMin) {
        this.targetDistance = targetDistance;
        this.power = power;
        this.strafe = strafe;
        this.hueMax = hueMax;
        this.hueMin = hueMin;
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
                distance = robot.getDistOL();
            } else {
                distance = robot.getDistOR();
            }
        }

        if (distance < targetDistance) {
            backUpMotors(robot);
        }
        else if (distance >= targetDistance){
            setMotors(robot, power);
        }

        if ((robot.getColorLeftHue() <= hueMax && robot.getColorLeftHue() >= hueMin) ||
                (robot.getColorRightHue() <= hueMin && robot.getColorRightHue() >= hueMin)){
            setMotors(robot, 0);
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

    private void backUpMotors(RobotHardware robot) {
        double backUp = -0.25;
        initMotor(robot.leftFront, backUp);
        initMotor(robot.rightFront, backUp);
        initMotor(robot.leftBack, backUp);
        initMotor(robot.rightBack, backUp);
    }
}
