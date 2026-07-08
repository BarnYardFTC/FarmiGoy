package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.geometry.Pose2d;
import com.seattlesolvers.solverslib.geometry.Rotation2d;

import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.util.OpmodeData;

@TeleOp(name = "Red Teleop", group = "Teleops")
public class RedTeleop extends CommandOpMode {
    private TeleopTemplate template;
    private BarnRobot farminator;
    private OpmodeData opmodeData;

    @Override
    public void initialize() {
        farminator = BarnRobot.getInstance();
        farminator.init(this, new OpmodeData(OpmodeData.AllianceColor.RED, 0));

        template = new TeleopTemplate();
        template.initControls(false, hardwareMap);
        waitForStart();
        template.follower.startTeleopDrive();
        farminator.hardware.setBrake();
    }

    @Override
    public void run() {
        super.run();
        farminator.periodic();
    }
}
