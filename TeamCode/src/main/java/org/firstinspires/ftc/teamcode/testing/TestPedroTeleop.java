package org.firstinspires.ftc.teamcode.testing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopTemplate;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.OpmodeData;

@TeleOp
public class TestPedroTeleop extends CommandOpMode {
    private BarnRobot farminator;
    private TeleopTemplate template;
    private final Pose START_POSE = new Pose(72, 72, Math.toRadians(0));

    @Override
    public void initialize() {
        farminator = BarnRobot.getInstance();
        farminator.init(this, new OpmodeData(OpmodeData.AllianceColor.RED, 0));

        template = new TeleopTemplate();
        template.initControls(true, hardwareMap);

        template.follower.setStartingPose(START_POSE);
        template.follower.update();
        waitForStart();
        template.follower.startTeleopDrive();
        farminator.hardware.setBrake();

    }

    @Override
    public void run() {
        farminator.periodic();
        template.follower.update();
        super.run();
        template.follower.setTeleOpDrive(
                farminator.gamepadEx1.getLeftY() * template.speedModifier,
                -farminator.gamepadEx1.getLeftX() * template.speedModifier,
                -farminator.gamepadEx1.getRightX() * template.speedModifier,
                false
        );
    }
}
