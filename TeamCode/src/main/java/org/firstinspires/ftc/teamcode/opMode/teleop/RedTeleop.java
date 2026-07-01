package org.firstinspires.ftc.teamcode.opMode.teleop;

import android.annotation.TargetApi;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.sun.tools.javac.comp.Todo;

import org.firstinspires.ftc.teamcode.BarnRobot;

import java.util.function.ToDoubleBiFunction;

@TeleOp(name = "Red Teleop", group = "Teleops")
public class RedTeleop extends CommandOpMode {
    private TeleopTemplate template;
    private BarnRobot farminator;

    @Override
    public void initialize() {
        farminator = BarnRobot.getInstance();
        farminator.init(this);

        template = new TeleopTemplate();
        template.initControls();
    }

    @Override
    public void run() {
        super.run();

        telemetry.addLine("Red telemetry works");

        farminator.periodic();
    }
}
