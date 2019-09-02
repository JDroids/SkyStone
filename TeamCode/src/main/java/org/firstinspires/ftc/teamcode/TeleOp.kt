package org.firstinspires.ftc.teamcode

import com.disnodeteam.dogecommander.DogeCommander
import com.disnodeteam.dogecommander.DogeOpMode
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.bot.Bot
import org.firstinspires.ftc.teamcode.bot.commands.TelemetryCommand
import org.firstinspires.ftc.teamcode.bot.commands.teleop.ArcadeDrive

@TeleOp
class TeleOp : LinearOpMode(), DogeOpMode {
    override fun runOpMode() {
        val commander = DogeCommander(this)
        val bot = Bot(hardwareMap)

        commander.setBot(bot)
        commander.init()

        waitForStart()

        commander.runCommandsParallel(
                ArcadeDrive(bot.drive, gamepad1),
                TelemetryCommand(telemetry, bot.drive)
        )

        commander.stop()
    }
}