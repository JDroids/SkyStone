package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import org.firstinspires.ftc.teamcode.bot.commands.DetectSkystone

@Disabled
@Autonomous
class DoubleSkyStoneAuto : OpModeTemplate() {
    override fun initOpMode() {}

    override fun run() {
        +DetectSkystone(this)
    }
}
