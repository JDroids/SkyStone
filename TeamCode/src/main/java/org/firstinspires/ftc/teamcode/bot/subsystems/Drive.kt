package org.firstinspires.ftc.teamcode.bot.subsystems

import com.acmerobotics.roadrunner.control.PIDCoefficients
import com.acmerobotics.roadrunner.drive.MecanumDrive
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder
import com.acmerobotics.roadrunner.trajectory.constraints.DriveConstraints
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumConstraints
import com.disnodeteam.dogecommander.Subsystem
import com.qualcomm.hardware.bosch.BNO055IMU
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.init
import org.firstinspires.ftc.teamcode.robotHeadingRadians
import org.openftc.revextensions2.ExpansionHubEx
import org.openftc.revextensions2.ExpansionHubMotor
import org.openftc.revextensions2.RevBulkData
import kotlin.math.PI
import com.acmerobotics.roadrunner.followers.HolonomicPIDVAFollower
import com.acmerobotics.roadrunner.followers.PathFollower
import com.acmerobotics.roadrunner.followers.TrajectoryFollower


class Drive(private val hardwareMap: HardwareMap) :
        Subsystem, MecanumDrive(0.0, 0.0, 0.0, Drive.constraints.trackWidth) {
    val TICKS_PER_REVOLUTION = 537.6
    val WHEEL_RADIUS_ADJUSTED = 1.968504

    private val frontLeftMotor 
            by lazy { hardwareMap.get(ExpansionHubMotor::class.java, "frontLeft") }
    private val frontRightMotor
            by lazy { hardwareMap.get(ExpansionHubMotor::class.java, "frontRight") }
    private val backLeftMotor
            by lazy { hardwareMap.get(ExpansionHubMotor::class.java, "backLeft") }
    private val backRightMotor
            by lazy { hardwareMap.get(ExpansionHubMotor::class.java, "backRight") }

    private val imu by lazy {hardwareMap.get(BNO055IMU::class.java, "imu")}
    private val hub by lazy {hardwareMap.get(ExpansionHubEx::class.java, "Expansion Hub 3")}

    private var robotHeading = 0.0

    private lateinit var revBulkData: RevBulkData

    var motorPowers = DriveMotorPowers.STOP

    override fun initHardware() {
        // motors have to be accessed at least once to initialize them due to the nature of lazy
        // variables
        frontLeftMotor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        frontRightMotor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        backLeftMotor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        backRightMotor.mode = DcMotor.RunMode.RUN_USING_ENCODER

        // sets the left side to reverse so power of 1 to all motors goes forwards, not backwards
        frontLeftMotor.direction = DcMotorSimple.Direction.REVERSE
        backLeftMotor.direction = DcMotorSimple.Direction.REVERSE

        imu.init()

        // hub has to be accessed because of lazy
        revBulkData = hub.bulkInputData
    }

    override fun periodic() {
        frontLeftMotor.power = motorPowers.frontLeft
        frontRightMotor.power = motorPowers.frontRight
        backLeftMotor.power = motorPowers.backLeft
        backRightMotor.power = motorPowers.backRight

        robotHeading = imu.robotHeadingRadians
        revBulkData = hub.bulkInputData

        updatePoseEstimate()
    }

    override val rawExternalHeading: Double
        get() = robotHeading

    override fun getWheelPositions(): List<Double> =
            IntArray(4, {it}).
                    map {revBulkData.getMotorCurrentPosition(it)}.map { encoderTicksToInches(it) }

    override fun setMotorPowers(frontLeft: Double, rearLeft: Double, rearRight: Double, frontRight: Double) {
        this.motorPowers = DriveMotorPowers(
                frontLeft, frontRight, rearLeft, rearRight
        )
    }

    private fun encoderTicksToInches(ticks: Int): Double =
            WHEEL_RADIUS_ADJUSTED * PI * 2 * ticks / TICKS_PER_REVOLUTION

    data class DriveMotorPowers(val frontLeft: Double,
                                val frontRight: Double,
                                val backLeft: Double,
                                val backRight: Double) {
        constructor(speed: Double, strafe: Double, turn: Double) : this(
                speed + turn + strafe,
                speed - turn - strafe,
                speed + turn - strafe,
                speed - turn + strafe
        )

        operator fun times(double: Double) = DriveMotorPowers(
                frontLeft * double,
                frontRight * double,
                backLeft * double,
                backRight * double
        )

        operator fun div(double: Double) = DriveMotorPowers(
                frontLeft / double,
                frontRight / double,
                backLeft / double,
                backRight / double
        )

        companion object {
            val STOP = DriveMotorPowers(0.0, 0.0, 0.0, 0.0)
        }
    }

    companion object {
        val constraints = MecanumConstraints(
                DriveConstraints(90.0, 45.0, 0.0, 90.0, 45.0, 0.0),
                8.5
        )

        private val TRANSLATIONAL_PID = PIDCoefficients()
        private val HEADING_PID = PIDCoefficients()

        val follower: TrajectoryFollower =
                HolonomicPIDVAFollower(TRANSLATIONAL_PID, TRANSLATIONAL_PID, HEADING_PID)
    }

    val trajectoryBuilder
        get() = TrajectoryBuilder(poseEstimate, constraints)
}
