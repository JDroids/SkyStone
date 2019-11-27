package org.firstinspires.ftc.teamcode.bot.commands.teleop

import com.disnodeteam.dogecommander.Command
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.bot.subsystems.Deposit

class ControlDeposit(private val deposit: Deposit, private val gamepad: Gamepad) : Command {
    private var previousButtonPosition = false

    override fun start() {}

    override fun periodic() {
        deposit.depositPos = when {
            gamepad.a -> Deposit.PivotPosition.RETRACTED
            gamepad.b -> Deposit.PivotPosition.LEVEL_1
            gamepad.y -> Deposit.PivotPosition.LEVEL_2
            else -> deposit.depositPos
        }

        val currentButtonPosition = gamepad.x

        if (currentButtonPosition && !previousButtonPosition) {
            deposit.graspState = !deposit.graspState
        }
        
        previousButtonPosition = currentButtonPosition
    }

    override fun stop() {}

    override fun isCompleted() = false
}