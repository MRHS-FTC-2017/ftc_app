package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="BE2018_Auto_ClaimSide", group="Iterative Opmode")
public class BE2018_Auto_ClaimSide extends BE2018_Auto_ABSTRACT {

    @Override
    public void runOpMode() {
        autonomousInit();
        actuatorDrop(robot.HOOK_POSITION_UP);
        actuatorDrop(0);
    }
}
