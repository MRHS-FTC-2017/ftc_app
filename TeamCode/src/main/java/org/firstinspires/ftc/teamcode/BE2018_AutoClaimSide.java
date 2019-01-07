package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.phases.AutonomousPhase;
import org.firstinspires.ftc.teamcode.phases.DoNothingPhase;
import org.firstinspires.ftc.teamcode.phases.DropRobotPhase;
import org.firstinspires.ftc.teamcode.phases.StrafePhase;

import java.util.LinkedList;

@Autonomous(name="BE2018_AutoClaimSide", group="Iterative Opmode")
public class BE2018_AutoClaimSide extends AutoAbstract {

    @Override
    void setPhases(LinkedList<AutonomousPhase> phaseList) {
        phaseList.add(new DropRobotPhase());
        phaseList.add(new StrafePhase(360, 1.0));
        phaseList.add(new DoNothingPhase());
    }
}
