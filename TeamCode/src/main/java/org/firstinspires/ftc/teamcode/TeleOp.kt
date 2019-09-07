package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.bot.commands.TelemetryCommand
import org.firstinspires.ftc.teamcode.bot.commands.teleop.ArcadeDrive

@TeleOp
class TeleOp : OpModeTemplate() {
    override fun initOpMode() {}

    override fun run() {
        commander.runCommandsParallel(
                ArcadeDrive(drive, gamepad1),
                TelemetryCommand(telemetry, drive)
        )
    }
}