package org.firstinspires.ftc.teamcode.bot.subsystems

import com.disnodeteam.dogecommander.Subsystem
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.ServoImplEx

class Deposit(private val hardwareMap: HardwareMap) : Subsystem {
    private val pivotServo by lazy {hardwareMap.get(ServoImplEx::class.java, "depositPivot")}
    private val graspServo by lazy {hardwareMap.get(ServoImplEx::class.java, "depositGrasp")}

    enum class PivotPosition {
        RETRACTED,
        LEVEL_1,
        LEVEL_2
    }

    enum class GraspState {
        GRASPED,
        OPEN;

        operator fun not() = when (this) {
            GRASPED -> OPEN
            OPEN -> GRASPED
        }
    }

    var depositPos = PivotPosition.RETRACTED
    var graspState = GraspState.OPEN

    override fun initHardware() {
        pivotServo
        graspServo
    }

    override fun periodic() {
        pivotServo.position = when(depositPos) {
            PivotPosition.RETRACTED -> 0.9
            PivotPosition.LEVEL_1 -> 0.1
            PivotPosition.LEVEL_2 -> 0.3
        }

        graspServo.position = when (graspState) {
            GraspState.GRASPED -> 1.0
            GraspState.OPEN -> if (depositPos == PivotPosition.RETRACTED) 0.9 else 0.7
        }
    }
}