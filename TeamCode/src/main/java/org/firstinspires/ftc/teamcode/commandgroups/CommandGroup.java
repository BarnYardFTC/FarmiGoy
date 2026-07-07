package org.firstinspires.ftc.teamcode.commandgroups;

import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.BarnRobot;

public class CommandGroup extends SequentialCommandGroup {
    static BarnRobot farminator = BarnRobot.getInstance();
    public static boolean isShooting = false;

    public static Command shootCommand(){
        return new SequentialCommandGroup(
                new InstantCommand(() -> isShooting = true),
                farminator.transfer.activateCommand(),
                farminator.gate.openCommand(),
                new WaitCommand(1500),
                farminator.gate.closeCommand(),
                farminator.transfer.disableCommand(),
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
}
