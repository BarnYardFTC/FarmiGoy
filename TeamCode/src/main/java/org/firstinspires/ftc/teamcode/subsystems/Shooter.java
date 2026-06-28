package org.firstinspires.ftc.teamcode.subsystems;


import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.BarnRobot;

@Configurable
public class Shooter extends SubsystemBase {

    private static final double TICKS_PER_REVOLUTION = 28;
    private static final int MAX_RPM = 3030;

    private static final double DETLA_TIME = 100;

    private double kV = 0.000174, kS = 0.09, kP = 0.00145;

    public static double DISTANCE_RANGE_1, DISTANCE_RANGE_2, DISTANCE_RANGE_3;
    public static int RPM_RANGE_1, RPM_RANGE_2, RPM_RANGE_3, RPM_RANGE_4 = MAX_RPM;

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
        shooterRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        shooterLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterLeft.setDirection(DcMotorSimple.Direction.REVERSE    );
        shooterLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

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

    public void setMotorRPM(int rpm) {
        // call it every loop pls

        double feedForward = kV * rpm + kS;
        double error = rpm - getRPM();
        double feedback = error * kP;

        setMotorPower(feedForward + feedback);
    }

    public void operateBasedOnDistance(double distance) {
        if (distance <= DISTANCE_RANGE_1 && distance > 0) {
            setMotorRPM(RPM_RANGE_1);
        } else if (distance <= DISTANCE_RANGE_2 && distance > DISTANCE_RANGE_1 ) {
            setMotorRPM(RPM_RANGE_2);
        } else if (distance <= DISTANCE_RANGE_3 && distance > DISTANCE_RANGE_2 ) {
            setMotorRPM(RPM_RANGE_3);
        } else if (distance > DISTANCE_RANGE_3) {
            setMotorRPM(RPM_RANGE_4);
        }
    }

    public RunCommand operateRangeOneCommand() {
        return new RunCommand(() -> operateBasedOnDistance(DISTANCE_RANGE_1));
    }
    public RunCommand operateRangeTwoCommand() {
        return new RunCommand(() -> operateBasedOnDistance(DISTANCE_RANGE_2));
    }
    public RunCommand operateRangeThreeCommand() {
        return new RunCommand(() -> operateBasedOnDistance(DISTANCE_RANGE_3));
    }
    public RunCommand operateRangeFourCommand() {
        return new RunCommand(() -> operateBasedOnDistance(DISTANCE_RANGE_3 + 1));
    }

}