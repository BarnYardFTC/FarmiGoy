package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.BarnRobot;

public class Intake extends SubsystemBase {
    private DcMotor motor;

    public Intake() {
        motor = BarnRobot.getInstance().hardware.intakeMotor;
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public Command activateCommand(){
        return new InstantCommand(() -> motor.setPower(1), this);
    }

    public Command disableCommand(){
        return new InstantCommand(() -> motor.setPower(1), this);
    }
}
