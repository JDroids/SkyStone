package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import org.firstinspires.ftc.teamcode.OpModeTemplate
import org.firstinspires.ftc.teamcode.bot.commands.MoveDistance
import org.firstinspires.ftc.teamcode.bot.commands.Turn

@Disabled
@Autonomous
class AutoMovementTest : OpModeTemplate() {
    override fun initOpMode() {}

    override fun run() {
        commander.runCommand(MoveDistance(drive, 12.0))

        //commander.runCommand(Turn(drive, 90.0))
    }
}