package org.firstinspires.ftc.teamcode.phases;

import android.util.Pair;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotHardware;

public class DropRobotPhase implements AutonomousPhase {

    public static final int HOOK_POSITION_UP = 7800;
    private boolean isInitialized = false;

    /**
     * This method processes one op-mode process call with the intent of executing the drop-robot phase
     *
     * @param robot The robot configuration (provided by the op-mode class)
     * @return True if the drop-robot phase is complete, false if there's more to do.
     */
    @Override
    public Pair<Boolean,AutonomousPhase> process(RobotHardware robot, Telemetry telemetry) {
        boolean isComplete = false;

        if (!isInitialized) {
            robot.hook.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.hook.setTargetPosition(HOOK_POSITION_UP);
            robot.hook.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.hook.setPower(1.0);
            isInitialized = true;
        }

        if (!robot.hook.isBusy()) {
            robot.hook.setPower(0);
            isComplete = true;
        }

        return new Pair<>(isComplete, null);
    }
}
