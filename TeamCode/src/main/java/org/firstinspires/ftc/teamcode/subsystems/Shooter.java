package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.BarnRobot;

public class Shooter extends SubsystemBase {

    private static final int MIN_SHOOTER_VELOCITY = 800;
    private static final int MAX_SHOOTER_VELOCITY = 1300;

    private static final int SHOOTER_VELOCITY_STAGES = 10;

    private DcMotorEx shooterLeft;
    private DcMotorEx shooterRight;

    private GamepadEx gamepad;

    private boolean shooterEnabled = false;

    private int currentShooterVelocityStage = 1;

    private double targetShooterVelocity;

    public Shooter() {

        shooterLeft = BarnRobot.getInstance().hardware.shooterMotorLeft;
        shooterRight = BarnRobot.getInstance().hardware.shooterMotorRight;

        shooterRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterRight.setDirection(DcMotorSimple.Direction.FORWARD);
        shooterLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        shooterLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        shooterRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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