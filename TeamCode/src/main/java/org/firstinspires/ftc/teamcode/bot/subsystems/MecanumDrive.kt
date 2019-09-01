package org.firstinspires.ftc.teamcode.bot.subsystems

import com.disnodeteam.dogecommander.Subsystem
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.util.Range
import kotlin.math.abs

class MecanumDrive(private val hardwareMap: HardwareMap) : Subsystem {
    private val frontLeftMotor: DcMotorEx
            by lazy { hardwareMap.get(DcMotorEx::class.java, "frontLeft") }
    private val frontRightMotor: DcMotorEx
            by lazy { hardwareMap.get(DcMotorEx::class.java, "frontRight") }
    private val backLeftMotor: DcMotorEx
            by lazy { hardwareMap.get(DcMotorEx::class.java, "backLeft") }
    private val backRightMotor: DcMotorEx
            by lazy { hardwareMap.get(DcMotorEx::class.java, "backRight") }

    var motorPowers = DriveMotorPowers(0.0, 0.0, 0.0, 0.0)
        private set

    fun setPowerForDirections(speed: Double, strafe: Double, turn: Double) {
        val magnitude = abs(speed) + (strafe) + (turn)

        motorPowers = DriveMotorPowers(
                scale(speed + turn - strafe, magnitude),
                scale(speed - turn + strafe, magnitude),
                scale(speed + turn + strafe, magnitude),
                scale(speed - turn - strafe, magnitude)
        )
    }


    private fun scale(value: Double, magnitude: Double): Double =
            Range.scale(value, -magnitude, +magnitude, -MAX_POWER, +MAX_POWER)

    override fun initHardware() {
        // motors have to be accessed at least once to initialize them due to the nature of lazy
        // variables
        frontLeftMotor
        frontRightMotor
        backLeftMotor
        backRightMotor

        // sets the right side to reverse so power of 1 to all motors goes forwards, not backwards
        backLeftMotor.direction = DcMotorSimple.Direction.REVERSE
        backRightMotor.direction = DcMotorSimple.Direction.REVERSE
    }

    override fun periodic() {
        frontLeftMotor.power = motorPowers.frontLeft
        frontLeftMotor.power = motorPowers.frontRight
        frontLeftMotor.power = motorPowers.frontLeft
        frontLeftMotor.power = motorPowers.frontLeft
    }


    data class DriveMotorPowers(val frontLeft: Double,
                                val frontRight: Double,
                                val backLeft: Double,
                                val backRight: Double)

    companion object {
        val MAX_POWER = 0.9
    }
}