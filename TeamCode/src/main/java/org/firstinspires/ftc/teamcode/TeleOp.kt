package org.firstinspires.ftc.teamcode

import com.disnodeteam.dogecommander.DogeOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.bot.commands.teleop.ArcadeDrive

@TeleOp
class TeleOp : OpModeTemplate(), DogeOpMode {
    override fun runOpMode() {
        initDogeCommander()

        waitForStart()

        commander.runCommand(
                ArcadeDrive(bot.drive, gamepad1     )
        )

        commander.stop()
    }
}