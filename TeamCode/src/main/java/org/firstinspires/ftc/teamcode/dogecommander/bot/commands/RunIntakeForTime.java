package org.firstinspires.ftc.teamcode.dogecommander.bot.commands;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dogecommander.bot.subsystems.Intake;

public class RunIntakeForTime implements Command {
    private ElapsedTime timer;

    private Intake intake;
    private double seconds;
    private Intake.State state;

    public RunIntakeForTime(Intake intake, double seconds, Intake.State state) {
        timer = new ElapsedTime();

        this.intake = intake;
        this.seconds = seconds;
        this.state = state;
    }

    @Override
    public void start() {
        timer.reset();
        intake.setState(state);
    }

    @Override
    public void periodic() {

    }

    @Override
    public void stop() {
        intake.setState(Intake.State.STOP);
    }

    @Override
    public boolean isCompleted() {
        return timer.seconds() >= seconds;
    }
}
