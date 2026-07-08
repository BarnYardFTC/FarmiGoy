package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.seattlesolvers.solverslib.command.Robot;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.*;
import org.firstinspires.ftc.teamcode.util.Hardware;

public class BarnRobot extends Robot {

    private static BarnRobot instance;

    public MechanumDrivetrain drive;
    public Gate gate;
    public Hood hood;
    public Intake intake;
    public Pinpoint pinpoint;
    public Shooter shooter;
    public Transfer transfer;
    public Kickstand kickstand;

    public GamepadEx gamepadEx1;
    public Telemetry telemetry;

    public Hardware hardware;

    public static boolean isRobotInitialized = false;

    public static synchronized BarnRobot getInstance() {
        if (instance == null) {
            instance = new BarnRobot();
            isRobotInitialized = true;
        }
        return instance;
    }

    public void init(OpMode opMode){
        this.hardware = new Hardware(opMode.hardwareMap);
        this.telemetry = opMode.telemetry;
        gamepadEx1 = new GamepadEx(opMode.gamepad1);
        initShooter();
        initGate();
        initIntake();
        initHood();
        initPinpoint();
        initDriveTrain();
        initTransfer();
        initKickstand();

    }

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

    public void initPinpoint() {
        pinpoint = new Pinpoint();
    }

    public void initDriveTrain(){
        drive = new MechanumDrivetrain();
    }

    public void initTransfer() { transfer = new Transfer(); }

    public void initKickstand() { kickstand = new Kickstand(); }


    //Runs in a loop
    public void periodic() {
        telemetry.update();
        pinpoint.update();
    }
}