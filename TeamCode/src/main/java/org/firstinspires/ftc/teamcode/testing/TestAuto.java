package org.firstinspires.ftc.teamcode.testing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.commandGroups.CommandGroup;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.OpmodeData;

@Autonomous(name="TestAuto", group="test")
public class TestAuto extends CommandOpMode {
    BarnRobot farminator;

    Follower follower;
    private Path path;
    private boolean shootScheduled = false;
    private final Pose START_POSE = new Pose(115.90781796966162, 127.63068844807466, Math.toRadians(37));
    private final Pose END_POSE = new Pose(94.80105017502918, 117.89498249708285, Math.toRadians(26));

    private final OpmodeData opmodeData = new OpmodeData(
            OpmodeData.AllianceColor.RED,
            OpmodeData.OpModeType.AUTONOMOUS,
            new Pose2d(0, 0, 0)
    );

    @Override
    public void initialize() {
        farminator = BarnRobot.getInstance();
        farminator.init(this);
        farminator.shooter.setDefaultCommand(farminator.shooter.operateRangeOneCommand());

        follower = Constants.createFollower(hardwareMap);
        farminator.pinpoint.setPosition(START_POSE);
        path = new Path(new BezierLine(START_POSE, END_POSE));

        waitForStart();
        follower.followPath(path);
    }

    @Override
    public void run() {
        super.run();
        farminator.periodic();

        follower.update();

        if (!follower.isBusy() && !shootScheduled) {
            schedule(CommandGroup.shootCommand());
            shootScheduled = true;
        }
    }
}
