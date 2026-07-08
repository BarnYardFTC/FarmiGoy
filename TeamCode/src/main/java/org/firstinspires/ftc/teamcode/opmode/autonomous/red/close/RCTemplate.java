package org.firstinspires.ftc.teamcode.opmode.autonomous.red.close;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class RCTemplate {
    static final Pose START_POSE = new Pose(116.31461434370773, 127.48037889039247, Math.toRadians(37));
    static final Pose SHOOT_POSE = new Pose(96, 96, Math.toRadians(45));
    static final Pose CLOSE_COLLECT_POSE = new Pose(120, 82.0682796507215, Math.toRadians(0));
    static final Pose CLOSE_COLLECT_CURVE = new Pose(91.30630105017504, 83.38098016336055);
    static final Pose LEAVE_POSE = new Pose(119.306, 106.894, Math.toRadians(45));

    static PathChain goShootPre, goCollectClose, goLeave, goShootClose;

    static void buildPathChains(Follower follower) {
        goShootPre = follower.pathBuilder()
                .addPath(new BezierLine(START_POSE, SHOOT_POSE))
                .setLinearHeadingInterpolation(START_POSE.getHeading(), SHOOT_POSE.getHeading())
                .build();

        goCollectClose = follower.pathBuilder()
                .addPath(new BezierCurve(SHOOT_POSE, CLOSE_COLLECT_CURVE, CLOSE_COLLECT_POSE))
                .setLinearHeadingInterpolation(Math.toRadians(SHOOT_POSE.getHeading()), Math.toRadians(CLOSE_COLLECT_POSE.getHeading()))
                .build();

        goShootClose = follower.pathBuilder()
                .addPath(new BezierLine(CLOSE_COLLECT_POSE, SHOOT_POSE))
                .setLinearHeadingInterpolation(Math.toRadians(CLOSE_COLLECT_POSE.getHeading()), Math.toRadians(SHOOT_POSE.getHeading()))
                .build();

        goLeave = follower.pathBuilder()
                .addPath(new BezierLine(SHOOT_POSE, LEAVE_POSE))
                .setLinearHeadingInterpolation(SHOOT_POSE.getHeading(), LEAVE_POSE.getHeading())
                .build();
    }
}
