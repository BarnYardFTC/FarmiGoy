package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

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


    public LimeLight(){
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
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


}
