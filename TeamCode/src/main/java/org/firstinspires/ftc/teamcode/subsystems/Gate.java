package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class Gate extends SubsystemBase {

    private Servo rightServo, leftServo;

    private boolean isOpen = false;
    private final double MIN = 0.88;

    private final double MAX = 1;


    public Gate(){
        rightServo = hardwareMap.get(Servo.class, "rightGate");
        leftServo = hardwareMap.get(Servo.class,"leftGate");
        rightServo.setDirection(Servo.Direction.FORWARD);
        leftServo.setDirection(Servo.Direction.REVERSE);
        //call function to close gate


    }
    private void setPosition(double pos){

        rightServo.setPosition(pos);
        leftServo.setPosition(pos);
    }
    private void close(){
        setPosition(MIN);
        isOpen = false;
    }
    private void open() {
        setPosition(MAX);
        isOpen = true;
    }
    public boolean isOpen(){return isOpen;}
//    public void displayTelemetry(){
//
//
//
//
//    }
    public Command openCommand(){

        return new InstantCommand(()-> open(),this);
    }
    public Command closeCommand(){

        return new InstantCommand(()-> close(), this);

    }





}
