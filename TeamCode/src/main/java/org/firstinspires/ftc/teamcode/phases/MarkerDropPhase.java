package org.firstinspires.ftc.teamcode.phases;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;

public class MarkerDropPhase implements AutonomousPhase {

    private double position;
    private boolean forward;


    public MarkerDropPhase(double position, boolean forward) {
        this.position = position;
        this.forward = forward;
    }

    /**
     * This method processes one op-mode process call with the intent of executing the forward phase
     *
     * @param robot The robot configuration (provided by the op-mode class)
     * @return True if the forward phase is complete, false if there's more to do.
     */
    @Override
    public boolean process(RobotHardware robot, Telemetry telemetry) {

        robot.arm.setPosition(position);
        if (forward) {
            robot.arm.setDirection(Servo.Direction.FORWARD);
        }
        else {
            robot.arm.setDirection(Servo.Direction.REVERSE);
        }

        return true;
    }
}
