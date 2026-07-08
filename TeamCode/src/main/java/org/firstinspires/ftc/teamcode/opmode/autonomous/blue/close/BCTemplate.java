package org.firstinspires.ftc.teamcode.opmode.autonomous.blue.close;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class BCTemplate {
    static final Pose START_POSE = new Pose(26.03427713821365, 126.15949207592341, Math.toRadians(143));
    static final Pose SHOOT_POSE = new Pose(34.9941813261, 112.0155615696888, Math.toRadians(131));
    static final Pose LEAVE_POSE = new Pose(23.7362277538, 101.91607877167438, Math.toRadians(135));

    static final Pose UPPER_COLLECT_POSE = new Pose(18.65752625437573, 82.2252042007001, Math.toRadians(180));
    static final Pose UPPER_COLLECT_CURVE = new Pose(69.84189031505251, 78.26254375729289);
    static final Pose MID_COLLECT_POSE = new Pose(12.737545280613324, 58.44784504082358, Math.toRadians(180));
    static final Pose MID_COLLECT_CURVE = new Pose(98.86352958106963, 54.47586899953859);

    static PathChain goShootPre, goCollectUpper, goLeave, goShootUpper, goCollectMid, goShootMid;

    static void buildPathChains(Follower follower) {
        goShootPre = follower.pathBuilder()
                .addPath(new BezierLine(START_POSE, SHOOT_POSE))
                .setLinearHeadingInterpolation(START_POSE.getHeading(), SHOOT_POSE.getHeading())
                .build();

        goCollectUpper = follower.pathBuilder()
                .addPath(new BezierCurve(SHOOT_POSE, UPPER_COLLECT_CURVE, UPPER_COLLECT_POSE))
                .setLinearHeadingInterpolation(SHOOT_POSE.getHeading(), UPPER_COLLECT_POSE.getHeading())
                .build();

        goShootUpper = follower.pathBuilder()
                .addPath(new BezierLine(UPPER_COLLECT_POSE, SHOOT_POSE))
                .setLinearHeadingInterpolation(UPPER_COLLECT_POSE.getHeading(), SHOOT_POSE.getHeading())
                .build();

        goCollectMid = follower.pathBuilder()
                .addPath(new BezierCurve(SHOOT_POSE, MID_COLLECT_CURVE, MID_COLLECT_POSE))
                .setLinearHeadingInterpolation(SHOOT_POSE.getHeading(), MID_COLLECT_POSE.getHeading())
                .build();

        goShootMid = follower.pathBuilder()
                .addPath(new BezierLine(MID_COLLECT_POSE, SHOOT_POSE))
                .setLinearHeadingInterpolation(MID_COLLECT_POSE.getHeading(), SHOOT_POSE.getHeading())
                .build();

        goLeave = follower.pathBuilder()
                .addPath(new BezierLine(SHOOT_POSE, LEAVE_POSE))
                .setLinearHeadingInterpolation(SHOOT_POSE.getHeading(), LEAVE_POSE.getHeading())
                .build();
    }
}
