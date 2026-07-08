package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.BarnRobot;

/** Unified class for Drivetrain and Mechanum. We don't need to separate them anymore because difficult tasks are handled by Pepe. */
public class MechanumDrivetrain extends SubsystemBase {
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightBack = null;

    private double spdX;
    private double spdY;
    private double spdTurn;
    private double speedModifier;

    private static final double SLOW_SPEED = 0.5;
    private static final double FAST_SPEED = 1.0;
    public MechanumDrivetrain(){
        try {
            this.leftFront = BarnRobot.getInstance().hardware.leftFrontDrivetrain;
            this.rightFront = BarnRobot.getInstance().hardware.rightFrontDrivetrain;
            this.leftBack = BarnRobot.getInstance().hardware.leftBackDrivetrain;
            this.rightBack = BarnRobot.getInstance().hardware.rightBackDrivetrain;
            initMotor(DcMotorSimple.Direction.REVERSE, leftFront);
            initMotor(DcMotorSimple.Direction.FORWARD, rightFront);
            initMotor(DcMotorSimple.Direction.REVERSE, leftBack);
            initMotor(DcMotorSimple.Direction.FORWARD, rightBack);
        } catch (Exception e) {
            BarnRobot.getInstance().telemetry.addData("couldn't find drivetrain motors", e);
        }

        try {
            initData();
        } catch (Exception e) {
            BarnRobot.getInstance().telemetry.addData("couldn't find init data", e);
        }
    }

    private void initMotor(DcMotorSimple.Direction direction, DcMotor motor) {
        motor.setDirection(direction);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    private void initData() {
        spdX = 0;
        spdY = 0;
        spdTurn = 0;
        activateFastMode();
    }

    public void translateSpeedToPower() {
        double lf = spdY + spdX + spdTurn;
        double lb = spdY - spdX + spdTurn;
        double rf = spdY - spdX - spdTurn;
        double rb = spdY + spdX - spdTurn;

        // Normalize powers if needed
        double maxPower = Math.max(Math.max(Math.abs(lf), Math.abs(lb)),
                Math.max(Math.abs(rf), Math.abs(rb)));
        if (maxPower > 1.0) {
            lf /= maxPower;
            lb /= maxPower;
            rf /= maxPower;
            rb /= maxPower;
        }

        // Apply speed modifier
        lf *= speedModifier;
        lb *= speedModifier;
        rf *= speedModifier;
        rb *= speedModifier;

        // Set motor powers
        leftFront.setPower(lf);
        leftBack.setPower(lb);
        rightFront.setPower(rf);
        rightBack.setPower(rb);
    }

    public void setSpeed(double spdX, double spdY, double spdTurn) {
        this.spdX = spdX;
        this.spdY = spdY;
        this.spdTurn = spdTurn;
    }

    public void driveNonFieldCentric(double x, double y, double turn) {
        setSpeed(x, y, turn);
        translateSpeedToPower();
    }

    public Command activateFastMode() {
        return new InstantCommand(() -> speedModifier = FAST_SPEED, this);
    }

    public Command activateSlowMode() {
        return new InstantCommand(() -> speedModifier = SLOW_SPEED, this);
    }

    public Command driveCommand(){
        return new RunCommand(
                () -> driveNonFieldCentric(
                        BarnRobot.getInstance().gamepadEx1.getLeftX(),
                        BarnRobot.getInstance().gamepadEx1.getLeftY(),
                        BarnRobot.getInstance().gamepadEx1.getRightX()
                )
        , this);
    }
}
