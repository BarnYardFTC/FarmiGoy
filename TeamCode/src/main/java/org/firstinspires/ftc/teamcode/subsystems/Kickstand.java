package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.BarnRobot;

public class Kickstand extends SubsystemBase{

    private Servo rightServo, leftServo;

    public final double MIN = 0.15;
    public final double MAX = 0.7;

    public Kickstand(){
        rightServo = BarnRobot.getInstance().hardware.rightKickStand;
        leftServo = BarnRobot.getInstance().hardware.leftKickStand;
        rightServo.setDirection(Servo.Direction.REVERSE);
        leftServo.setDirection(Servo.Direction.FORWARD);
        setPos(MIN);
    }

    private void setPos(double pos) {
        rightServo.setPosition(pos);
        leftServo.setPosition(pos);
    }

    public Command activateCommand() {
        return new InstantCommand(() -> setPos(MAX), this);
    }

    public Command deactivateCommand() {
        return new InstantCommand(() -> setPos(MIN), this);
    }
}
