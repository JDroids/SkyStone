package org.firstinspires.ftc.teamcode.bot.subsystems

import com.qualcomm.robotcore.hardware.HardwareMap
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock

// changing these will break the test if you don't change the constants in the test
private const val SPEED = 1.0
private const val STRAFE = 1.0
private const val TURN = 0.0

internal class MecanumDriveTest {
    @Test
    fun `MecanumDrive properly calculates motor powers (ignores turn)`() {
        val hardwareMap = mock(HardwareMap::class.java)
        val drive = MecanumDrive(hardwareMap)

        drive.setPowerForDirections(SPEED, STRAFE, TURN)

        assert(drive.motorPowers.frontLeft == 0.0)
        assert(drive.motorPowers.frontRight == MecanumDrive.MAX_POWER)
        assert(drive.motorPowers.backLeft == MecanumDrive.MAX_POWER)
        assert(drive.motorPowers.backRight == 0.0)
    }
}