package org.firstinspires.ftc.teamcode.bot.commands.teleop

import com.disnodeteam.dogecommander.Command
import com.qualcomm.robotcore.hardware.Gamepad
import org.firstinspires.ftc.teamcode.bot.subsystems.MecanumDrive

class ArcadeDrive(private val drive: MecanumDrive, private val gamepad: Gamepad) : Command {
    override fun start() {
        drive.setPowerForDirections(0.0, 0.0, 0.0)
    }

    override fun periodic() {
        drive.setPowerForDirections(-gamepad.left_stick_y.toDouble(),
                gamepad.left_stick_x.toDouble(),
                gamepad.right_stick_x.toDouble()
        )
    }

    override fun stop() {
        drive.setPowerForDirections(0.0, 0.0, 0.0)
    }

    override fun isCompleted(): Boolean = false
}