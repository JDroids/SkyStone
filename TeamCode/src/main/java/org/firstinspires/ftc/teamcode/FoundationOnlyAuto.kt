package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.bot.commands.MoveDistance
import org.firstinspires.ftc.teamcode.bot.commands.MoveFoundationMechanism
import org.firstinspires.ftc.teamcode.bot.commands.Turn
import org.firstinspires.ftc.teamcode.bot.subsystems.FoundationMechanism

abstract class FoundationOnlyAuto(private val alliance: Alliance) : OpModeTemplate() {
    override fun initOpMode() {}

    override fun run() {
        // Get to the foundation
        +Turn(drive, 30.0 * alliance.multiplier)
        +MoveDistance(drive, -20.0)
        +Turn(drive, 10.0* alliance.multiplier)
        +MoveDistance(drive, -18.0)

        // Grab the foundation
        +MoveFoundationMechanism(foundationMechanism, FoundationMechanism.State.DEPLOYED)

        // Drive to the wall
        +MoveDistance(drive, 10.0)
        //+Turn(drive, 5.0 * alliance.multiplier)
        +MoveDistance(drive, 30.0)

        // Make sure foundation is in building site
        +Turn(drive, 90.0 * alliance.multiplier)

        +MoveFoundationMechanism(foundationMechanism, FoundationMechanism.State.RETRACTED)

        +MoveDistance(drive, 2.0)
    }
}

@Autonomous(name="BlueFoundationOnly", group="blue")
class BlueFoundationOnlyAuto() : FoundationOnlyAuto(Alliance.BLUE)

@Autonomous(name="RedFoundationOnly", group="red")
class RedFoundationOnlyAuto() : FoundationOnlyAuto(Alliance.RED)