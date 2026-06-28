package org.firstinspires.ftc.teamcode.testing;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.BarnRobot;

@Configurable
@TeleOp(name = "Shooter kV Tuner", group = "tuning")
public class kVTuner extends CommandOpMode {

    public static double kV = 0;

    private double kS = 0.1;

    public static int goalRPM = 3030;
    BarnRobot farmiGoy;
    public void initialize() {
        farmiGoy = BarnRobot.getInstance();
        farmiGoy.init(this);
    }

    @Override
    public void run() {
        super.run();

        double power;

        power = kV * goalRPM + kS;

        farmiGoy.shooter.setMotorPower(power);
        farmiGoy.telemetry.addData("kV", kV);
        farmiGoy.telemetry.addData("rpm", farmiGoy.shooter.getRPM());
        farmiGoy.shooter.updateRPM();

        farmiGoy.periodic();
    }
}

