package org.firstinspires.ftc.teamcode.bot.commands.teleop

import com.disnodeteam.dogecommander.Command
import com.qualcomm.robotcore.hardware.Gamepad
import org.firstinspires.ftc.teamcode.bot.subsystems.MecanumDrive
import org.firstinspires.ftc.teamcode.bot.subsystems.MecanumDrive.DriveMotorPowers

class ArcadeDrive(private val drive: MecanumDrive, private val gamepad: Gamepad) : Command {
    override fun start() {
        drive.motorPowers = DriveMotorPowers.STOP
    }

    override fun periodic() {
        drive.motorPowers = DriveMotorPowers(
                -gamepad.left_stick_y.toDouble(),
                gamepad.left_stick_x.toDouble(),
                gamepad.right_stick_x.toDouble()
        )

        drive.periodic() // bad idea, should be handled by DogeCommander, fix it idiot
    }

    override fun stop() {
        drive.motorPowers = DriveMotorPowers.STOP
    }

    override fun isCompleted(): Boolean = false
}