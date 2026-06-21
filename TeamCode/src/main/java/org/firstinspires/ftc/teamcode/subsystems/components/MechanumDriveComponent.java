package org.firstinspires.ftc.teamcode.subsystems.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.BarnRobot;

public class MechanumDriveComponent {


    private final DcMotorEx leftFront;
    private final DcMotorEx rightFront;
    private final DcMotorEx leftBack;
    private final DcMotorEx rightBack;

    private double spdX;
    private double spdY;
    private double spdTurn;

    private double speedModifier;

    private static final double SLOW_SPEED = 0.3;
    private static final double FAST_SPEED = 1.0;
    private static double turnModifier = 1;

    private static final double speedMultiplier = 1;
    private static final double turnMultiplier = 1;

    public MechanumDriveComponent(){
        this.leftFront = BarnRobot.getInstance().Hardware.leftFrontDriveTrain;
        this.rightFront = BarnRobot.getInstance().Hardware.rightFrontDriveTrain;
        this.leftBack = BarnRobot.getInstance().Hardware.leftBackDriveTrain;
        this.rightBack = BarnRobot.getInstance().Hardware.rightBackDriveTrain;

        initMotor(DcMotorSimple.Direction.REVERSE, leftFront);
        initMotor(DcMotorSimple.Direction.REVERSE, rightFront);
        initMotor(DcMotorSimple.Direction.REVERSE, leftBack);
        initMotor(DcMotorSimple.Direction.REVERSE, rightBack);

        initData();
    }

    private void initMotor(DcMotorSimple.Direction direction, DcMotor motor){
        motor.setDirection(direction);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    private void initData(){
        spdX = 0;
        spdY = 0;
        spdTurn = 0;
        activateFastMode();
    }

}
