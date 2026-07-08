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
    @Override
    public void initialize() {
        farminator = BarnRobot.getInstance();
        farminator.init(this, new OpmodeData(OpmodeData.AllianceColor.BLUE, 0));

        template = new TeleopTemplate();
        template.initControls(false, hardwareMap);
    }

    @Override
    public void run() {
        super.run();

        telemetry.addLine("Blue telemetry works");
        farminator.limelight.displayTelemetry(telemetry);

        farminator.periodic();
    }
}
