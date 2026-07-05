package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
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
    public Shooter shooter;
    public Transfer transfer;
    public Kickstand kickstand;


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


    //farminator initialization

    public void init(OpMode opMode){

        this.hardware = new Hardware(opMode.hardwareMap);
        this.telemetry = opMode.telemetry;

        gamepadEx1 = new GamepadEx(opMode.gamepad1);

        initShooter();
        initGate();
        initIntake();
        initHood();
        initLimeLight();
        initDriveTrain();
        initTransfer();
        initKickstand();

    }


    //Subsystems initialization

    public void initGate(){
        gate = new Gate();
    }

    public void initShooter(){
        shooter = new Shooter();
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

    public void initTransfer() { transfer = new Transfer(); }

    public void initKickstand() { kickstand = new Kickstand(); }


    //Runs in a loop
    public void periodic() {
        telemetry.update();
    }
}