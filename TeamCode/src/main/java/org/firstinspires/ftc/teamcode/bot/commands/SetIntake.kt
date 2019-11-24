package org.firstinspires.ftc.teamcode.bot.commands

import com.disnodeteam.dogecommander.Command
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.bot.subsystems.Intake

class SetIntake(private val intake: Intake, private val state: Intake.State,
                     private val time: Double) : Command {
    private val timer = ElapsedTime()

    override fun start() {
        // intake.deploy()
        intake.state = state
    }

    override fun periodic() {}

    override fun stop() {
        intake.state = Intake.State.STOP
    }

    override fun isCompleted() = timer.seconds() >= time
}