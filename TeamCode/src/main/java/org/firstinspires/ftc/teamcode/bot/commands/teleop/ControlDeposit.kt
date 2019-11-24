package org.firstinspires.ftc.teamcode.bot.commands.teleop

import com.disnodeteam.dogecommander.Command
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.bot.subsystems.Deposit

class ControlDeposit(private val deposit: Deposit, private val gamepad: Gamepad) : Command {
    private val timer = ElapsedTime()
    private val timeToWait = 50

    override fun start() {}

    override fun periodic() {
        deposit.depositPos = when {
            gamepad.a -> Deposit.PivotPosition.RETRACTED
            gamepad.b -> Deposit.PivotPosition.LEVEL_1
            gamepad.y -> Deposit.PivotPosition.LEVEL_2
            else -> deposit.depositPos
        }

        if (gamepad.x && timer.milliseconds() >= timeToWait) {
            deposit.graspState = !deposit.graspState
            timer.reset()
        }
    }

    override fun stop() {}

    override fun isCompleted() = false
}