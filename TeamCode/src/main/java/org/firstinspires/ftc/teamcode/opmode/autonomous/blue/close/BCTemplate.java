package org.firstinspires.ftc.teamcode.opmode.autonomous.blue.close;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class BCTemplate {
    static final Pose START_POSE = new Pose(27.6853856563, 127.48037889039247, Math.toRadians(143));
    static final Pose SHOOT_POSE = new Pose(34.9941813261, 112.0155615696888, Math.toRadians(131));
    static final Pose LEAVE_POSE = new Pose(23.7362277538, 101.91607877167438, Math.toRadians(135));

    static final Pose CLOSE_COLLECT_POSE = new Pose(20.969078179696616, 83.05075845974328, Math.toRadians(180));
    static final Pose CLOSE_COLLECT_CURVE = new Pose(58.77946324387398, 81.23453908984828);

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
