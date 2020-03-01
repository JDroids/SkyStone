package org.firstinspires.ftc.teamcode.bot.subsystems

import android.util.Log
import com.acmerobotics.dashboard.config.Config
import com.disnodeteam.dogecommander.Subsystem
import com.qualcomm.robotcore.hardware.*
import org.firstinspires.ftc.teamcode.bot.subsystems.LiftConfig.LIFT_PID

class Deposit(private val hardwareMap: HardwareMap) : Subsystem {
    private val leftDeposit by lazy {hardwareMap.get(DcMotorEx::class.java, "depositExtensionLeft")}
    private val rightDeposit by lazy {hardwareMap.get(DcMotorEx::class.java, "depositExtensionRight")}
    private val liftMotors by lazy { listOf(leftDeposit, rightDeposit) }

    private val graspServo by lazy {hardwareMap.get(ServoImplEx::class.java, "graspServo") }
    private val fourBarServo by lazy {hardwareMap.get(ServoImplEx::class.java, "fourBarServo") }

    private var tickOffsetLeft = 0
    private var tickOffsetRight = 0

    var height: Double = 0.0
        private set

    var targetHeight = 0.0

    enum class VirtualFourBarState(val servoPosition: Double) {
        IN(0.0),
        OUT(0.7)
    }

    enum class GraspState(val servoPosition: Double) {
        OPEN(0.2),
        CLOSED(0.7)
    }

    var fourBarState = VirtualFourBarState.IN
    var graspState = GraspState.OPEN

    override fun initHardware() {
        leftDeposit
        rightDeposit.direction = DcMotorSimple.Direction.REVERSE

        liftMotors.forEach {
            it.apply {
                setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, LIFT_PID)
                mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
                this.targetPosition = 0
                mode = DcMotor.RunMode.RUN_TO_POSITION
            }
        }
        reset()
    }

    override fun periodic() {
        targetHeight = if (targetHeight > 0) targetHeight else 0.0

        val targetPosition = heightToEncoderTicks(targetHeight).toInt()

        leftDeposit.targetPosition = targetPosition - tickOffsetLeft
        rightDeposit.targetPosition = targetPosition - tickOffsetRight

        liftMotors.forEach {
            it.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, LIFT_PID)
            it.power = 0.8
        }

        fourBarServo.position = fourBarState.servoPosition
        graspServo.position = graspState.servoPosition
    }

    private fun reset() {
        tickOffsetLeft = leftDeposit.currentPosition
        tickOffsetRight = rightDeposit.currentPosition
    }

    private fun encoderTicksToHeight(ticks: Double) = Math.PI * 1.57 * ticks/537.6

    private fun heightToEncoderTicks(height: Double) = (537.6 * height) / (Math.PI * 1.57)

}

@Config
object LiftConfig {
    @JvmField var LIFT_PID = PIDFCoefficients(3.0, 0.0, 0.0, 0.0)
}
