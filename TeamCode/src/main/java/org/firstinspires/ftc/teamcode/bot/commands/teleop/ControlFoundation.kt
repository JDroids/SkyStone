package org.firstinspires.ftc.teamcode.bot.commands.teleop

import com.disnodeteam.dogecommander.Command
import com.qualcomm.robotcore.hardware.Gamepad
import org.firstinspires.ftc.teamcode.bot.subsystems.FoundationMechanism

class ControlFoundation(private val foundationMechanism: FoundationMechanism,
                        private val gamepad: Gamepad) : Command {
    override fun start() {}

    override fun periodic() {
        foundationMechanism.state = when {
            gamepad.dpad_down -> FoundationMechanism.State.DEPLOYED
            gamepad.dpad_up -> FoundationMechanism.State.RETRACTED
            else -> foundationMechanism.state
        }
    }

    override fun stop() {
        foundationMechanism.state = FoundationMechanism.State.RETRACTED
    }

    override fun isCompleted() = false
}