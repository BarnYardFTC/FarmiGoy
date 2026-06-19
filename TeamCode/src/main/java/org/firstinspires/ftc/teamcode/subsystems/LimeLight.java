package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

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

    double oldTa = 0;
    double avgArea = 0;
    double stability = 0;

    double goalDistance = 1;
    LLResultTypes.FiducialResult closestGoal = null;
    double bestDiff = 1000;
    public double goalDistanceV1() {
        if (isGoalDetected()) {
            closestGoal = frs.get(0);

            for (LLResultTypes.FiducialResult fr : frs) {
                if (goalStabCheck(oldTa, fr.getTargetArea()) > 500) {
                    avgArea /= stability;

                    if (fr.getTargetArea() - avgArea < bestDiff) {
                        bestDiff = fr.getTargetArea() - avgArea;
                        closestGoal = fr;
                    }
                }

                else if (fr.getTargetArea() > closestGoal.getTargetArea()) {
                    closestGoal = fr;
                }

                oldTa = fr.getTargetArea();

            }
            goalDistance = closestGoal.getCameraPoseTargetSpace().getPosition().z;
        }

        return goalDistance;
    }

    public double goalStabCheck(double oldTa, double nextTa){
        if (nextTa - oldTa < 0.2){
            stability ++;
            avgArea += oldTa;
        }
        else stability = 0;

        return stability;
    }




}
