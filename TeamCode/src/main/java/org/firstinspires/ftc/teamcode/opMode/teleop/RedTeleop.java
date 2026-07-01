package org.firstinspires.ftc.teamcode.opMode.teleop;

import android.annotation.TargetApi;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.sun.tools.javac.comp.Todo;

import org.firstinspires.ftc.teamcode.BarnRobot;

import java.util.function.ToDoubleBiFunction;

@TeleOp(name = "Blue Teleop", group = "Teleops")
public class RedTeleop extends CommandOpMode {
    private TeleopTemplate template;
    private BarnRobot farminator;

    @Override
    public void initialize() {
        template = new TeleopTemplate();
        template.initControls();

        farminator = BarnRobot.getInstance();
        farminator.init(this);
    }

    @Override
    public void run() {
        super.run();

        telemetry.addLine("Red telemetry works");

        farminator.periodic();
    }
}
