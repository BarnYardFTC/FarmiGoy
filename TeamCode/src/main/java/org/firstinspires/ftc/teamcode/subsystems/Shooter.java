package org.firstinspires.ftc.teamcode.subsystems;


import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.BarnRobot;

@Configurable
public class Shooter extends SubsystemBase {

    private static final double TICKS_PER_REVOLUTION = 28;
    private static final double IDEAL_RPM = 3030;

    private static final double DETLA_TIME = 100;

    private double kV, kS, kP;

    private DcMotorEx shooterLeft;
    private DcMotorEx shooterRight;

    private double lastTime;

    private int lastPosition;
    private double rpm;


    public Shooter() {
        
        shooterLeft = BarnRobot.getInstance().hardware.shooterMotorLeft;
        shooterRight = BarnRobot.getInstance().hardware.shooterMotorRight;


        shooterRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterRight.setDirection(DcMotorSimple.Direction.FORWARD);
        shooterLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        shooterLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        shooterRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        lastTime = System.currentTimeMillis();
        lastPosition = shooterLeft.getCurrentPosition();
        rpm = 0;
    }

    public void setMotorPower(double power) {
        shooterLeft.setPower(power);
        shooterRight.setPower(power);
    }

    public void updateRPM() {
        int currentPosition = shooterRight.getCurrentPosition();
        double currentTime = System.currentTimeMillis();

        double deltaTime = currentTime - lastTime;
        int deltaPosition = currentPosition - lastPosition;

        if(deltaTime >= DETLA_TIME) {
            rpm = (deltaPosition * 60 * 1000) / (TICKS_PER_REVOLUTION * deltaTime);

            lastPosition = currentPosition;
            lastTime = currentTime;
        }
    }

    public double getRPM(){
        return rpm;
    }



//    private void updateDriver() {
//
//        double shooterRange = MAX_SHOOTER_VELOCITY - MIN_SHOOTER_VELOCITY;
//
//        targetShooterVelocity = MIN_SHOOTER_VELOCITY + ((shooterRange / (SHOOTER_VELOCITY_STAGES - 1)) * (currentShooterVelocityStage - 1));
//
//        if (shooterEnabled) {
//            shooterLeft.setVelocity(targetShooterVelocity);
//            shooterRight.setVelocity(targetShooterVelocity);
//        } else {
//            shooterLeft.setVelocity(0);
//            shooterRight.setVelocity(0);
//        }
//    }

    /*private void telemetryUpdate() {

        telemetry.addData("Shooter Enabled", shooterEnabled);
        telemetry.addLine("Shooter Velocity Stage: " + currentShooterVelocityStage + "/" + SHOOTER_VELOCITY_STAGES);
        telemetry.addData("Target Shooter Velocity", targetShooterVelocity);

        telemetry.update();
    }*/
}