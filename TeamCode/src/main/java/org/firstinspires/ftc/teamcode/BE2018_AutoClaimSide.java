package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.phases.AutonomousPhase;
import org.firstinspires.ftc.teamcode.phases.DoNothingPhase;
import org.firstinspires.ftc.teamcode.phases.DropRobotPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardColorPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardDurationPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardSensePhase;

import java.util.LinkedList;

@Autonomous(name="BE2018_AutoClaimSide", group="Iterative Opmode")
public class BE2018_AutoClaimSide extends AutoAbstract {

    @Override
    void setPhases(LinkedList<AutonomousPhase> phaseList) {
        phaseList.add(new DropRobotPhase());
        phaseList.add(new ForwardDurationPhase(100, 0.25, true));
        phaseList.add(new ForwardDurationPhase(100, 0.25, false));
        phaseList.add(new ForwardDurationPhase(100, -0.25, true));
        phaseList.add(new ForwardSensePhase(3.75, 0.5, false, 20, 0.75));
        phaseList.add(new ForwardSensePhase(3.75, 0.5,true, 0, 0));
        phaseList.add(new ForwardColorPhase(-0.15, true,40,10, 10000));
        phaseList.add(new ForwardDurationPhase(1000, 0.5, false));
        phaseList.add(new DoNothingPhase());
    }
}
