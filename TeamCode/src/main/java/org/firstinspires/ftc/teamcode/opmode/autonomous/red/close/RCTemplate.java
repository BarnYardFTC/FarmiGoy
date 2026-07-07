package org.firstinspires.ftc.teamcode.opmode.autonomous.red.close;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class RCTemplate {
    static final Pose START_POSE = new Pose(116.31461434370773, 127.48037889039247, Math.toRadians(37));
    static final Pose SHOOT_POSE = new Pose(96, 96, Math.toRadians(45));
    static final Pose LEAVE_POSE = new Pose(119.306, 106.894, Math.toRadians(45));

    static PathChain goShootPre;
    static PathChain goLeave;

    static void buildPathChains(Follower follower) {
        goShootPre = follower.pathBuilder()
                .addPath(new BezierLine(START_POSE, SHOOT_POSE))
                .setLinearHeadingInterpolation(START_POSE.getHeading(), SHOOT_POSE.getHeading())
                .build();

        goLeave = follower.pathBuilder()
                .addPath(new BezierLine(SHOOT_POSE, LEAVE_POSE))
                .setLinearHeadingInterpolation(SHOOT_POSE.getHeading(), LEAVE_POSE.getHeading())
                .build();
    }
}
