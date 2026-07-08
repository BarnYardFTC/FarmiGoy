package org.firstinspires.ftc.teamcode.testing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import com.seattlesolvers.solverslib.pedroCommand.TurnToCommand;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.util.CommandGroup;
import org.firstinspires.ftc.teamcode.util.Constants;
import org.firstinspires.ftc.teamcode.util.OpmodeData;

@Autonomous(name="TestAuto", group="test")
public class TestAuto extends CommandOpMode {
    BarnRobot farminator;


    /** Path follower - corrects robot towards it's supposed position each loop */
    Follower follower;


    /** Pedro's Pose class objects store position.
     * To understand the coordinate system visit Pedro's manual coordinate page.  */
    private final Pose START_POSE = new Pose(72, 72, Math.toRadians(90));
    private final Pose SECOND_POSE = new Pose(72, 96, Math.toRadians(90));
    private final Pose END_POSE = new Pose(96, 96, Math.toRadians(180));


    /** PathChain objects store transition between Poses */
    private PathChain goForward, strafeAndTurn;

    @Override
    public void initialize() {
        farminator = BarnRobot.getInstance();
        farminator.init(this, new OpmodeData());

        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        follower.setStartingPose(START_POSE);
        //farminator.pinpoint.setPosition(START_POSE);

        schedule(autoRoutine()); // Wrapping in schedule() is necessary
        // DO NOT CHANGE THE ORDER !!
    }

    @Override
    public void run() {
        farminator.periodic();
        follower.update();
        super.run();
        // DO NOT CHANGE THE ORDER !!
        farminator.telemetry.addData("pinpoint x: ", farminator.pinpoint.getPosition().getX(DistanceUnit.INCH));
        farminator.telemetry.addData("pinpoint y: ", farminator.pinpoint.getPosition().getY(DistanceUnit.INCH));
        farminator.telemetry.addData("pinpoint heading: ", farminator.pinpoint.getPosition().getHeading(AngleUnit.DEGREES));
        farminator.telemetry.addData("follower x: ", follower.getPose().getX());
        farminator.telemetry.addData("follower y: ", follower.getPose().getY());
        farminator.telemetry.addData("follower heading: ", follower.getPose().getHeading());
    }


    /** Path initialization */
    private void buildPaths() {
        goForward = follower.pathBuilder()
                .addPath(new BezierLine(START_POSE, SECOND_POSE))
                .setLinearHeadingInterpolation(START_POSE.getHeading(), SECOND_POSE.getHeading())
                .build();
        
        strafeAndTurn = follower.pathBuilder()
                .addPath(new BezierLine(SECOND_POSE, END_POSE))
                .setLinearHeadingInterpolation(SECOND_POSE.getHeading(), END_POSE.getHeading())
                .build();
    }


    /** Function that wraps all the PathChains into FollowPathCommands
     * (yup, solverslib and Pepe had a child) and returns them as a SequentialCommandGroup */
    private Command autoRoutine() {
        return new SequentialCommandGroup(
                new FollowPathCommand(follower, goForward),
                new FollowPathCommand(follower, strafeAndTurn),
                CommandGroup.shootCommand(),
                new TurnToCommand(follower, START_POSE.getHeading()) // Preferably don't use, messes up heading
        );
    }
}
