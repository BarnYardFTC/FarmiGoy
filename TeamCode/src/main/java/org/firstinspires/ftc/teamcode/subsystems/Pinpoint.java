package org.firstinspires.ftc.teamcode.subsystems;

import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.BarnRobot;

public class Pinpoint {
    private final GoBildaPinpointDriver pinpoint;
    public Pinpoint() {
        pinpoint = BarnRobot.getInstance().hardware.pinpoint;
        pinpoint.resetPosAndIMU();
    }
    public void setPose(Pose pose){
        pinpoint.setPosition(new Pose2D(DistanceUnit.INCH, pose.getX(), pose.getY(), AngleUnit.RADIANS, pose.getHeading()));
    }

    public void reset(){
        pinpoint.resetPosAndIMU();
    }

    public Pose2D getPosition() {
        return pinpoint.getPosition();
    }

    public void update(){
        pinpoint.update();
    }
}
