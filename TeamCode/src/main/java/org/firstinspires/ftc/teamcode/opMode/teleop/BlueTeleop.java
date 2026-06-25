package org.firstinspires.ftc.teamcode.opMode.teleop;

import android.annotation.TargetApi;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.sun.tools.javac.comp.Todo;

import org.firstinspires.ftc.teamcode.BarnRobot;

import java.util.function.ToDoubleBiFunction;

@TeleOp(name = "Blue Teleop maun", group = "Teleops")
public class BlueTeleop extends CommandOpMode {

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

        telemetry.addLine("Blue telemetry works");

        farminator.periodic();
    }
}
