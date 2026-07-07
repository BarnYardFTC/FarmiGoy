package org.firstinspires.ftc.teamcode.opmode.autonomous.red.close;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.commandgroups.CommandGroup;

import static org.firstinspires.ftc.teamcode.opmode.autonomous.red.close.RCTemplate.*;
import org.firstinspires.ftc.teamcode.util.Constants;

@Autonomous(name = "RC0", group = "RC")
public class RC0 extends CommandOpMode{
    BarnRobot farminator;
    Follower follower;

    @Override
    public void initialize() {
        farminator = BarnRobot.getInstance();
        farminator.init(this);
        farminator.hood.setPosCommand(0.75);
        follower = Constants.createFollower(hardwareMap);
        RCTemplate.buildPathChains(follower);
        follower.setStartingPose(START_POSE);
        farminator.shooter.operateRangeTwoCommand(); // TBD
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
                CommandGroup.shootCommand(),
                new FollowPathCommand(follower, goLeave)
        );
    }
}
