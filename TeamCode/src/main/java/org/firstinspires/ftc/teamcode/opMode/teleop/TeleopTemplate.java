package org.firstinspires.ftc.teamcode.opMode.teleop;

import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.commandGroups.CommandGroup;

public class TeleopTemplate {
    // TODO: Reset pinpoint

    private BarnRobot farminator = BarnRobot.getInstance();

    public void initControls(){

//        farminator.drive.setDefaultCommand(farminator.drive.driveCommand());

        // =========== BINDS ===========

        new Trigger(() -> farminator.gamepadEx1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.05)
                .whenActive(
                        farminator.intake.activateCommand()
                )

                .whenInactive(
                        farminator.intake.disableCommand()
                );

        new Trigger(() -> farminator.gamepadEx1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.05)
                .whenActive(
                        CommandGroup.shootCommand()
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
                .whenActive(
                        () -> farminator.drive.mechanumDriveComponent.activateSlowMode()
                )
                .whenInactive(
                        () -> farminator.drive.mechanumDriveComponent.activateFastMode()
                );

        farminator.gamepadEx1.getGamepadButton(GamepadKeys.Button.Y)
                .toggleWhenPressed(
                        farminator.shooter.operateRangeFourCommand(),
                        farminator.shooter.turnOff()
                );

        farminator.gamepadEx1.getGamepadButton(GamepadKeys.Button.X)
                .toggleWhenPressed(
                        farminator.kickstand.activateCommand(),
                        farminator.kickstand.deactivateCommand()
                );
    }
}
