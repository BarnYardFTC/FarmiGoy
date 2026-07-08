package org.firstinspires.ftc.teamcode.opmode.autonomous.blue.close;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import static org.firstinspires.ftc.teamcode.opmode.autonomous.blue.close.BCTemplate.*;


import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.util.CommandGroup;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.OpmodeData;

@Autonomous(name = "BC6", group = "BC")
public class BC6 extends CommandOpMode {
    BarnRobot farminator;
    Follower follower;

    @Override
    public void initialize() {
        farminator = BarnRobot.getInstance();
        farminator.init(this, new OpmodeData());
        farminator.hood.setPosCommand(0.65);
        follower = Constants.createFollower(hardwareMap);
        BCTemplate.buildPathChains(follower);
        follower.setStartingPose(START_POSE);
        farminator.shooter.setDefaultCommand(farminator.shooter.operateRangeThreeCommand()); // TBD
        schedule(autoRoutine());
    }

    @Override
    public void run() {
        farminator.periodic();
        follower.update();
        super.run();
    }

    public Command autoRoutine(){
        return new SequentialCommandGroup(
            new FollowPathCommand(follower, goShootPre),
            CommandGroup.autoShootingSequence(),
            CommandGroup.enableIntakeTransferCommand(),
            new FollowPathCommand(follower, goCollectUpper),
            CommandGroup.disableIntakeTransferCommand(),
            new FollowPathCommand(follower, goShootUpper),
            CommandGroup.autoShootingSequence(),
            CommandGroup.enableIntakeTransferCommand(),
            new FollowPathCommand(follower, goCollectMid),
            CommandGroup.disableIntakeTransferCommand(),
            new FollowPathCommand(follower, goShootMid),
            CommandGroup.autoShootingSequence(),
            new FollowPathCommand(follower, goLeave),
            new InstantCommand(this::requestOpModeStop)
        );
    }
}
