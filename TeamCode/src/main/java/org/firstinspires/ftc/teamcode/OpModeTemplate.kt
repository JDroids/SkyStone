package org.firstinspires.ftc.teamcode

import com.disnodeteam.dogecommander.DogeCommander
import com.disnodeteam.dogecommander.DogeOpMode
import com.disnodeteam.dogecommander.Subsystem
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.bot.subsystems.MecanumDrive

abstract class OpModeTemplate : LinearOpMode(), DogeOpMode {
    val commander by lazy { DogeCommander(this) }
    val drive by lazy {MecanumDrive(hardwareMap)}

    @Override
    final override fun runOpMode() {
        +drive

        commander.init()

        initOpMode()

        waitForStart()

        commander.start()

        run()

        commander.stop()
    }

    open fun initOpMode() {}

    abstract fun run()

    private operator fun Subsystem.unaryPlus() {
        commander.registerSubsystem(this)
    }
}