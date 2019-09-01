package org.firstinspires.ftc.teamcode

import com.disnodeteam.dogecommander.DogeCommander
import com.disnodeteam.dogecommander.DogeOpMode
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.bot.Bot

abstract class OpModeTemplate : LinearOpMode(), DogeOpMode {
    val commander by lazy {DogeCommander(this)}
    val bot by lazy {Bot(hardwareMap)}

    fun initDogeCommander() {
        commander.setBot(bot)
        commander.init()
    }
}