package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.phases.AutonomousPhase;
import org.firstinspires.ftc.teamcode.phases.DoNothingPhase;
import org.firstinspires.ftc.teamcode.phases.DropRobotPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardColorDepotPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardDurationPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardSensePhase;
import org.firstinspires.ftc.teamcode.phases.TurnDurationPhase;

import java.util.LinkedList;

@Autonomous(name="BE2018_AutoCraterSide_ParkOnly", group="Iterative Opmode")
public class BE2018_AutoCraterSide_ParkOnly extends AutoAbstract {

    @Override
    void setPhases(LinkedList<AutonomousPhase> phaseList) {
        phaseList.add(new DropRobotPhase());
        phaseList.add(new ForwardDurationPhase(100, 0.28, true));
        phaseList.add(new ForwardDurationPhase(100, 0.28, false));
        phaseList.add(new ForwardDurationPhase(100, -0.20, true));
        phaseList.add(new ForwardSensePhase(3.85, 1, false, 15, 0.90));
        phaseList.add(new ForwardDurationPhase(200, 1, true));
        phaseList.add(new ForwardSensePhase(5, 0.25,true, 0, 0));
        phaseList.add(new ForwardDurationPhase(100, 0.5, true));
        phaseList.add(new ForwardColorDepotPhase(-0.28, true,50,10, 10000, 9));
        phaseList.add(new DoNothingPhase());
    }
}
