package org.firstinspires.ftc.teamcode.bot.commands.teleop

import com.disnodeteam.dogecommander.Command
import com.qualcomm.robotcore.hardware.Gamepad
import org.firstinspires.ftc.teamcode.bot.subsystems.Drive
import org.firstinspires.ftc.teamcode.bot.subsystems.Drive.DriveMotorPowers

class ArcadeDrive(private val drive: Drive, private val gamepad: Gamepad) : Command {
    override fun start() {
        drive.motorPowers = Drive.DriveMotorPowers.STOP
    }

    override fun periodic() {
        drive.motorPowers = DriveMotorPowers(
                -gamepad.left_stick_y.toDouble(),
                -gamepad.left_stick_x.toDouble(),
                -gamepad.right_stick_x.toDouble()
        )
    }

    override fun stop() {
        drive.motorPowers = DriveMotorPowers.STOP
    }

    override fun isCompleted(): Boolean = false
}