package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class BE2018_RobotHardware {

    private HardwareMap hwMap = null;

    public DcMotor leftFront = null;
    public DcMotor rightFront= null;
    public DcMotor leftBack = null;
    public DcMotor rightBack = null;
    public DcMotor hook = null;

    public static final int HOOK_POSITION_UP = 10000;

    public BE2018_RobotHardware() {}

    public void init(HardwareMap aHWMap) {

        hwMap = aHWMap;

        // Here we are obtaining the hardware mappings for the motors
        leftFront = hwMap.get(DcMotor.class, "leftFront");
        rightFront = hwMap.get(DcMotor.class, "rightFront");
        leftBack = hwMap.get(DcMotor.class, "leftBack");
        rightBack = hwMap.get(DcMotor.class, "rightBack");

        hook = hwMap.get(DcMotor.class, "hook");
        hook.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors


        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        hook.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hook.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }


}
