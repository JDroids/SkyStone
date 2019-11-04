package org.firstinspires.ftc.teamcode.bot.subsystems

import com.qualcomm.robotcore.hardware.HardwareMap
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.firstinspires.ftc.teamcode.bot.subsystems.Drive.DriveMotorPowers

internal class MecanumDriveTest {
    @Test
    fun `MecanumDrive properly calculates motor powers`() {
        val SPEED = 1.0
        val STRAFE = 1.0
        val TURN = 1.0

        val hardwareMap = mock(HardwareMap::class.java)
        val drive = Drive(hardwareMap)

        drive.motorPowers = DriveMotorPowers(SPEED, STRAFE, TURN)

        assert(drive.motorPowers.frontLeft == DriveMotorPowers.MAX_POWER/3)
        assert(drive.motorPowers.frontRight == DriveMotorPowers.MAX_POWER/3)
        assert(drive.motorPowers.backLeft == DriveMotorPowers.MAX_POWER)
        assert(drive.motorPowers.backRight == -DriveMotorPowers.MAX_POWER/3)
    }
}