package org.firstinspires.ftc.teamcode.testing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.opmode.teleop.TeleopTemplate;
import org.firstinspires.ftc.teamcode.util.Constants;

@TeleOp
public class TestPedroTeleop extends CommandOpMode {
    private BarnRobot farminator;
    private Follower follower;
    private TeleopTemplate template;
    private final Pose START_POSE = new Pose(72, 72, Math.toRadians(0));

    @Override
    public void initialize() {
        farminator = BarnRobot.getInstance();
        farminator.init(this);

        template = new TeleopTemplate();
        template.initControls(true);

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(START_POSE);
        follower.update();
        waitForStart();
        follower.startTeleopDrive();
        farminator.hardware.setBrake();
    }

    @Override
    public void run() {
        farminator.periodic();
        follower.update();
        super.run();
        follower.setTeleOpDrive(
                farminator.gamepadEx1.getLeftY(),
                -farminator.gamepadEx1.getLeftX(),
                -farminator.gamepadEx1.getRightX(),
                false
        );
    }
}
