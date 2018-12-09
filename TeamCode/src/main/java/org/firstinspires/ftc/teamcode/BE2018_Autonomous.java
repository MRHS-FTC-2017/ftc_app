package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="BE2018_Autonomous", group="Iterative Opmode")
public class BE2018_Autonomous extends OpMode
{
    private DcMotor dcMotor0 = null;
    private DcMotor dcMotor1 = null;
    private DcMotor dcMotor2 = null;
    private DcMotor dcMotor3 = null;

    @Override
    public void init() {
        telemetry.addData("Status", "BE2018 Autonomous Initialized");

        dcMotor0 = hardwareMap.get(DcMotor.class, "dcMotor0");
        dcMotor1 = hardwareMap.get(DcMotor.class, "dcMotor1");
        dcMotor2 = hardwareMap.get(DcMotor.class, "dcMotor2");
        dcMotor3 = hardwareMap.get(DcMotor.class, "dcMotor3");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "BE2018 Autonomous Running");
    }

    @Override
    public void stop() {
    }
}
