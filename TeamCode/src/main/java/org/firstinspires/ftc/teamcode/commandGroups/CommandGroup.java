package org.firstinspires.ftc.teamcode.commandGroups;

import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.BarnRobot;

public class CommandGroup extends SequentialCommandGroup {
    static BarnRobot farminator = BarnRobot.getInstance();

    public static Command shootCommand(){
        return new SequentialCommandGroup(
                farminator.intake.activateCommand(),
                farminator.gate.openCommand(),
                new WaitCommand(1500),
                farminator.gate.closeCommand(),
                farminator.intake.disableCommand()
        );
    }

    public static Command activateIntakeTransferCommand() {
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
