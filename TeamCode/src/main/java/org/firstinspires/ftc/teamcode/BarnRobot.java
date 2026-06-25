package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.seattlesolvers.solverslib.command.Robot;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.*;
import org.firstinspires.ftc.teamcode.util.Hardware;

public class BarnRobot extends Robot {

    //Singleton instance
    private static BarnRobot instance;


    //Subsystems

    public Drivetrain drive;
    public Gate gate;
    public Hood hood;
    public Intake intake;
    public LimeLight limelight;


    //Gamepads
    public GamepadEx gamepadEx1;
    public GamepadEx gamepadEx2;


    //Telemetry
    public Telemetry telemetry;


    //Robot Hardware
    public Hardware hardware;


    //Robot Data
    public static boolean isRobotInitialized = false;
    //TODO: opmode data


    //Singleton Accessor
    public static synchronized BarnRobot getInstance() {
        if (instance == null) {
            instance = new BarnRobot();
            isRobotInitialized = true;
        }
        return instance;
    }


    //TODO: init


    //Subsystems initialisation

    public void initGate(){
        gate = new Gate();
    }

    public void initIntake(){
        intake = new Intake();
    }

    public void initHood() {
        hood = new Hood();
    }
    public void initLimeLight(){
        limelight = new LimeLight();
    }

    public void initDriveTrain(){
        drive = new Drivetrain();
    }


    //Runs in a loop
    public void periodic() {
        telemetry.update();
    }
}