package org.firstinspires.ftc.teamcode.opMode.teleop;

import android.annotation.TargetApi;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.geometry.Pose2d;
import com.seattlesolvers.solverslib.geometry.Rotation2d;
import com.sun.tools.javac.comp.Todo;

import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.util.OpmodeData;

import java.util.function.ToDoubleBiFunction;

@TeleOp(name = "Red Teleop", group = "Teleops")
public class RedTeleop extends CommandOpMode {
    private TeleopTemplate template;
    private BarnRobot farminator;
    private OpmodeData opmodeData;

    @Override
    public void initialize() {
        farminator = BarnRobot.getInstance();
        farminator.init(this);

        template = new TeleopTemplate();
        template.initControls();

        opmodeData = new OpmodeData(
                OpmodeData.AllianceColor.RED,
                OpmodeData.OpModeType.TELEOP,
                new Pose2d(0, 0, new Rotation2d(0)),
                0
        );
    }

    @Override
    public void run() {
        super.run();

        farminator.periodic();
    }
}
