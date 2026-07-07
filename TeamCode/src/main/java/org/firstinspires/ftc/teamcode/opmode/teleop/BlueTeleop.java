package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.seattlesolvers.solverslib.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.geometry.Rotation2d;

import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.util.OpmodeData;

@TeleOp(name = "Blue Teleop", group = "Teleops")
public class BlueTeleop extends CommandOpMode {
    private TeleopTemplate template;
    private BarnRobot farminator;
    private OpmodeData opmodeData;

    @Override
    public void initialize() {
        farminator = BarnRobot.getInstance();
        farminator.init(this);

        template = new TeleopTemplate();
        template.initControls(false);

        opmodeData = new OpmodeData(
                OpmodeData.AllianceColor.BLUE,
                OpmodeData.OpModeType.TELEOP,
                new Pose2d(0, 0, new Rotation2d(0)),
                0);

        farminator.limelight.setAlianceCol(true);
    }

    @Override
    public void run() {
        super.run();

        telemetry.addLine("Blue telemetry works");
        farminator.limelight.displayTelemetry(telemetry);

        farminator.periodic();
    }
}
