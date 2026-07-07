package org.firstinspires.ftc.teamcode.opmode.autonomous.red.far;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class RFTemplate {
    static final Pose START_POSE = new Pose(85.10419485791611, 9.148849797022999, Math.toRadians(90));
    static final Pose SHOOT_POSE = new Pose(86.63599458728011, 20.107577807848443, Math.toRadians(75));
    static final Pose LEAVE_POSE = new Pose(90.51867593216754, 23.802451438016583, Math.toRadians(45));

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
