package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.BarnRobot;

public class Hood extends SubsystemBase {

    private Servo servo;


    private final double MAX = 0.95;
    private final double MIN = 0.35;


    public Hood(){
        servo = BarnRobot.getInstance().hardware.hoodServo;
        servo.setDirection(Servo.Direction.REVERSE);
        servo.setPosition(MAX);


    }
    private void lower(){
        if(servo.getPosition()-0.1>=MIN){
            servo.setPosition(servo.getPosition()-0.1);
        }
    }

    private void raise(){
        if(servo.getPosition()+0.1<=MAX){
            servo.setPosition(servo.getPosition()+0.1);
        }
    }

    public double getPosition(){
        return servo.getPosition();

    }
    public Command lowerCommand(){

        return new InstantCommand(()-> lower(),this);
    }
    public Command raiseCommand(){

        return new InstantCommand(()-> raise(), this);

    }

}

