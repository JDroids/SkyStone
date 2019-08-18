package org.firstinspires.ftc.teamcode.dogecommander.bot.commands;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dogecommander.bot.subsystems.Drive;

public class DriveForTime implements Command {
    private ElapsedTime timer;

    private Drive drive;
    private double seconds;
    private double power;

    public DriveForTime(Drive drive, double seconds, double power) {
        timer = new ElapsedTime();

        this.drive = drive;
        this.seconds = seconds;
        this.power = power;
    }

    @Override
    public void start() {
        timer.reset();
        drive.setPower(power, power);
    }

    @Override
    public void periodic() {

    }

    @Override
    public void stop() {
        drive.setPower(0, 0);
    }

    @Override
    public boolean isCompleted() {
        return timer.seconds() > seconds;
    }
}
