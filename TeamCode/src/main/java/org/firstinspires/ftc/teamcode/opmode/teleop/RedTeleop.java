package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.BarnRobot;

@TeleOp(name = "Red Teleop", group = "Teleops")
public class RedTeleop extends CommandOpMode {
    private BarnRobot farminator;
    @Override
    public void initialize() {
        farminator = BarnRobot.getInstance();
        farminator.init(this);
        TeleopTemplate template = new TeleopTemplate();
    }

    @Override
    public void run() {
        super.run();
        farminator.periodic();
    }
}
