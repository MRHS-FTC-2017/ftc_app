package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

abstract class BE2018_RobotConfig_OpMode extends OpMode {
    DcMotor leftFront = null;
    DcMotor rightFront= null;
    DcMotor leftBack = null;
    DcMotor rightBack = null;
    DcMotor actuator = null;

    int ACTUATOR_POSITION_UP = 10285;

    void initRobotConfig() {

        // Here we are obtaining the hardware mappings for the motors
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        actuator = hardwareMap.get(DcMotor.class, "actuator");
        actuator.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors


        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        actuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        actuator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }


}
