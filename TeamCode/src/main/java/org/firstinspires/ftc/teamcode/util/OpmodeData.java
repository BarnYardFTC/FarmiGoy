package org.firstinspires.ftc.teamcode.util;

import com.seattlesolvers.solverslib.geometry.Pose2d;

public class OpmodeData {
    public enum AllianceColor { RED, BLUE }
    public enum OpModeType { TELEOP, AUTONOMOUS }

    public OpModeType opModeType;
    public AllianceColor allianceColor;

    public double fieldReferenceHeading;
    public Pose2d initialPos;

    public double initialBotHeading;


    private static Pose2d autoFinishPos;

    public static OpmodeData defaultOpmodeData = new OpmodeData(AllianceColor.RED, 0);


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
                      double initHeading) {

        this.allianceColor = allianceColor;
        this.initialBotHeading = initHeading;}

    public OpmodeData(double initialBotHeading){
        this(defaultOpmodeData.allianceColor, initialBotHeading);
    }
    public OpmodeData(){
        this(defaultOpmodeData.allianceColor, defaultOpmodeData.initialBotHeading);
    }

    public static void setAutoFinishPose(Pose2d autoFinishPose){
        OpmodeData.autoFinishPos = autoFinishPose;
    }



    public void setAllianceColor(AllianceColor allianceColor){
        this.allianceColor = allianceColor;
    }

    public void setInitialBotHeading(double initialBotHeading){
        this.initialBotHeading = initialBotHeading;
    }

}