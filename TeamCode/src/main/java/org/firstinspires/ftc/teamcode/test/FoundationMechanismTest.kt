package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import org.firstinspires.ftc.teamcode.OpModeTemplate
import org.firstinspires.ftc.teamcode.bot.commands.MoveFoundationMechanism
import org.firstinspires.ftc.teamcode.bot.subsystems.FoundationMechanism

@Disabled
@Autonomous
class FoundationMechanismTest : OpModeTemplate() {
    override fun initOpMode() {}

    override fun run() {
        commander.runCommand(MoveFoundationMechanism(foundationMechanism,
                FoundationMechanism.State.DEPLOYED))

        sleep(3000)

        commander.runCommand(MoveFoundationMechanism(foundationMechanism,
                FoundationMechanism.State.RETRACTED))
    }
}