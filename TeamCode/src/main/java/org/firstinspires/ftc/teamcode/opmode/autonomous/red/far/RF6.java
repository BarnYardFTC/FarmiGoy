package org.firstinspires.ftc.teamcode.opmode.autonomous.red.far;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.util.CommandGroup;

import static org.firstinspires.ftc.teamcode.opmode.autonomous.red.far.RFTemplate.*;
import org.firstinspires.ftc.teamcode.util.Constants;

@Autonomous(name = "RF6", group = "RF")
public class RF6 extends CommandOpMode {
    BarnRobot farminator;
    Follower follower;

    @Override
    public void initialize() {
        farminator = BarnRobot.getInstance();
        farminator.init(this);
        follower = Constants.createFollower(hardwareMap);
        RFTemplate.buildPathChains(follower);
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
                new FollowPathCommand(follower, goCollectFar),
                CommandGroup.disableIntakeTransferCommand(),
                new FollowPathCommand(follower, goFarShoot),
                CommandGroup.autoShootingSequence(),
                CommandGroup.enableIntakeTransferCommand(),
                new FollowPathCommand(follower, goHumanCollect),
                CommandGroup.disableIntakeTransferCommand(),
                new FollowPathCommand(follower, goShootHuman),
                CommandGroup.autoShootingSequence(),
                new FollowPathCommand(follower, goLeave),
                new InstantCommand(this::requestOpModeStop)
        );
    }
}