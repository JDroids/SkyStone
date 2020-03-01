package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.bot.commands.DetectSkystone

@Autonomous
class DoubleSkyStoneAuto : OpModeTemplate() {
    val detectSkystone by lazy {DetectSkystone(this, getCamera(this))}
    val stonePosition by lazy {detectSkystone.pipeline.skystonePosition}


    override fun initOpMode() {
        +detectSkystone //necessary to initialize the detectSkystone variable

        stonePosition
    }

    override fun run() {

    }
}
