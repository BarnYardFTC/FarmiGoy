package org.firstinspires.ftc.teamcode.opmode.autonomous.blue.close;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.util.CommandGroup;

import static org.firstinspires.ftc.teamcode.opmode.autonomous.blue.close.BCTemplate.*;
import org.firstinspires.ftc.teamcode.util.Constants;

@Autonomous(name = "BC0", group = "BC")
public class BC0 extends CommandOpMode{
    BarnRobot farminator;
    Follower follower;

    @Override
    public void initialize() {
        farminator = BarnRobot.getInstance();
        farminator.init(this);
        follower = Constants.createFollower(hardwareMap);
        BCTemplate.buildPathChains(follower);
        follower.setStartingPose(START_POSE);
        farminator.shooter.operateRangeTwoCommand();
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
