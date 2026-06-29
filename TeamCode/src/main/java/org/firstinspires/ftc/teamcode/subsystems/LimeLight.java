package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.BarnRobot;

import java.util.List;

public class LimeLight extends SubsystemBase {


    public static final int POLL_RATE_HZ = 100;
    public static final int STANDARD_STALENESS = 100;

    private final Limelight3A limelight;

    private LLResult llResult;
    private List<LLResultTypes.FiducialResult> frs;

    private final int closePipeline = 7;
    private final int farBluePipeline = 6;
    private final int farRedPipeline = 5;

    boolean alianceCol = false; // false - blue true - red


    public LimeLight(){
        limelight = BarnRobot.getInstance().hardware.limeLight;
        limelight.pipelineSwitch(closePipeline);
        limelight.setPollRateHz(POLL_RATE_HZ);
        resetData();
        start();
    }

    public void resetData(){
        llResult = null;
        frs = null;
    }

    public void start(){
        limelight.start();
    }

    public boolean isDataValid(){
        return llResult != null && frs != null && !frs.isEmpty() && llResult.isValid() && llResult.getStaleness() < STANDARD_STALENESS;
    }

    public boolean isGoalDetected(){
        if (isDataValid()){
            for (LLResultTypes.FiducialResult fr : frs){
                if ((fr.getFiducialId() == 20 && !alianceCol) || (fr.getFiducialId() == 21 && alianceCol)){
                    return true;
                }
            }
        }
        return false;
    }

    public void targetAreaTelemetry(){              // It will fill all telemetry !!!!!!!!!!!!!!!
        if(isGoalDetected()){
            for (LLResultTypes.FiducialResult fr : frs){
                telemetry.addData("ta: ", fr.getTargetArea());
            }
        }
    }

    double distSum = 0;
    double distCount = 0;
    double goalDistance = 1;
    LLResultTypes.FiducialResult closestGoal = null;
    double bestDiff = 1000;
    public double getGoalDistance() {
        if (isGoalDetected()) {
            closestGoal = frs.get(0);

            for (LLResultTypes.FiducialResult fr : frs) {
                if (fr.getTargetArea() > closestGoal.getTargetArea()) {
                    closestGoal = fr;
                }
            }

            goalDistance = closestGoal.getTargetPoseCameraSpace().getPosition().z;

        }
        return goalDistance;

    }

    double goalYaw = -1;
    public double getGoalYaw(){
        if (isGoalDetected()){
            closestGoal = frs.get(0);

            for (LLResultTypes.FiducialResult fr : frs) {
                if (fr.getTargetArea() > closestGoal.getTargetArea()) {
                    closestGoal = fr;
                }
            }

            goalYaw = closestGoal.getCameraPoseTargetSpace().getOrientation().getYaw();
        }

        return goalYaw;
    }

    public int choosePipeline(){
        if (getGoalDistance() > 2.8){
            if (alianceCol) return farRedPipeline;
            return farBluePipeline;
        }
        return closePipeline;
    }

    public void setAlianceCol(boolean alCol){
        alianceCol = alCol;
    }

    @Override
    public void periodic() {
        super.periodic();
        llResult = limelight.getLatestResult();
        frs = llResult.getFiducialResults();
        limelight.pipelineSwitch(choosePipeline());
    }

    //todo: Requires bot heading from pinpoint
//    public Pose2D getBotPose(){
//        if (llResult.getBotpose_MT2() != null){
//            Pose2D currentPos = new Pose2D(llResult.getBotpose_MT2().getPosition().x / 0.0254 ,llResult.getBotpose_MT2().getPosition().y / 0.0254);
//        }
//    }

    public void displayTelemetry(Telemetry telemetry){
        telemetry.addData("Current pipeline: ", choosePipeline());

        if(isGoalDetected()){
            telemetry.addData("Limelight distance: ", getGoalDistance());
            telemetry.addData("Limelight yaw: ", getGoalYaw());
        }
    }
}
