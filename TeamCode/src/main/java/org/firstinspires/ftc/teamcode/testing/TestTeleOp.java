package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.util.OpmodeData;

@TeleOp
@Disabled
public class TestTeleOp extends CommandOpMode {
    BarnRobot farmigoy;
    @Override
    public void initialize() {
        farmigoy = BarnRobot.getInstance();
        farmigoy.init(this, new OpmodeData());

        farmigoy.drive.setDefaultCommand(farmigoy.drive.driveNonFieldCentricCommand());

//        new Trigger(() -> farmigoy.gamepadEx1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.05)
//                .whenActive(
//                        CommandGroup.enableIntakeTransferCommand()
//                )
//
//                .whenInactive(
//                        CommandGroup.disableIntakeTransferCommand()
//                );

        //TODO: ADD IN FUTURE SHOOT COMMAND
//        new Trigger(() -> farmigoy.gamepadEx1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.05)
//                .whenActive(
//                        CommandGroup.shootCommand()
//                );


        farmigoy.gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(
                        farmigoy.hood.lowerCommand()
                );

        farmigoy.gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(
                        farmigoy.hood.raiseCommand()
                );

        farmigoy.gamepadEx1.getGamepadButton(GamepadKeys.Button.A)
                .toggleWhenPressed(
                        farmigoy.gate.openCommand(),
                        farmigoy.gate.closeCommand()
                );

        farmigoy.gamepadEx1.getGamepadButton(GamepadKeys.Button.B)
                .toggleWhenActive(
                        () -> farmigoy.drive.mechanumDriveComponent.activateSlowMode(),
                        () -> farmigoy.drive.mechanumDriveComponent.activateFastMode()
                );


        farmigoy.gamepadEx1.getGamepadButton(GamepadKeys.Button.X)
                .toggleWhenPressed(
                        farmigoy.kickstand.activateCommand(),
                        farmigoy.kickstand.deactivateCommand()
                );

        farmigoy.gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(
                        farmigoy.shooter.operateRangeFourCommand()
                );

        farmigoy.gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(
                        farmigoy.shooter.operateRangeThreeCommand()
                );

        farmigoy.gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(
                        farmigoy.shooter.operateRangeTwoCommand()
                );

        farmigoy.gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(
                        farmigoy.shooter.operateRangeOneCommand()
                );

        farmigoy.gamepadEx1.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(
                        farmigoy.shooter.turnOff()
                );
    }

    @Override
    public void run(){
        super.run();
        farmigoy.limelight.displayTelemetry(telemetry);
        telemetry.addData("RPM", farmigoy.shooter.getRPM());
        farmigoy.periodic();
    }
}
