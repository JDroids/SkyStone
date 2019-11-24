package org.firstinspires.ftc.teamcode.bot.subsystems

import com.disnodeteam.dogecommander.Subsystem
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.ServoImplEx

class FoundationMechanism(private val hardwareMap: HardwareMap) : Subsystem {
    enum class State {
        RETRACTED,
        DEPLOYED
    }

    var state = State.RETRACTED

    private val leftFoundationServo by lazy {hardwareMap.get(ServoImplEx::class.java, "fServo2")}
    private val rightFoundationServo by lazy {hardwareMap.get(ServoImplEx::class.java, "fServo1")}

    override fun initHardware() {
        leftFoundationServo
        rightFoundationServo
    }

    override fun periodic() {
        when (state) {
            State.RETRACTED -> {
                leftFoundationServo.position = 1.0
                rightFoundationServo.position = 0.0
            }
            State.DEPLOYED -> {
                leftFoundationServo.position = 0.0
                rightFoundationServo.position = 1.0
            }
        }
    }
}