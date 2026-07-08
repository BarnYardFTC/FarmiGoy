package org.firstinspires.ftc.teamcode.opmode.autonomous.blue.far;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class BFTemplate {
    static final Pose START_POSE = new Pose(55.428477254118675, 9.313960648831635, Math.toRadians(90));
    static final Pose SHOOT_POSE = new Pose(57.3640054127, 20.107577807848443, Math.toRadians(110));
    static final Pose DOWN_COLLECT_POSE = new Pose(11.574182412284271, 34.89353498413321, Math.toRadians(180));
    static final Pose DOWN_COLLECT_CURVE = new Pose(59.35561666628447, 35.67354356051823);
    static final Pose HUMAN_COLLECT_POSE = new Pose(9.330089121056176, 10.416315087527284, Math.toRadians(-90));
    static final Pose HUMAN_COLLECT_CURVE1 = new Pose(37.887595691615545, 67.21731400894805);
    static final Pose HUMAN_COLLECT_CURVE2 = new Pose(11.231528013669216, 58.0069872878279);
    static final Pose LEAVE_POSE = new Pose(53.4813240678, 23.802451438016583, Math.toRadians(110));

    static PathChain goShootPre, goLeave, goCollectDown, goShootDown, goCollectHuman, goShootHuman;

    static void buildPathChains(Follower follower) {
        goShootPre = follower.pathBuilder()
                .addPath(new BezierLine(START_POSE, SHOOT_POSE))
                .setLinearHeadingInterpolation(START_POSE.getHeading(), SHOOT_POSE.getHeading())
                .build();

        goCollectDown = follower.pathBuilder()
                .addPath(new BezierCurve(SHOOT_POSE, DOWN_COLLECT_CURVE, DOWN_COLLECT_POSE))
                .setLinearHeadingInterpolation(SHOOT_POSE.getHeading(), DOWN_COLLECT_POSE.getHeading())
                .build();

        goShootDown = follower.pathBuilder()
                .addPath(new BezierLine(DOWN_COLLECT_POSE, SHOOT_POSE))
                .setLinearHeadingInterpolation(DOWN_COLLECT_POSE.getHeading(), SHOOT_POSE.getHeading())
                .build();

        goCollectHuman = follower.pathBuilder()
                .addPath(new BezierCurve(SHOOT_POSE, HUMAN_COLLECT_CURVE1, HUMAN_COLLECT_CURVE2, HUMAN_COLLECT_POSE))
                .setLinearHeadingInterpolation(SHOOT_POSE.getHeading(), HUMAN_COLLECT_POSE.getHeading())
                .build();

        goShootHuman = follower.pathBuilder()
                .addPath(new BezierLine(HUMAN_COLLECT_POSE, SHOOT_POSE))
                .setLinearHeadingInterpolation(HUMAN_COLLECT_POSE.getHeading(), SHOOT_POSE.getHeading())
                .build();

        goLeave = follower.pathBuilder()
                .addPath(new BezierLine(SHOOT_POSE, LEAVE_POSE))
                .setLinearHeadingInterpolation(SHOOT_POSE.getHeading(), LEAVE_POSE.getHeading())
                .build();
    }
}
