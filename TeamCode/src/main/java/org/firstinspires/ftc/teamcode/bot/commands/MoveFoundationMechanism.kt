package org.firstinspires.ftc.teamcode.bot.commands

import com.disnodeteam.dogecommander.Command
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.bot.subsystems.FoundationMechanism

class MoveFoundationMechanism(private val mechanism: FoundationMechanism,
                              private val state: FoundationMechanism.State) : Command {
    private val timeToWait = 0.5
    private val timer = ElapsedTime()

    override fun start() {
        mechanism.state = state
        timer.reset()
    }

    override fun periodic() {}

    override fun stop() {}

    override fun isCompleted(): Boolean {
        return timer.seconds() > timeToWait
    }
}