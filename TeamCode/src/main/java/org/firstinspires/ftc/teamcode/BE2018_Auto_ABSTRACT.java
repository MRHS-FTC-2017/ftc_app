package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

@Autonomous(name="BE2018_Autonomous", group="Iterative Opmode")
public class BE2018_Autonomous extends OpMode
{
    private DcMotor leftFront = null;
    private DcMotor rightFront= null;
    private DcMotor leftBack = null;
    private DcMotor rightBack = null;
    private DcMotor actuator = null;

    private ModernRoboticsI2cColorSensor colorSensor;


    @Override
    public void init() {
        telemetry.addData("Status", "BE2018 Autonomous Initialized");

        // here we are obtaining the hardware mappings for the motors

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        actuator = hardwareMap.get(DcMotor.class, "actuator");

        colorSensor = hardwareMap.get(ModernRoboticsI2cColorSensor.class, "i2cColorSensor");
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

        int red = colorSensor.red();
        int green = colorSensor.green();
        int blue = colorSensor.blue();

        telemetry.addData("Colors", "RGB (%d, %d, %d)", red, green, blue);

        NormalizedRGBA normalizedColors = colorSensor.getNormalizedColors();
        float normalizedRed = normalizedColors.red;
        float normalizedGreen = normalizedColors.green;
        float normalizedBlue = normalizedColors.blue;

        telemetry.addData("Normalized Colors", "RGB (%f, %f, %f)",
                normalizedRed, normalizedGreen, normalizedBlue);
    }

    @Override
    public void stop() {
    }
}
