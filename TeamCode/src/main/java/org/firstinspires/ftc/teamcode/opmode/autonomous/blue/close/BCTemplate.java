package org.firstinspires.ftc.teamcode.opmode.autonomous.blue.close;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class BCTemplate {
    static final Pose START_POSE = new Pose(27.6853856563, 127.48037889039247, Math.toRadians(143));
    static final Pose SHOOT_POSE = new Pose(34.9941813261, 112.0155615696888, Math.toRadians(131));
    static final Pose LEAVE_POSE = new Pose(23.7362277538, 101.91607877167438, Math.toRadians(135));

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
