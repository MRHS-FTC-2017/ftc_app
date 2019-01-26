package org.firstinspires.ftc.teamcode.phases;

import android.util.Pair;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;
import java.util.LinkedList;


public class movePastDistanceSensors implements AutonomousPhase{

        private boolean isInitialized = false;
        private boolean strafe;
        private double power;
        private boolean isComplete;
        private boolean passed = false;

        public movePastDistanceSensors(double power, boolean strafe) {
            this.strafe = strafe;
            this.power = power;
        }



    @Override
    public Pair<Boolean, LinkedList<AutonomousPhase>> process(RobotHardware robot, Telemetry telemetry) {
            isComplete = false;

            if (!isInitialized) {
                initMotor(robot.rightBack, power);
                initMotor(robot.rightFront, power);
                initMotor(robot.leftBack, power);
                initMotor(robot.leftFront, power);
            }

            double distance = robot.getDistOR();

            if (distance <= 5 ) {
                passed = true;
            }

            if (distance > 10 && passed) {
                isComplete = true;
            }

        return new Pair<> (isComplete, null);
    }


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
