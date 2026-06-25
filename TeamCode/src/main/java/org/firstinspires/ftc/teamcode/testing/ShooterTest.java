package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class ShooterTest extends LinearOpMode {

    private DcMotor shooterRight;
    private DcMotor shooterLeft;

    private static final double TICKS_PER_REV = 28.0;

    @Override
    public void runOpMode() {

        shooterRight = hardwareMap.get(DcMotor.class, "shooterRight");
        shooterLeft = hardwareMap.get(DcMotor.class, "shooterLeft");

        shooterLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooterLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooterLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        shooterRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooterRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double power = 0.0;

        boolean lastLeftBumper = false;
        boolean lastRightBumper = false;

        int lastPosition = 0;
        long lastTime = System.nanoTime();

        double rpm = 0;

        waitForStart();

        while (opModeIsActive()) {

            // Edge detection
            if (gamepad1.right_bumper && !lastRightBumper) {
                power += 0.01;
            }

            if (gamepad1.left_bumper && !lastLeftBumper) {
                power -= 0.01;
            }

            power = Math.max(0.0, Math.min(1.0, power));

            lastLeftBumper = gamepad1.left_bumper;
            lastRightBumper = gamepad1.right_bumper;

            shooterRight.setPower(power);
            shooterLeft.setPower(power);

            // RPM calculation
            int currentPosition = shooterRight.getCurrentPosition();
            long currentTime = System.nanoTime();

            double deltaTime = (currentTime - lastTime) / 1e9;
            int deltaTicks = currentPosition - lastPosition;

            if (deltaTime >= 0.1) { // update RPM every 100ms
                rpm = (deltaTicks * 60.0) / (TICKS_PER_REV * deltaTime);

                lastPosition = currentPosition;
                lastTime = currentTime;
            }

            telemetry.addData("Power", "%.2f", power);
            telemetry.addData("Encoder", currentPosition);
            telemetry.addData("RPM", "%.1f", rpm);
            telemetry.update();
        }
    }
}