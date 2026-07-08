package org.firstinspires.ftc.teamcode.subsystems;

import com.pedropathing.follower.Follower;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.subsystems.components.MechanumDriveComponent;

public class Drivetrain extends SubsystemBase {

    public final MechanumDriveComponent mechanumDriveComponent;

    public Drivetrain(){
        mechanumDriveComponent = new MechanumDriveComponent();
    }

    public RunCommand driveCommand() {
        return new RunCommand(
                ()-> mechanumDriveComponent.driveFieldCentric(
                        BarnRobot.getInstance().gamepadEx1.getLeftX(),
                        BarnRobot.getInstance().gamepadEx1.getLeftY(),
                        BarnRobot.getInstance().gamepadEx1.getRightX(),
                        BarnRobot.getInstance().pinpoint.getPosition().getHeading(AngleUnit.RADIANS)
                ),
                this
        );
    }

    public Command driveNonFieldCentricCommand(){
        return new RunCommand(
                () -> mechanumDriveComponent.driveNonFieldCentric(
                        BarnRobot.getInstance().gamepadEx1.getLeftX(),
                        BarnRobot.getInstance().gamepadEx1.getLeftY(),
                        BarnRobot.getInstance().gamepadEx1.getRightX()
                )
        , this);
    }

    public Command drivePedroTolerantCommand(Follower follower){
        return new RunCommand(
                () -> mechanumDriveComponent.drivePepeTolerant(
                        follower,
                        BarnRobot.getInstance().gamepadEx1.getLeftX(),
                        BarnRobot.getInstance().gamepadEx1.getLeftY(),
                        BarnRobot.getInstance().gamepadEx1.getRightX()
                ), this);
    }
}
