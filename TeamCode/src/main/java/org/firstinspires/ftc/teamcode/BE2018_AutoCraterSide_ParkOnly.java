package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.phases.AutonomousPhase;
import org.firstinspires.ftc.teamcode.phases.DoNothingPhase;
import org.firstinspires.ftc.teamcode.phases.DropRobotPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardColorDepotPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardColorDepotWithParkingPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardDurationPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardSensePhase;
import org.firstinspires.ftc.teamcode.phases.TurnDurationPhase;

import java.util.LinkedList;

@Autonomous(name="BE2018_AutoCraterSide_ParkOnly", group="Iterative Opmode")
public class BE2018_AutoCraterSide_ParkOnly extends AutoAbstract {

    @Override
    void setPhases(LinkedList<AutonomousPhase> phaseList) {

        // escape latch
        phaseList.add(new ForwardDurationPhase(50, 0.40, true));
        phaseList.add(new ForwardDurationPhase(50, 0.40, false));
        phaseList.add(new ForwardDurationPhase(50, -0.40, true));

        // forward to minerals
        phaseList.add(new ForwardSensePhase(3.75, 1, false, 15, 0.98));

        // strafe left towards leftmost mineral
        phaseList.add(new ForwardDurationPhase(400, 0.5, true));

        // strafe to leftmost mineral
        phaseList.add(new ForwardSensePhase(5, 0.34,true, 0, 0));

        //strafe past leftmost mineral
        phaseList.add(new ForwardDurationPhase(200, 0.5, true));

        //adjust distance
        phaseList.add(new ForwardSensePhase(3, 0.25, false, 0,0));

        // strafe to select gold mineral
        phaseList.add(new ForwardColorDepotPhase(-0.30, true,60,10, 10000, 9));

        phaseList.add(new DoNothingPhase());
    }
}
