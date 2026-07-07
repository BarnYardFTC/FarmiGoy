package org.firstinspires.ftc.teamcode.opmode.autonomous.blue.far;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class BFTemplate {
    static final Pose START_POSE = new Pose(58.8958051421, 9.148849797022999, Math.toRadians(90));
    static final Pose SHOOT_POSE = new Pose(57.3640054127, 20.107577807848443, Math.toRadians(105));
    static final Pose LEAVE_POSE = new Pose(53.4813240678, 23.802451438016583, Math.toRadians(135));

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
