package org.firstinspires.ftc.teamcode.bot.commands

import com.disnodeteam.dogecommander.Command
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.bot.subsystems.MecanumDrive

class TelemetryCommand(private val telemetry: Telemetry,
                       private val drive: MecanumDrive) : Command {
    override fun start() {

    }

    override fun periodic() {
        telemetry.addData("Motor Powers", drive.motorPowers)
        telemetry.addData("Periodic Calls", drive.periodicCalls)
        telemetry.update()
    }

    override fun stop() {

    }

    override fun isCompleted(): Boolean = false
}