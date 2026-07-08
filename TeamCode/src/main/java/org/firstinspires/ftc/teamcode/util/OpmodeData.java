package org.firstinspires.ftc.teamcode.util;

import com.seattlesolvers.solverslib.geometry.Pose2d;

public class OpmodeData {
    public enum AllianceColor { RED, BLUE }
    public enum OpModeType { TELEOP, AUTONOMOUS }

    OpModeType opModeType;
    AllianceColor allianceColor;

    public double fieldReferenceHeading;
    public Pose2d initialPos;

    private static Pose2d autoFinishPos;

    //Teleop
    public OpmodeData(AllianceColor allianceColor,
                      OpModeType opModeType,
                      Pose2d initialPos,
                      double fieldReferenceHeading) {

        this.allianceColor = allianceColor;
        this.fieldReferenceHeading = fieldReferenceHeading;
        this.opModeType = opModeType;
        this.initialPos = initialPos;
    }

    //Autonomous
    public OpmodeData(AllianceColor allianceColor,
                      OpModeType opModeType) {

        this.allianceColor = allianceColor;
        this.opModeType = opModeType;
    }

    public static void setAutoFinishPose(Pose2d autoFinishPose){
        OpmodeData.autoFinishPos = autoFinishPose;
    }
}