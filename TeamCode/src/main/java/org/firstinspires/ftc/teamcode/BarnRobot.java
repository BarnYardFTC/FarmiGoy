package org.firstinspires.ftc.teamcode;

import com.seattlesolvers.solverslib.command.Robot;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.*;
import org.firstinspires.ftc.teamcode.util.Hardware;

public class BarnRobot extends Robot {

    //Singleton instance
    private static BarnRobot instance;


    //Subsystems

    public Gate gate;
    public Hood hood;


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

    public void initHood() {
        hood = new Hood();
    }


    //Runs in a loop
    public void periodic() {
        telemetry.update();
    }
}