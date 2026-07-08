package org.firstinspires.ftc.teamcode.opmode.autonomous.red.close;

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

import static org.firstinspires.ftc.teamcode.opmode.autonomous.red.close.RCTemplate.*;
import org.firstinspires.ftc.teamcode.util.Constants;

@Autonomous(name = "RC3", group = "RC")
public class RC3 extends CommandOpMode{
    BarnRobot farminator;
    Follower follower;

    @Override
    public void initialize() {
        farminator = BarnRobot.getInstance();
        farminator.init(this);
        farminator.hood.setPosCommand(0.65);
        follower = Constants.createFollower(hardwareMap);
        RCTemplate.buildPathChains(follower);
        follower.setStartingPose(START_POSE);
        farminator.shooter.setDefaultCommand(farminator.shooter.operateRangeThreeCommand());
        schedule(autoRoutine());
    }

    @Override
    public void run() {
        farminator.periodic();
        follower.update();
        super.run();
        farminator.telemetry.addData("shooter is ready:", farminator.shooter.isReady());
    }

    private Command autoRoutine() {
        return new SequentialCommandGroup(
                new FollowPathCommand(follower, goShootPre),
                CommandGroup.autoShootingSequence(),
                CommandGroup.enableIntakeTransferCommand(),
                new FollowPathCommand(follower, goCollectClose),
                CommandGroup.disableIntakeTransferCommand(),
                new FollowPathCommand(follower, goShootClose),
                CommandGroup.autoShootingSequence(),
                new FollowPathCommand(follower, goLeave),
                new InstantCommand(this::requestOpModeStop)
        );
    }
}