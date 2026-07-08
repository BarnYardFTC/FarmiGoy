package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.util.CommandGroup;

public class TeleopTemplate {
    private BarnRobot farminator = BarnRobot.getInstance();
    public TeleopTemplate(){

        // ========== DEFAULTS ==========

        farminator.drive.setDefaultCommand(farminator.drive.driveCommand());
        farminator.shooter.setDefaultCommand(farminator.shooter.operateRangeThreeCommand());
        farminator.intake.setDefaultCommand(farminator.intake.activateCommand());

        // =========== BINDS ===========

        new Trigger(() -> farminator.gamepadEx1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.5 && !CommandGroup.isShooting)
                .toggleWhenActive(
                        new ParallelCommandGroup(farminator.transfer.activateCommand(),
                                farminator.intake.activateCommand()),
                        new ParallelCommandGroup(farminator.transfer.disableCommand(),
                                farminator.intake.disableCommand())
                );

        new Trigger(() -> farminator.gamepadEx1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.5)
                .whenActive(
                        CommandGroup.shootCommand()
                );

        farminator.gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenActive(
                        CommandGroup.shootCommand()
                );
        farminator.gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                        .whenPressed(
                                farminator.shooter.operateRangeFourCommand()
                        );
        farminator.gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                        .whenPressed(
                                farminator.shooter.operateRangeThreeCommand()
                        );

        farminator.gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(
                        farminator.hood.lowerCommand()
                );

        farminator.gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(
                        farminator.hood.raiseCommand()
                );

        farminator.gamepadEx1.getGamepadButton(GamepadKeys.Button.B)
                .toggleWhenActive(
                        farminator.drive.activateSlowMode(),
                        farminator.drive.activateFastMode()
                );

        farminator.gamepadEx1.getGamepadButton(GamepadKeys.Button.Y)
                .toggleWhenPressed(
                        farminator.shooter.turnOff(),
                        farminator.shooter.operateRangeThreeCommand()
                );

        farminator.gamepadEx1.getGamepadButton(GamepadKeys.Button.X)
                .toggleWhenPressed(
                        farminator.kickstand.activateCommand(),
                        farminator.kickstand.deactivateCommand()
                );
    }
}
