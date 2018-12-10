package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

@Autonomous(name="BE2018_Autonomous", group="Iterative Opmode")
public class BE2018_Autonomous extends OpMode
{
    private DcMotor dcMotor0;
    private DcMotor dcMotor1;
    private DcMotor dcMotor2;
    private DcMotor dcMotor3;

    ModernRoboticsI2cColorSensor colorSensor;

    @Override
    public void init() {
        telemetry.addData("Status", "BE2018 Autonomous Initialized");

        dcMotor0 = hardwareMap.get(DcMotor.class, "dcMotor0");
        dcMotor1 = hardwareMap.get(DcMotor.class, "dcMotor1");
        dcMotor2 = hardwareMap.get(DcMotor.class, "dcMotor2");
        dcMotor3 = hardwareMap.get(DcMotor.class, "dcMotor3");

        colorSensor = hardwareMap.get(ModernRoboticsI2cColorSensor.class, "i2cColorSensor0");

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
        telemetry.addData("Colors", "RGB (%d, %d, %d)",
                colorSensor.red(), colorSensor.green(), colorSensor.blue());

        NormalizedRGBA normalizedColors = colorSensor.getNormalizedColors();
        telemetry.addData("Normalized Colors", "RGB (%f, %f, %f)",
                normalizedColors.red, normalizedColors.green, normalizedColors.blue);
    }

    @Override
    public void stop() {
    }
}
