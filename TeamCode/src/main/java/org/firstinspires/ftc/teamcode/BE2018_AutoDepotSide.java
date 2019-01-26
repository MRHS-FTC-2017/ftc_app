package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.sun.tools.javac.comp.Check;

import org.firstinspires.ftc.teamcode.phases.AutonomousPhase;
import org.firstinspires.ftc.teamcode.phases.CheckColorPhase;
import org.firstinspires.ftc.teamcode.phases.DistanceSenceCenterPhase;
import org.firstinspires.ftc.teamcode.phases.DoNothingPhase;
import org.firstinspires.ftc.teamcode.phases.DropRobotPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardDurationPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardSensePhase;
import org.firstinspires.ftc.teamcode.phases.MarkerDropPhase;
import org.firstinspires.ftc.teamcode.phases.TurnDurationPhase;

import java.util.LinkedList;

@Autonomous(name="BE2018_AutoDepotSide", group="Iterative Opmode")
public class BE2018_AutoDepotSide extends AutoAbstract {

    @Override
    void setPhases(LinkedList<AutonomousPhase> phaseList) {
        phaseList.add(new DropRobotPhase());

        // escape latch
        phaseList.add(new ForwardDurationPhase(100, 0.5, true));
        phaseList.add(new ForwardDurationPhase(100, 0.5, false));
        phaseList.add(new ForwardDurationPhase(100, -0.5, true));


        // forward to minerals
        phaseList.add(new ForwardSensePhase(3, 1, false, 15, 0.98));


        //center mineral
        phaseList.add(new DistanceSenceCenterPhase(3));

        //check if the center mineral is gold
        phaseList.add(new CheckColorPhase(70, 10, 100000));

        if(robot.getDecision()){
            phaseList.add(new ForwardDurationPhase(200, 1, true));
            phaseList.add(new ForwardDurationPhase(1200, 1, false));
        }
        else {

            // strafe left towards leftmost mineral
            phaseList.add(new ForwardDurationPhase(400, 0.5, true));

            // strafe to leftmost mineral
            phaseList.add(new ForwardSensePhase(5, 0.25, true, 0, 0));

            // check if left mineral is gold
            phaseList.add(new CheckColorPhase(60, 10, 1000));

            if(robot.getDecision()){
                phaseList.add(new TurnDurationPhase(200, 0.50));
                phaseList.add(new ForwardDurationPhase(1200, 1, false));
            }
        }

        // Drop Marker
        phaseList.add(new MarkerDropPhase());

        // Do Nothing
        phaseList.add(new DoNothingPhase());
    }
}
