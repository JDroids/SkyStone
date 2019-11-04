package org.firstinspires.ftc.teamcode

import com.disnodeteam.dogecommander.DogeCommander
import com.disnodeteam.dogecommander.DogeOpMode
import com.disnodeteam.dogecommander.Subsystem
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.bot.subsystems.Drive
import org.firstinspires.ftc.teamcode.bot.subsystems.FoundationMechanism

abstract class OpModeTemplate : LinearOpMode(), DogeOpMode {
    val commander by lazy { DogeCommander(this) }
    val drive by lazy {Drive(hardwareMap)}
    val foundationMechanism by lazy {FoundationMechanism(hardwareMap)}

    final override fun runOpMode() {
        +drive
        +foundationMechanism

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
}