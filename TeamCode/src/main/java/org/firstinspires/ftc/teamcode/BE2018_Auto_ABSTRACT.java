package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

public abstract class BE2018_Auto_ABSTRACT extends LinearOpMode
{

    private ModernRoboticsI2cColorSensor colorSensor;

    BE2018_RobotHardware robot = new BE2018_RobotHardware();

/*
    @Override
    public void loop() {

        actuatorDrop();


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
*/

    /////////////////////
    // Autonomous Methods
    /////////////////////

    void autonomousInit(){
        telemetry.addData("Status", "BE2018 Autonomous Initialized");
        robot.init(hardwareMap);
        colorSensor = hardwareMap.get(ModernRoboticsI2cColorSensor.class, "i2cColorSensor");
    }

    void actuatorDrop(int target){


        robot.hook.setTargetPosition(target);
        robot.hook.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.hook.setPower(0.5);

        while(robot.hook.isBusy()) {
            telemetry.addData("Hook Position", robot.hook.getCurrentPosition());
            telemetry.update();
        }

        robot.hook.setPower(0);

        robot.hook.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }



}
