package org.firstinspires.ftc.teamcode.subsystems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.util.InterpLUT;

import org.firstinspires.ftc.teamcode.BarnRobot;

@Configurable
public class Hood extends SubsystemBase {
    public static double DEFAULT_POS = 0.8;
    private Servo servo;
    private InterpLUT interpLUT;

    private final double MAX = 0.95;
    private final double MIN = 0.35;


    public Hood(){
        initInterpLUT();

        servo = BarnRobot.getInstance().hardware.hoodServo;
        servo.setDirection(Servo.Direction.REVERSE);
        servo.setPosition(DEFAULT_POS);

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

    private void initInterpLUT() {
        interpLUT = new InterpLUT();

        interpLUT.add(0.05, 0.45);
        interpLUT.add(0.87, 0.67);
        interpLUT.add(1.15,0.73);
        interpLUT.add(1.27,0.93);
        interpLUT.add(1.5,0.93);
        interpLUT.add(1.75,0.93);
        interpLUT.add(2,0.95);

        interpLUT.createLUT();
    }

    public void hoodPositionDependsDistance(double distance) {
        if (distance < 0.03) {
            distance = 0.1;
        }

        double position = interpLUT.get(distance);

        servo.setPosition(position);
    }

    public double getPosition(){
        return servo.getPosition();

    }

    public void setHoodPositionDependsDistance() {
        hoodPositionDependsDistance(BarnRobot.getInstance().limelight.getGoalDistance());
    }

    public Command autoHoodCommand() {
        return new RunCommand(() -> setHoodPositionDependsDistance(), this);
    }

    public Command lowerCommand(){

        return new InstantCommand(()-> lower(),this);
    }
    public Command raiseCommand(){

        return new InstantCommand(()-> raise(), this);

    }

    public Command setPosCommand(double pos){
        return new InstantCommand(()-> servo.setPosition(pos), this);
    }
}

