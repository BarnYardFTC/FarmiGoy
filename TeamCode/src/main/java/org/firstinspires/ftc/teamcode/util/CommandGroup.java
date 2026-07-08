package org.firstinspires.ftc.teamcode.util;

import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.ParallelRaceGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.BarnRobot;

public class CommandGroup extends SequentialCommandGroup {
    static BarnRobot farminator = BarnRobot.getInstance();
    public static boolean isShooting = false;

    public static Command shootCommand(){
        return new SequentialCommandGroup(
                new InstantCommand(() -> isShooting = true),
                farminator.transfer.activateCommand(),
                farminator.intake.activateCommand(),
                farminator.gate.openCommand(),
                new WaitCommand(1500),
                farminator.gate.closeCommand(),
                farminator.transfer.disableCommand(),
                farminator.intake.disableCommand(),
                new InstantCommand(() -> isShooting = false)
                );
    }

    public static Command enableIntakeTransferCommand() {
        return new ParallelCommandGroup(
                farminator.intake.activateCommand(),
                farminator.transfer.activateCommand()
        );
    }

    public static Command disableIntakeTransferCommand() {
        return new ParallelCommandGroup(
                farminator.intake.disableCommand(),
                farminator.transfer.disableCommand()
        );
    }

    public static Command autoShootingSequence(){
        return new SequentialCommandGroup(
            new WaitUntilCommand(() -> farminator.shooter.isReady()),
            new WaitCommand(1000),
            shootCommand()
        );
    }
}
