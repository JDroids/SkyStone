package org.firstinspires.ftc.teamcode.bot.commands.teleop

import com.qualcomm.robotcore.hardware.Gamepad
import org.firstinspires.ftc.teamcode.bot.subsystems.Drive
import org.firstinspires.ftc.teamcode.bot.subsystems.Drive.DriveMotorPowers
import org.junit.Test
import org.mockito.Mockito.*

private const val SPEED = 1.0
private const val STRAFE = 1.0
private const val TURN = 0.0

internal class ArcadeDriveTest {
    @Test
    fun `ArcadeDrive passes the correct values through to MecanumDrive`() {
        val gamepad = mock(Gamepad::class.java)

        gamepad.left_stick_y = -SPEED.toFloat()
        gamepad.left_stick_x = STRAFE.toFloat()
        gamepad.right_stick_x = TURN.toFloat()

        val drive = mock(Drive::class.java)

        val command = ArcadeDrive(drive, gamepad)

        command.start()
        verify(drive).motorPowers = DriveMotorPowers.STOP

        command.periodic()
        verify(drive).motorPowers = DriveMotorPowers(SPEED, STRAFE, TURN)

        command.stop()
        verify(drive, atLeast(2)).motorPowers = DriveMotorPowers.STOP
    }
}