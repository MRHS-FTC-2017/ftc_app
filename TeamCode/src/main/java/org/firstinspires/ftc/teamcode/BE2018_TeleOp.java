package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="BE2018_TeleOp", group="Iterative Opmode")
public class BE2018_TeleOp extends OpMode
{
    private double power = 1;
    private double direction = 1;
    private double lowPower = 0.25;
    private double highPower = 1;
    private boolean xButtonHeld = false;
    private boolean yButtonHeld = false;


    RobotHardware robot = new RobotHardware();

    public void init() {
        telemetry.addData("Status", "BE2018 TeleOp Initialized");
        robot.init(hardwareMap);

    }

    public void loop() {

        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;

        ////          Driving         ////

        // Slow Down
        if (gamepad1.x) {
            if (!xButtonHeld) {
                if (power == highPower) {
                    power = lowPower;
                }
                else {
                    power = highPower;
                }
                xButtonHeld = true;
            }
        }
        else {
            xButtonHeld = false;
        }

        // Direction Change
        if (gamepad1.y) {
            if (!yButtonHeld) {
                direction *= -1;
            }
            yButtonHeld = true;
        }
        else {
            yButtonHeld = false;
        }



        // Driving Power
        robot.leftFront.setPower((y + (x * direction)) * power);
        robot.rightFront.setPower(((-y * direction) + x) * power);
        robot.leftBack.setPower(((-y * direction) + x) * power);
        robot.rightBack.setPower((y + (x * direction)) * power);

        // Spinning
        if (gamepad1.left_bumper){
            robot.leftFront.setPower(-1 * power);
            robot.rightFront.setPower(1* power);
            robot.leftBack.setPower(-1 * power);
            robot.rightBack.setPower(1 * power);
        }
        else if (gamepad1.right_bumper){
            robot.leftFront.setPower(1 * power);
            robot.rightFront.setPower(-1* power);
            robot.leftBack.setPower(1 * power);
            robot.rightBack.setPower(-1 * power);
        }


        //hook

        double hook;

        if (gamepad1.dpad_up){
            robot.hook.setPower(0.5);
            hook = -0.5;
        }
        else if (gamepad1.dpad_down){
            robot.hook.setPower(-0.5);
            hook = -0.5;
        }
        else {
            robot.hook.setPower(0);
            hook = 0;
        }


        //collector
            robot.collector.setPower(gamepad2.right_stick_y);

        // X Rail
        if (gamepad2.dpad_up) {
            robot.xRail.setPower(1);
        }
        else if (gamepad2.dpad_down) {
            robot.xRail.setPower(-1);
        }
        else {
            robot.xRail.setPower(0);
        }



        // All For Telemetry //
        double righty = gamepad1.right_stick_y;
        double rightx = gamepad1.right_stick_x;
        double lefty = gamepad1.left_stick_y;
        double leftx = gamepad1.left_stick_x;

        telemetry.addData("Status", "BE2018 TeleOp Running");
        telemetry.addData("Joysticks", "Right Y:(%.2f), Right X:(%.2f), Left Y:(%.2f), Left X:(%.2f)", righty, rightx, lefty, leftx);
        telemetry.addData("Dpad", "Power:(%.2f)", hook);
        telemetry.addData("Actuator Position", robot.hook.getCurrentPosition());
        telemetry.addData("Actuator Mode", robot.hook.getMode().isPIDMode());
        telemetry.addData("Speed", "Power: (%.2f)", power);
        telemetry.addData("Y button", gamepad1.y);


    }

    /*@Override
    public void stop() {
    }*/
}
