package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.phases.AutonomousPhase;
import org.firstinspires.ftc.teamcode.phases.DoNothingPhase;
import org.firstinspires.ftc.teamcode.phases.DropRobotPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardColorCraterAndClaimingPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardDurationPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardSensePhase;

import java.util.LinkedList;

@Autonomous(name="BE2018_AutoCraterSideAndClaiming", group="Iterative Opmode")
public class BE2018_AutoCraterSideAndClaiming extends AutoAbstract {

    @Override
    void setPhases(LinkedList<AutonomousPhase> phaseList) {
        phaseList.add(new DropRobotPhase());

        // escape latch
        phaseList.add(new ForwardDurationPhase(70, 0.30, true));
        phaseList.add(new ForwardDurationPhase(70, 0.30, false));
        phaseList.add(new ForwardDurationPhase(70, -0.30, true));

        // forward to minerals
        phaseList.add(new ForwardSensePhase(3.75, 1, false, 15, 0.90));

        // strafe left toward leftmost mineral
        phaseList.add(new ForwardDurationPhase(200, 0.5, true));
        //strafe to left most mineral
        phaseList.add(new ForwardSensePhase(5, 0.25,true, 0, 0));
        //strafe past leftmost mineral
        phaseList.add(new ForwardDurationPhase(10, 0.5, true));
        //straft to gold mineral
        phaseList.add(new ForwardColorCraterAndClaimingPhase(-0.28, true,50,10, 10000, 9));
        phaseList.add(new DoNothingPhase());
    }
}
