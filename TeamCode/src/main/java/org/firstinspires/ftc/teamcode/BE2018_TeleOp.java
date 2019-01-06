package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="BE2018_TeleOp", group="Iterative Opmode")
public class BE2018_TeleOp extends OpMode
{
    private double direction = 1;
    private double power = 1;

    RobotHardware robot = new RobotHardware();

    public void init() {
        telemetry.addData("Status", "BE2018 TeleOp Initialized");
        robot.init(hardwareMap);

    }

    /*@Override
    public void init_loop() {
    }*/

    /*@Override
    public void start() {
    }*/

    public void loop() {

        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        boolean buttonYdone = true;

        // Driving  //

        if(gamepad1.x){
            direction *= -1;
        }

        if(gamepad1.y){
            if(power == 1){
                power = 0.3;
            }
            else{
                power = 1;
            }
            while (buttonYdone){
                if(!gamepad1.y){
                    buttonYdone = false;
                }
            }
        }

        robot.leftFront.setPower((y - x) * direction * power);
        robot.rightFront.setPower(-(y + x) * direction * power);
        robot.leftBack.setPower((y + x) * direction * power);
        robot.rightBack.setPower((x - y) * direction *power);

        if(gamepad1.right_bumper){
            robot.leftFront.setPower(-1 * direction * power);
            robot.rightFront.setPower(-1* direction * power);
            robot.leftBack.setPower(-1 * direction * power);
            robot.rightBack.setPower(-1 * direction *power);
        }
        else if(gamepad1.left_bumper){
            robot.leftFront.setPower(1 * direction * power);
            robot.rightFront.setPower(1* direction * power);
            robot.leftBack.setPower(1 * direction * power);
            robot.rightBack.setPower(1 * direction *power);
        }




        // Linear Actuator //
        double hook;

        if(gamepad1.dpad_up){
            robot.hook.setPower(0.5);
            hook = -0.5;
        }
        else if(gamepad1.dpad_down){
            robot.hook.setPower(-0.5);
            hook = -0.5;
        }
        else{
            robot.hook.setPower(0);
            hook = 0;
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
