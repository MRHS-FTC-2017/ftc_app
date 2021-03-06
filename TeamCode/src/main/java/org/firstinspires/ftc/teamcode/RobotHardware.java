package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.phases.AutonomousPhase;

public class RobotHardware {

    private HardwareMap hwMap = null;

    public DcMotor leftFront = null;
    public DcMotor rightFront= null;
    public DcMotor leftBack = null;
    public DcMotor rightBack = null;
    public DcMotor hook = null;
    public DcMotor collector = null;
    public DcMotor xRail = null;

    public ColorSensor colorFront = null;

    public DistanceSensor distanceOuterLeft = null;
    public DistanceSensor distanceInnerLeft = null;
    public DistanceSensor distanceInnerRight = null;
    public DistanceSensor distanceOuterRight = null;

    int colorFrontHue;

    double distOL;
    double distIL;
    double distIR;
    double distOR;

    public RobotHardware() {}

    public void init(HardwareMap aHWMap) {

        hwMap = aHWMap;

        // Here we are obtaining the hardware mappings for the motors
        leftFront = hwMap.get(DcMotor.class, "leftFront");
        rightFront = hwMap.get(DcMotor.class, "rightFront");
        leftBack = hwMap.get(DcMotor.class, "leftBack");
        rightBack = hwMap.get(DcMotor.class, "rightBack");
        hook = hwMap.get(DcMotor.class, "hook");
        collector = hwMap.get(DcMotor.class, "collector");
        xRail = hwMap.get(DcMotor.class, "xRail");

        // Hardware Mappings for Color & Distance Sensors
        colorFront = hwMap.get(ColorSensor.class, "colorFront");
        distanceOuterLeft = hwMap.get(DistanceSensor.class, "distanceOuterLeft");
        distanceInnerLeft = hwMap.get(DistanceSensor.class, "distanceInnerLeft");
        distanceInnerRight = hwMap.get(DistanceSensor.class, "distanceInnerRight");
        distanceOuterRight = hwMap.get(DistanceSensor.class, "distanceOuterRight");
//
//        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        collector.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        hook.setDirection(DcMotor.Direction.REVERSE);
        collector.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    private void hsvOfColorSensor(ColorSensor sensor, float[] hsv) {
        int red = sensor.red();
        int green = sensor.green();
        int blue = sensor.blue();

        double denom = Math.sqrt(red*red + green*green + blue*blue);
        int redNorm = (int)(255 * red / denom);
        int greenNorm = (int)(255 * green / denom);
        int blueNorm = (int)(255 * blue / denom);

        Color.RGBToHSV(redNorm, greenNorm, blueNorm, hsv);
    }

    void computeTelemetry(Telemetry telemetry, AutonomousPhase phase){
        float[] hsv = new float[3];

        telemetry.addData("Phase", "%s", phase.getClass().getName());

        hsvOfColorSensor(colorFront, hsv);
        colorFrontHue = (int)hsv[0];
        telemetry.addData("Right HSV", "(%.2f, %.2f, %.2f)", hsv[0], hsv[1], hsv[2]);

        distOL = distanceOuterLeft.getDistance(DistanceUnit.INCH);
        distIL = distanceInnerLeft.getDistance(DistanceUnit.INCH);
        distIR = distanceInnerRight.getDistance(DistanceUnit.INCH);
        distOR = distanceOuterRight.getDistance(DistanceUnit.INCH);

        telemetry.addData("Distances", "(%.0f, %.0f, %.0f, %.0f)", distOL, distIL, distIR, distOR);

        telemetry.addData("Hook Position", "%d", hook.getCurrentPosition());


    }

    public int getColorFrontHue() {
        return colorFrontHue;
    }

    public double getNearestObject() {
        double distance = distOL;
        distance = (distIL < distance) ? distIL : distance;
        distance = (distIR < distance) ? distIR : distance;
        distance = (distOR < distance) ? distOR : distance;
        return distance;
    }

    public double getDistOL() {
        return distOL;
    }

    public double getDistOR() {
        return distOR;
    }

    public double getDistIL() { return  distIL; }

    public double getDistIR() { return distIR; }
}


