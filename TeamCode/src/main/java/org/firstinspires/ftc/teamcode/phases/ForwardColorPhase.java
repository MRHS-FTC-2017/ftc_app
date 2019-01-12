package org.firstinspires.ftc.teamcode.phases;

import android.util.Pair;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;

public class ForwardColorPhase implements AutonomousPhase {


    private boolean isInitialized = false;
    private double power;
    private boolean strafe;
    private double hueMax;
    private double hueMin;
    private long maxDuration;
    private double cutOff;
    boolean seen;
    private ElapsedTime runtime = new ElapsedTime();
    private AutonomousPhase injectedPhase;
    private int elementsPassed;


    /**
     * This phase will move the robot until it sees a color in the specific hue range.
     *
     * @param power  Power to assign for movement (forrward and left (strafe) are positive)
     * @param strafe Set to true if robot should strafe
     * @param hueMax Maximum hue to recognize as target color
     * @param hueMin Minimum hue to recognize as target color
     */
    public ForwardColorPhase(double power, boolean strafe, double hueMax, double hueMin, long maxDuration, double cutOff) {
        this.power = power;
        this.strafe = strafe;
        this.hueMax = hueMax;
        this.hueMin = hueMin;
        this.maxDuration = maxDuration;
        this.cutOff = cutOff;
        this.seen = false;
        this.injectedPhase = null;
        this.elementsPassed = 0;
    }

    /**
     * This method processes one op-mode process call with the intent of executing the forward phase
     *
     * @param robot The robot configuration (provided by the op-mode class)
     * @return True if the forward phase is complete, false if there's more to do.
     */
    @Override
    public Pair<Boolean,AutonomousPhase> process(RobotHardware robot, Telemetry telemetry) {
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

        if (robot.getColorLeftHue() <= hueMax && robot.getColorLeftHue() >= hueMin) {
            setMotors(robot, 0);
            isComplete = true;
        }

        if (runtime.milliseconds() >= maxDuration) {
            setMotors(robot,0);
            isComplete = true;
        }

        if (isComplete) {
            if (elementsPassed == 1) {
                injectedPhase = new ForwardDurationPhase(250, 1, true,true);
            }
            else if (elementsPassed == 2) {
                injectedPhase = new ForwardDurationPhase(100, 1, true, false);
            }
            else if (elementsPassed == 3) {
                injectedPhase = new TurnDurationPhase(100, 0.60);
            }
        }

        return new Pair<>(isComplete, injectedPhase);
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
