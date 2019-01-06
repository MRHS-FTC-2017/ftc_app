package org.firstinspires.ftc.teamcode.phases;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;

public class DropRobotPhase implements AutonomousPhase {

    public static final int HOOK_POSITION_UP = 8000;
    private boolean isInitialized = false;

    /**
     * This method processes one op-mode process call with the intent of executing the drop-robot phase
     *
     * @param robot The robot configuration (provided by the op-mode class
     * @return True if the drop-robot phase is complete, false if there's more to do.
     */
    @Override
    public boolean process(RobotHardware robot, Telemetry telemetry) {
        boolean isComplete = false;

        if (!isInitialized) {
            robot.hook.setTargetPosition(HOOK_POSITION_UP);
            robot.hook.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.hook.setPower(0.5);
            isInitialized = true;
        }

        if (!robot.hook.isBusy()) {
            robot.hook.setPower(0);
            isComplete = true;
        }

        return isComplete;
    }
}
