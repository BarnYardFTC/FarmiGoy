package org.firstinspires.ftc.teamcode.util;

import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.BarnRobot;

public class Constants {

    // TODO: decide whether we need secondary PIDF controllers
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(14.07)
            .forwardZeroPowerAcceleration(-25.76395763804099)
            .lateralZeroPowerAcceleration(-87.59485280046445)
            .useSecondaryTranslationalPIDF(true) // shitty without it
            .useSecondaryHeadingPIDF(false) // no need
            .useSecondaryDrivePIDF(false) // might use
            .translationalPIDFCoefficients(new PIDFCoefficients(0.12, 0.9, 0, 0.7))
            .secondaryTranslationalPIDFCoefficients(new PIDFCoefficients(0.1, 0.04, 0, 0.2))
            .headingPIDFCoefficients(new PIDFCoefficients(0.2, 0.5, 0, 0.07));
    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .pinpointLocalizer(localizerConstants)
                .build();
    }

    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName(Hardware.RIGHT_FRONT_DRIVETRAIN_CONFIG_NAME)
            .rightRearMotorName(Hardware.RIGHT_BACK_DRIVETRAIN_CONFIG_NAME)
            .leftRearMotorName(Hardware.LEFT_BACK_DRIVETRAIN_CONFIG_NAME)
            .leftFrontMotorName(Hardware.LEFT_FRONT_DRIVETRAIN_CONFIG_NAME)
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .xVelocity(73.24950450987328)
            .yVelocity(53.103720837690695);

    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(-47/2.54)
            .strafePodX(-70/2.54)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);
}