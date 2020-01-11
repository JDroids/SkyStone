package org.firstinspires.ftc.teamcode.bot.subsystems

import android.util.Log
import com.disnodeteam.dogecommander.DogeOpMode
import com.disnodeteam.dogecommander.Subsystem
import com.qualcomm.hardware.rev.Rev2mDistanceSensor
import com.qualcomm.robotcore.hardware.*
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit

class Intake(private val opMode: DogeOpMode, private val hardwareMap: HardwareMap, private var shouldDeploy: Boolean) : Subsystem {
    private val leftMotor by lazy {hardwareMap.get(DcMotorEx::class.java, "intakeMotorLeft")}
    private val rightMotor by lazy {hardwareMap.get(DcMotorEx::class.java, "intakeMotorRight")}
    // private val leftServo by lazy {hardwareMap.get(CRServoImplEx::class.java, "intakeServoLeft")}
    // private val rightServo by lazy {hardwareMap.get(CRServoImplEx::class.java, "intakeServoRight")}

    private val leftDeployServo by lazy {hardwareMap.get(ServoImplEx::class.java, "hookLeft")}
    private val rightDeployServo by lazy {hardwareMap.get(ServoImplEx::class.java, "hookRight")}

    enum class State(val speed: Double) {
        FORWARD(1.0),
        REVERSE(-0.5),
        STOP(0.0)
    }

    var state = State.STOP

    override fun initHardware() {
        leftMotor.direction = DcMotorSimple.Direction.REVERSE
        rightMotor

        leftDeployServo.position = 0.4
        rightDeployServo.position = 0.0
    }

    override fun periodic() {
        if (shouldDeploy && opMode.opModeIsActive()) {
            shouldDeploy = false
            leftDeployServo.position = 0.0
            rightDeployServo.position = 1.0

            Log.d("Intake", "deployed?")
        }

        leftMotor.power = state.speed
        rightMotor.power = state.speed
    }
}