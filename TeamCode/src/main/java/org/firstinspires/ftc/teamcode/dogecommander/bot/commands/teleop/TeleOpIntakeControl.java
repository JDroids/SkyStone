package org.firstinspires.ftc.teamcode.dogecommander.bot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.dogecommander.bot.subsystems.Intake;

public class TeleOpIntakeControl implements Command {
    private Intake intake;
    private Gamepad gamepad;

    public TeleOpIntakeControl(Intake intake, Gamepad gamepad) {
        this.intake = intake;
        this.gamepad = gamepad;
    }

    @Override
    public void start() {
        intake.setState(Intake.State.STOP);
    }

    @Override
    public void periodic() {
        if (gamepad.right_bumper) {
            intake.setState(Intake.State.INTAKE);
        }
        else if (gamepad.left_bumper) {
            intake.setState(Intake.State.SPIT_OUT);
        }
        else {
            intake.setState(Intake.State.STOP);
        }
    }

    @Override
    public void stop() {
        intake.setState(Intake.State.STOP);
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
