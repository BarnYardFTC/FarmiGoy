package org.firstinspires.ftc.teamcode.opmode.autonomous.blue.far;

import static org.firstinspires.ftc.teamcode.opmode.autonomous.blue.far.BFTemplate.START_POSE;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.util.CommandGroup;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.OpmodeData;

import static org.firstinspires.ftc.teamcode.opmode.autonomous.blue.far.BFTemplate.*;


@Autonomous(name="BF6", group = "BF")
public class BF6 extends CommandOpMode {
    BarnRobot farminator;
    Follower follower;

    @Override
    public void initialize() {
        farminator = BarnRobot.getInstance();
        farminator.init(this, new OpmodeData());
        follower = Constants.createFollower(hardwareMap);
        BFTemplate.buildPathChains(follower);
        follower.setStartingPose(START_POSE);
        farminator.shooter.setDefaultCommand(farminator.shooter.operateRangeThreeCommand());
        schedule(autoRoutine());
    }

    @Override
    public void run() {
        farminator.periodic();
        follower.update();
        super.run();
    }

    private Command autoRoutine() {
        return new SequentialCommandGroup(
                new FollowPathCommand(follower, goShootPre),
                CommandGroup.autoShootingSequence(),
                CommandGroup.enableIntakeTransferCommand(),
                new FollowPathCommand(follower, goCollectDown),
                CommandGroup.disableIntakeTransferCommand(),
                new FollowPathCommand(follower, goShootDown),
                CommandGroup.autoShootingSequence(),
                CommandGroup.enableIntakeTransferCommand(),
                new FollowPathCommand(follower, goCollectHuman),
                CommandGroup.disableIntakeTransferCommand(),
                new FollowPathCommand(follower, goShootHuman),
                CommandGroup.autoShootingSequence(),
                new FollowPathCommand(follower, goLeave),
                new InstantCommand(this::requestOpModeStop)
        );
    }
}
