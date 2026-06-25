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
    private BarnRobot farminator = BarnRobot.getInstance();

    @Override
    public void initialize() {
        // TODO: init staff related to OpModeData

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
