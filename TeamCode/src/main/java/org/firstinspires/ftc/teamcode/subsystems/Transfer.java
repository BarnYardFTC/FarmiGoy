package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.BarnRobot;

public class Transfer extends SubsystemBase {
    private DcMotor motor;


    public Transfer() {
        motor = BarnRobot.getInstance().hardware.transfer;
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor.setDirection(DcMotorSimple.Direction.FORWARD);

    }

    private void activate() {

        motor.setPower(1);

    }

    private void disable() {

        motor.setPower(0);

    }
    private void reverse() {
        motor.setPower(-1);
    }

    public Command activateCommand(){
        return new InstantCommand(this::activate);
    }

    public Command disableCommand(){
        return new InstantCommand(this::disable);
    }

    public Command reversCommand() {
        return new InstantCommand(this::reverse);
    }
}
