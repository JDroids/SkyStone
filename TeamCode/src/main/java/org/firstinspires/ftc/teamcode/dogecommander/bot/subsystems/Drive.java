package org.firstinspires.ftc.teamcode.dogecommander.bot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drive implements Subsystem {
    private HardwareMap hardwareMap;

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    private double leftPower = 0;
    private double rightPower = 0;

    public Drive(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    public void setPower(double leftPower, double rightPower) {
        this.leftPower = leftPower;
        this.rightPower = rightPower;
    }

    @Override
    public void initHardware() {
        leftMotor = hardwareMap.get(DcMotor.class, "m1");
        rightMotor = hardwareMap.get(DcMotor.class, "m2");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void periodic() {
        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
    }
}
