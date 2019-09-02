package org.firstinspires.ftc.teamcode

import com.disnodeteam.dogecommander.DogeCommander
import com.disnodeteam.dogecommander.DogeOpMode
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.bot.commands.TelemetryCommand
import org.firstinspires.ftc.teamcode.bot.commands.teleop.ArcadeDrive

@TeleOp
class TeleOp : OpModeTemplate() {
    override fun run() {
        waitForStart()

        commander.runCommandsParallel(
                ArcadeDrive(drive, gamepad1),
                TelemetryCommand(telemetry, drive)
        )

        commander.stop()
    }
}