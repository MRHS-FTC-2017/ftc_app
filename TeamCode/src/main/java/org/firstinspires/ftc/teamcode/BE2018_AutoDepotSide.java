package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.phases.AutonomousPhase;
import org.firstinspires.ftc.teamcode.phases.DoNothingPhase;
import org.firstinspires.ftc.teamcode.phases.DropRobotPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardColorDepotPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardDurationPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardSensePhase;
import org.firstinspires.ftc.teamcode.phases.MarkerDropPhase;

import java.util.LinkedList;

@Autonomous(name="BE2018_AutoDepotSide", group="Iterative Opmode")
public class BE2018_AutoDepotSide extends AutoAbstract {

    @Override
    void setPhases(LinkedList<AutonomousPhase> phaseList) {
        phaseList.add(new DropRobotPhase());

        // escape latch
        phaseList.add(new ForwardDurationPhase(100, 0.28, true));
        phaseList.add(new ForwardDurationPhase(100, 0.28, false));
        phaseList.add(new ForwardDurationPhase(100, -0.28, true));

        // forward to minerals
        phaseList.add(new ForwardSensePhase(3.75, 1, false, 15, 0.98));

        // strafe left towards leftmost mineral
        phaseList.add(new ForwardDurationPhase(200, 1, true));

        // strafe to leftmost mineral
        phaseList.add(new ForwardSensePhase(5, 0.25,true, 0, 0));

        //strafe past leftmost mineral
        phaseList.add(new ForwardDurationPhase(200, 0.5, true));

        // strafe to select gold mineral
        phaseList.add(new ForwardColorDepotPhase(-0.28, true,60,10, 10000, 9));

        // Drop Marker
        phaseList.add(new MarkerDropPhase(0.87, true));

        // Do Nothing
        phaseList.add(new DoNothingPhase());
    }
}
