package org.firstinspires.ftc.teamcode.testing;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.BarnRobot;

@Configurable
@Disabled
@TeleOp(name = "Shooter kP Tuner", group = "tuning")
public class kPTuner extends CommandOpMode {

    public static double kV = 0.000175;

    public static double kP = 0.0;

    private double kS = 0.09;

    public static int goalRPM = 3030;
    BarnRobot farmiGoy;
    public void initialize() {
        farmiGoy = BarnRobot.getInstance();
        farmiGoy.init(this);
    }

    @Override
    public void run() {
        super.run();

        if (gamepad1.dpad_up) {
            goalRPM = 3030;
        } else if (gamepad1.dpad_down) {
            goalRPM = 1515;
        }

        double feedForward = kV * goalRPM + kS;
        double error = goalRPM - farmiGoy.shooter.getRPM();
        double feedback = error * kP;

        farmiGoy.shooter.setMotorPower(feedForward + feedback);

        farmiGoy.telemetry.addData("kP", "%.6f", kP);
        farmiGoy.telemetry.addData("Error", error);
        farmiGoy.telemetry.addData("rpm", farmiGoy.shooter.getRPM());

        farmiGoy.shooter.updateRPM();

        farmiGoy.periodic();
    }
}

