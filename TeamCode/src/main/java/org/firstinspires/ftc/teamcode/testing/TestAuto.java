package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.util.OpmodeData;

@Autonomous(name="TestAuto", group="test")
public class TestAuto extends CommandOpMode {
    BarnRobot farminator;

    private final OpmodeData opmodeData = new OpmodeData(
            OpmodeData.AllianceColor.BLUE,
            OpmodeData.OpModeType.AUTONOMOUS,
            new Pose2d(0, 0, 0)
    );

    @Override
    public void initialize() {
        farminator = BarnRobot.getInstance();
        farminator.init(this);
    }
}
