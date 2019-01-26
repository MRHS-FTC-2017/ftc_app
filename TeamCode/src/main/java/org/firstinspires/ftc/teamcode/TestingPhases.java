package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.phases.AutonomousPhase;
import org.firstinspires.ftc.teamcode.phases.CheckColorPhase;
import org.firstinspires.ftc.teamcode.phases.DistanceSenceCenterPhase;
import org.firstinspires.ftc.teamcode.phases.DoNothingPhase;
import org.firstinspires.ftc.teamcode.phases.DropRobotPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardDurationPhase;
import org.firstinspires.ftc.teamcode.phases.ForwardSensePhase;

import java.util.LinkedList;

@Autonomous(name="TestingPhases", group="Iterative Opmode")
public class TestingPhases extends AutoAbstract {

    @Override
    void setPhases(LinkedList<AutonomousPhase> phaseList) {

//        //Drive to Samples
//        phaseList.add(new ForwardSensePhase(2, 1, false, 15, 0.98));
//
//        //Check Color
//        phaseList.add(new CheckColorPhase(60, 0, 10000));

        //Center
        phaseList.add(new DistanceSenceCenterPhase(3));

//        phaseList.add(new DistanceSenceCenterPhase(3));
        //End
        phaseList.add(new DoNothingPhase());
    }
}
