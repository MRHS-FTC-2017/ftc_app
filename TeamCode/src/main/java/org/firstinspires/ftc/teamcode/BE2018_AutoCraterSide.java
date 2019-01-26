package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.phases.AutonomousPhase;
import org.firstinspires.ftc.teamcode.phases.DoNothingPhase;
import org.firstinspires.ftc.teamcode.phases.DropRobotPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardColorCraterPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardDurationPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardSensePhase;

import java.util.LinkedList;

@Autonomous(name="BE2018_AutoCraterSide", group="Iterative Opmode")
public class BE2018_AutoCraterSide extends AutoAbstract {

    @Override
    void setPhases(LinkedList<AutonomousPhase> phaseList) {
        phaseList.add(new DropRobotPhase());

        // escape latch
        phaseList.add(new ForwardDurationPhase(60, 0.4, true));
        phaseList.add(new ForwardDurationPhase(60, 0.4, false));
        phaseList.add(new ForwardDurationPhase(60, -0.4, true));

        // forward to minerals
        phaseList.add(new ForwardSensePhase(3.55, 1, false, 15, 0.98));

        // strafe left toward leftmost mineral
        phaseList.add(new ForwardDurationPhase(200, 1, true));
        //strafe to left most mineral
        phaseList.add(new ForwardSensePhase(5, 0.30,true, 0, 0));
        //strafe past leftmost mineral
        phaseList.add(new ForwardDurationPhase(10, 0.5, true));
        //straft to gold mineral
        phaseList.add(new ForwardColorCraterPhase(-0.30, true,50,10, 10000, 9));
        //push gold mineral
        phaseList.add(new ForwardDurationPhase(150, 1, false));
        phaseList.add(new ForwardDurationPhase(300, -1, false));
        phaseList.add(new ForwardDurationPhase(550, 1, true));
        phaseList.add(new DoNothingPhase());
    }
}
