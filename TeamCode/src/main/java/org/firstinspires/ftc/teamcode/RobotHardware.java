package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class RobotHardware {

    private HardwareMap hwMap = null;

    public DcMotor leftFront = null;
    public DcMotor rightFront= null;
    public DcMotor leftBack = null;
    public DcMotor rightBack = null;
    public DcMotor hook = null;

    public ColorSensor colorRight = null;
    public ColorSensor colorLeft = null;

    public DistanceSensor distanceOuterLeft = null;
    public DistanceSensor distanceInnerLeft = null;
    public DistanceSensor distanceInnerRight = null;
    public DistanceSensor distanceOuterRight = null;

    public RobotHardware() {}

    public void init(HardwareMap aHWMap) {

        hwMap = aHWMap;

        // Here we are obtaining the hardware mappings for the motors
        leftFront = hwMap.get(DcMotor.class, "leftFront");
        rightFront = hwMap.get(DcMotor.class, "rightFront");
        leftBack = hwMap.get(DcMotor.class, "leftBack");
        rightBack = hwMap.get(DcMotor.class, "rightBack");
        hook = hwMap.get(DcMotor.class, "hook");

        // Hardware Mappings for Color & Distance Sensors
        colorRight = hwMap.get(ColorSensor.class, "colorRight");
        colorLeft = hwMap.get(ColorSensor.class, "colorLeft");
        distanceOuterLeft = hwMap.get(DistanceSensor.class, "distanceOuterLeft");
        distanceInnerLeft = hwMap.get(DistanceSensor.class, "distanceInnerLeft");
        distanceInnerRight = hwMap.get(DistanceSensor.class, "distanceInnerRight");
        distanceOuterRight = hwMap.get(DistanceSensor.class, "distanceOuterRight");

//        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        hook.setDirection(DcMotor.Direction.REVERSE);
        hook.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hook.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    public void emitTelemetry(Telemetry telemetry){

        //Right Color Sensor's Telemetry Data
        int redRight = colorRight.red();
        int greenRight = colorRight.green();
        int blueRight = colorRight.blue();

        telemetry.addData("Right Colors", "RGB (%d, %d, %d)", redRight, greenRight, blueRight);

        //Left Color Sensor's Telemetry Data
        int redLeft = colorLeft.red();
        int greenLeft = colorLeft.green();
        int blueLeft = colorLeft.blue();

        telemetry.addData("Left Colors", "RGB (%d, %d, %d)", redLeft, greenLeft, blueLeft);

        double distOL = distanceOuterLeft.getDistance(DistanceUnit.INCH);
        double distIL = distanceInnerLeft.getDistance(DistanceUnit.INCH);
        double distIR = distanceInnerRight.getDistance(DistanceUnit.INCH);
        double distOR = distanceOuterRight.getDistance(DistanceUnit.INCH);

        telemetry.addData("Distances", "OL|IL|IR|OR (%f, %f, %f, %f)", distOL, distIL, distIR, distOR);

        telemetry.addData("Hook Position", "%d", hook.getCurrentPosition());

    }
}
