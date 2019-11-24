package org.firstinspires.ftc.teamcode.bot.commands.teleop

import com.disnodeteam.dogecommander.Command
import com.qualcomm.robotcore.hardware.Gamepad
import org.firstinspires.ftc.teamcode.bot.subsystems.Intake

class ControlIntake(private val intake: Intake, private val gamepad: Gamepad) : Command {
    override fun start() {
        // intake.deploy()
    }

    override fun periodic() {
        intake.state = when {
            gamepad.left_bumper -> Intake.State.FORWARD
            gamepad.right_bumper -> Intake.State.REVERSE
            else -> Intake.State.STOP
        }
    }

    override fun stop() {
        intake.state = Intake.State.STOP
    }

    override fun isCompleted() = false
}