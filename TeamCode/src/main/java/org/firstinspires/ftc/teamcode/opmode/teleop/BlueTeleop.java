package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.BarnRobot;

@TeleOp(name = "Blue Teleop", group = "Teleops")
public class BlueTeleop extends CommandOpMode {
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
