package org.firstinspires.ftc.teamcode.dogecommander.bot;

import com.disnodeteam.dogecommander.DogeBot;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.dogecommander.bot.subsystems.Drive;
import org.firstinspires.ftc.teamcode.dogecommander.bot.subsystems.Intake;

public class Bot extends DogeBot {
    public Drive drive;
    public Intake intake;

    public Bot(HardwareMap hardwareMap) {
        drive = new Drive(hardwareMap);
        intake = new Intake(hardwareMap);

        addSubsystem(drive);
        addSubsystem(intake);
    }
}
