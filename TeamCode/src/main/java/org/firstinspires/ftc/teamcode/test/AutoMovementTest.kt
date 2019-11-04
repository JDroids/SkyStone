package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.OpModeTemplate
import org.firstinspires.ftc.teamcode.bot.commands.FollowTrajectory
import org.firstinspires.ftc.teamcode.bot.commands.Turn

@Autonomous
class AutoMovementTest : OpModeTemplate() {
    override fun initOpMode() {}

    override fun run() {
        commander.runCommand(FollowTrajectory(drive,
                drive.trajectoryBuilder.forward(36.0).build()))

        commander.runCommand(Turn(drive, 90.0))
    }
}