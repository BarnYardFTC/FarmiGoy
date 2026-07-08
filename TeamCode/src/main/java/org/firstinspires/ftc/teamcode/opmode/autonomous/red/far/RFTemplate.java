package org.firstinspires.ftc.teamcode.opmode.autonomous.red.far;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class RFTemplate {
    static final Pose START_POSE = new Pose(85.10419485791611, 9.148849797022999, Math.toRadians(90));
    static final Pose SHOOT_POSE = new Pose(86.63599458728011, 20.107577807848443, Math.toRadians(70));
    static final Pose LEAVE_POSE = new Pose(90.51867593216754, 23.802451438016583, Math.toRadians(45));
    static final Pose FAR_COLLECT_POSE = new Pose(122.79474923268906, 34.79023971742191, Math.toRadians(0));
    static final Pose FAR_COLLECT_CURVE = new Pose(86.50796234172321, 36.14523315003309);
    static final Pose HUMAN_COLLECT_POSE = new Pose(131.9235705950991, 9.57642940490083, Math.toRadians(-90));
    static final Pose HUMAN_COLLECT_CURVE = new Pose(130.86098158890488, 59.95996524047509);

    static PathChain goShootPre, goCollectFar, goHumanCollect, goLeave, goShootHuman, goFarShoot;

    static void buildPathChains(Follower follower) {
        goShootPre = follower.pathBuilder()
                .addPath(new BezierLine(START_POSE, SHOOT_POSE))
                .setLinearHeadingInterpolation(START_POSE.getHeading(), SHOOT_POSE.getHeading())
                .build();

        goCollectFar = follower.pathBuilder()
                .addPath(new BezierCurve(SHOOT_POSE, FAR_COLLECT_CURVE, FAR_COLLECT_POSE))
                .setLinearHeadingInterpolation(SHOOT_POSE.getHeading(), FAR_COLLECT_POSE.getHeading())
                .build();

        goFarShoot = follower.pathBuilder()
                .addPath(new BezierLine(FAR_COLLECT_POSE, SHOOT_POSE))
                .setLinearHeadingInterpolation(FAR_COLLECT_POSE.getHeading(), SHOOT_POSE.getHeading())
                .build();

        goHumanCollect = follower.pathBuilder()
                .addPath(new BezierCurve(SHOOT_POSE, HUMAN_COLLECT_CURVE, HUMAN_COLLECT_POSE))
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
