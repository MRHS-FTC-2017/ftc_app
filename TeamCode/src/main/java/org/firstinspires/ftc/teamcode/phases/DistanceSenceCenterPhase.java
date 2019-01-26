package org.firstinspires.ftc.teamcode.phases;

import android.util.Pair;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;

import java.util.LinkedList;

public class DistanceSenceCenterPhase implements AutonomousPhase {

    private double targetDistace;

    public DistanceSenceCenterPhase(double targetDistance) {
        this.targetDistace = targetDistance;
    }

    @Override
    public Pair<Boolean, LinkedList<AutonomousPhase>> process(RobotHardware robot, Telemetry telemetry) {
        boolean isComplete = false;
        double distIL = robot.getDistIL();
        double distIR = robot.getDistIR();


        if (distIR == targetDistace && distIL == targetDistace) {
            setMotors(robot, 0);
            isComplete = true;
        }

        else if (distIL > targetDistace && distIR < targetDistace){
            setMotors(robot, 0.05);
        }

        else if (distIR > targetDistace && distIL < targetDistace){
            setMotors(robot, -0.05);
        }

        return new Pair<Boolean, LinkedList<AutonomousPhase>>(isComplete, null);
    }


    private void initMotor(DcMotor motor, double power) {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setPower(power);
    }

    private void setMotors(RobotHardware robot, double power) {
        initMotor(robot.leftFront, power * -1);
        initMotor(robot.rightFront, power);
        initMotor(robot.leftBack, power);
        initMotor(robot.rightBack, power * -1);
    }

}
