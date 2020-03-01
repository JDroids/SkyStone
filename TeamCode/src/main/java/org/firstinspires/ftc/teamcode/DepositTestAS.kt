package org.firstinspires.ftc.teamcode

import com.disnodeteam.dogecommander.Command
import com.disnodeteam.dogecommander.DogeCommander
import com.disnodeteam.dogecommander.DogeOpMode
import com.disnodeteam.dogecommander.Subsystem
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.bot.commands.teleop.ControlDeposit
import org.firstinspires.ftc.teamcode.bot.subsystems.Deposit
import org.firstinspires.ftc.teamcode.bot.subsystems.Drive
import org.firstinspires.ftc.teamcode.bot.subsystems.FoundationMechanism
import org.firstinspires.ftc.teamcode.bot.subsystems.Intake

@TeleOp
class DepositTestAS : LinearOpMode(), DogeOpMode {
    val commander by lazy { DogeCommander(this) }

    val deposit by lazy { Deposit(hardwareMap) }

    override fun runOpMode() {
        +deposit

        commander.init()

        commander.start()

        initOpMode()

        waitForStart()

        run()

        commander.stop()
    }

    fun initOpMode() {

    }

    fun run() {
        +ControlDeposit(deposit, gamepad1)
    }

    private operator fun Subsystem.unaryPlus() {
        commander.registerSubsystem(this)
    }

    protected operator fun Command.unaryPlus() {
        commander.runCommand(this)
    }
}