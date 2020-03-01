package org.firstinspires.ftc.teamcode.bot.commands.teleop

import android.util.Log
import com.disnodeteam.dogecommander.Command
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.bot.subsystems.Deposit
import java.util.function.BooleanSupplier

class ControlDeposit(private val deposit: Deposit, private val gamepad: Gamepad) : Command {
    private val dpadLeft = OnPress {gamepad.dpad_left}
    private val dpadRight = OnPress {gamepad.dpad_right}
    private val dpadUp = OnPress {gamepad.dpad_up}
    private val dpadDown = OnPress {gamepad.dpad_down}

    override fun start() {

    }

    override fun periodic() {
        deposit.targetHeight += when {
            dpadLeft.getValue() -> 1.0
            dpadRight.getValue() -> -1.0
            dpadUp.getValue() -> 4.0
            dpadDown.getValue() -> -4.0
            else -> 0.0
        }

        deposit.graspState = when {
            gamepad.a -> Deposit.GraspState.OPEN
            gamepad.b -> Deposit.GraspState.CLOSED
            else -> deposit.graspState
        }

        Log.d("GRASPState", deposit.graspState.toString())

        deposit.fourBarState = when {
            gamepad.x -> Deposit.VirtualFourBarState.OUT
            gamepad.y -> Deposit.VirtualFourBarState.IN
            else -> deposit.fourBarState
        }
    }

    override fun stop() {}

    override fun isCompleted() = false
}

class OnPress(private val booleanSupplier: () -> Boolean) {
    var previousValue = false

    fun getValue(): Boolean {
        val currentValue = booleanSupplier()

        val result = currentValue && !previousValue

        previousValue = currentValue

        return result
    }
}