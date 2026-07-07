package org.firstinspires.ftc.teamcode.subsystems;

import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.subsystems.components.MechanumDriveComponent;

public class Drivetrain extends SubsystemBase {

    public final MechanumDriveComponent mechanumDriveComponent;

    public Drivetrain(){
        mechanumDriveComponent = new MechanumDriveComponent();
    }

//    public Command driveCommand() {
//        return new RunCommand(
//                ()-> mechanumDriveComponent.driveFieldCentric(
//                        BarnRobot.getInstance().gamepadEx1.getLeftX(),
//                        BarnRobot.getInstance().gamepadEx1.getLeftY(),
//                        BarnRobot.getInstance().gamepadEx1.getRightX()
//                ),
//                this
//        );
//    }

    public Command driveNonFieldoCommand(){
        return new RunCommand(
                () -> mechanumDriveComponent.driveNonFieldCentric(
                        BarnRobot.getInstance().gamepadEx1.getLeftX(),
                        BarnRobot.getInstance().gamepadEx1.getLeftY(),
                        BarnRobot.getInstance().gamepadEx1.getRightX()
                )
        , this);
    }
}
