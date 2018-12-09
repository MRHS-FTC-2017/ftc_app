package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="BE2018_TeleOp", group="Iterative Opmode")
public class BE2018_TeleOp extends OpMode
{
    private DcMotor dcMotor0 = null;
    private DcMotor dcMotor1 = null;
    private DcMotor dcMotor2 = null;
    private DcMotor dcMotor3 = null;

    @Override
    public void init() {
        telemetry.addData("Status", "BE2018 TeleOp Initialized");

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
        double forwardPower = gamepad1.left_stick_y;
        double rightPower = -gamepad1.left_stick_x;

        dcMotor0.setPower(forwardPower);
        dcMotor1.setPower(forwardPower);
        dcMotor2.setPower(rightPower);
        dcMotor3.setPower(rightPower);

        telemetry.addData("Status", "BE2018 TeleOp Running");
        telemetry.addData("Motors", "forward (%.2f), right (%.2f)", forwardPower, rightPower);
    }

    @Override
    public void stop() {
    }
}
