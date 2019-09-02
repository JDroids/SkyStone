package org.firstinspires.ftc.teamcode.bot.subsystems

import com.disnodeteam.dogecommander.Subsystem
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.util.Range
import kotlin.math.max

class MecanumDrive(private val hardwareMap: HardwareMap) : Subsystem {
    var periodicCalls = 0
        private set

    private val frontLeftMotor: DcMotorEx
            by lazy { hardwareMap.get(DcMotorEx::class.java, "frontLeft") }
    private val frontRightMotor: DcMotorEx
            by lazy { hardwareMap.get(DcMotorEx::class.java, "frontRight") }
    private val backLeftMotor: DcMotorEx
            by lazy { hardwareMap.get(DcMotorEx::class.java, "backLeft") }
    private val backRightMotor: DcMotorEx
            by lazy { hardwareMap.get(DcMotorEx::class.java, "backRight") }

    var motorPowers = DriveMotorPowers(0.0, 0.0, 0.0, 0.0)
        set(value) {
            field = value.normalized()
        }

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
        frontRightMotor.power = motorPowers.frontRight
        backLeftMotor.power = motorPowers.backLeft
        backRightMotor.power = motorPowers.backRight

        periodicCalls++
    }


    data class DriveMotorPowers(val frontLeft: Double,
                                val frontRight: Double,
                                val backLeft: Double,
                                val backRight: Double) {
        fun normalized() = this * MAX_POWER / maxOfPowers()

        constructor(speed: Double, strafe: Double, turn: Double) : this(
                speed + turn - strafe,
                speed - turn + strafe,
                speed + turn + strafe,
                speed - turn - strafe
        )

        private operator fun times(double: Double) = DriveMotorPowers(
                frontLeft * double,
                frontRight * double,
                backLeft * double,
                backRight * double
        )

        private operator fun div(double: Double) = this * (1/double)

        private fun maxOfPowers() = max(max(frontLeft, frontRight), max(backLeft, backRight))

        companion object {
            val STOP = DriveMotorPowers(0.0, 0.0, 0.0, 0.0)

            val MAX_POWER = 0.9
        }
    }
}
