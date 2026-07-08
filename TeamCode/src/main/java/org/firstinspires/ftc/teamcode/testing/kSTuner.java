package org.firstinspires.ftc.teamcode.testing;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.BarnRobot;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.util.OpmodeData;

@Configurable
@Disabled
@TeleOp (name = "Shooter kS Tuner", group = "tuning")
public class kSTuner extends CommandOpMode {

    public static double kS = 0;
    BarnRobot farmiGoy;
    public void initialize() {
        farmiGoy = BarnRobot.getInstance();
        farmiGoy.init(this, new OpmodeData());



    }

    @Override
    public void run() {
        super.run();
        farmiGoy.shooter.setMotorPower(kS);
        farmiGoy.telemetry.addData("Ks", kS);
        farmiGoy.telemetry.addData("rpm", farmiGoy.shooter.getRPM());
        farmiGoy.shooter.updateRPM();

        farmiGoy.periodic();
    }
}
