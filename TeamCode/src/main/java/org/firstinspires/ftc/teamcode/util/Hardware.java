package org.firstinspires.ftc.teamcode.util;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {

    private HardwareMap hwMap = hardwareMap;

    //Hardware names from driver hub configuration

    private static final String LEFT_FRONT_TRANSFER_CONFIG_NAME = "leftFrontTransfer";
    private static final String RIGHT_FRONT_TRANSFER_CONFIG_NAME = "rightFrontTransfer";
    private static final String LEFT_BACK_TRANSFER_CONFIG_NAME = "leftBackTransfer";
    private static final String RIGHT_BACK_TRANSFER_CONFIG_NAME = "rightBackTransfer";

    private static final String LEFT_FRONT_DRIVETRAIN_CONFIG_NAME = "leftFrontDrivetrain";
    private static final String RIGHT_FRONT_DRIVETRAIN_CONFIG_NAME = "rightFrontDrivetrain";
    private static final String LEFT_BACK_DRIVETRAIN_CONFIG_NAME = "leftBackDrivetrain";
    private static final String RIGHT_BACK_DRIVETRAIN_CONFIG_NAME = "rightBackDrivetrain";

    private static final String RIGHT_KICKSTAND_CONFIG_NAME = "kickStandRight";
    private static final String LEFT_KICKSTAND_CONFIG_NAME = "kickStandLeft";

    private static final String SHOOTER_RIGHT_CONFIG_NAME = "shooterRight";
    private static final String INTAKE_CONFIG_NAME = "intake";
    private static final String SHOOTER_LEFT_CONFIG_NAME = "shooterLeft";

    private static final String SHOOTER_HOOD_CONFIG_NAME = "shooterHood";

    private static final String BLINKIN_CONFIG_NAME = "blinkin";

    private static final String RIGHT_GATE_CONFIG_NAME = "rightGate";
    private static final String LEFT_GATE_CONFIG_NAME = "leftGate";

    private static final String TRANSFER_CONFIG_NAME = "transfer";


    //Hardware objects

    public Servo leftGateServo;
    public Servo rightGateServo;

    public Servo rightKickStand;
    public Servo leftKickStand;

    public Servo hoodServo;

    public Limelight3A limeLight;

    public DcMotorEx shooterMotorLeft;
    public DcMotorEx shooterMotorRight;
    public DcMotor intakeMotor;

    public DcMotor leftFrontDrivetrain;
    public DcMotor rightFrontDrivetrain;
    public DcMotor leftBackDrivetrain;
    public DcMotor rightBackDrivetrain;

    public DcMotor transfer;


    //Constructor

    public Hardware(HardwareMap hwMap) {
        this.hwMap = hwMap; //provided by the opmode

        initMotors();
        initServos();
        initSensors();
    }

    private void initMotors() {
        intakeMotor = hwMap.get(DcMotorEx.class, INTAKE_CONFIG_NAME);

        shooterMotorLeft = hwMap.get(DcMotorEx.class, SHOOTER_LEFT_CONFIG_NAME);
        shooterMotorRight = hwMap.get(DcMotorEx.class, SHOOTER_RIGHT_CONFIG_NAME);

        leftFrontDrivetrain = hwMap.get(DcMotor.class, LEFT_FRONT_DRIVETRAIN_CONFIG_NAME);
        rightFrontDrivetrain = hwMap.get(DcMotor.class, RIGHT_FRONT_DRIVETRAIN_CONFIG_NAME);
        leftBackDrivetrain = hwMap.get(DcMotor.class, LEFT_BACK_DRIVETRAIN_CONFIG_NAME);
        rightBackDrivetrain = hwMap.get(DcMotor.class, RIGHT_BACK_DRIVETRAIN_CONFIG_NAME);

        transfer = hwMap.get(DcMotor.class, TRANSFER_CONFIG_NAME);
    }

    private void initServos() {
        leftGateServo = hwMap.get(Servo.class, LEFT_GATE_CONFIG_NAME);
        rightGateServo = hwMap.get(Servo.class, RIGHT_GATE_CONFIG_NAME);

        hoodServo = hwMap.get(Servo.class, SHOOTER_HOOD_CONFIG_NAME);

        rightKickStand = hwMap.get(Servo.class, RIGHT_KICKSTAND_CONFIG_NAME);
        leftKickStand = hwMap.get(Servo.class, LEFT_KICKSTAND_CONFIG_NAME);
    }

    private void initSensors(){
        limeLight = hwMap.get(Limelight3A.class, "limelight");
    }
}