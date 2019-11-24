package org.firstinspires.ftc.teamcode

import com.disnodeteam.dogecommander.Command
import com.disnodeteam.dogecommander.DogeCommander
import com.disnodeteam.dogecommander.DogeOpMode
import com.disnodeteam.dogecommander.Subsystem
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.bot.subsystems.Deposit
import org.firstinspires.ftc.teamcode.bot.subsystems.Drive
import org.firstinspires.ftc.teamcode.bot.subsystems.FoundationMechanism
import org.firstinspires.ftc.teamcode.bot.subsystems.Intake

abstract class OpModeTemplate(shouldIntakeDeploy: Boolean=false) : LinearOpMode(), DogeOpMode {
    val commander by lazy { DogeCommander(this) }

    val drive by lazy {Drive(hardwareMap)}
    val foundationMechanism by lazy {FoundationMechanism(hardwareMap)}
    val intake by lazy { Intake(this, hardwareMap, shouldIntakeDeploy) }
    // val deposit by lazy { Deposit(hardwareMap) }

    final override fun runOpMode() {
        +drive
        +foundationMechanism
        +intake
        // +deposit

        commander.init()

        initOpMode()

        waitForStart()

        commander.start()

        run()

        commander.stop()
    }

    abstract fun initOpMode()

    abstract fun run()

    private operator fun Subsystem.unaryPlus() {
        commander.registerSubsystem(this)
    }

    protected operator fun Command.unaryPlus() {
        commander.runCommand(this)
    }
}