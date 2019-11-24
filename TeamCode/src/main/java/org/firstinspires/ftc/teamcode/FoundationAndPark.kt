package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.bot.commands.MoveDistance
import org.firstinspires.ftc.teamcode.bot.commands.Turn

abstract class FoundationAndParkClose(private val alliance: Alliance) : FoundationOnlyAuto(alliance) {
    override fun run() {
        super.run()

        +Turn(drive, 85.0 * alliance.multiplier)

        +MoveDistance(drive, 18.0)

        +Turn(drive, 280.0 * alliance.multiplier)

        +MoveDistance(drive, -18.0)
    }
}


@Autonomous(name="BlueFoundationAndParkClose", group="blue")
class BlueFoundationAndParkClose() : FoundationAndParkClose(Alliance.BLUE)

@Autonomous(name="RedFoundationAndParkClose", group="red")
class RedFoundationAndParkClose() : FoundationAndParkClose(Alliance.RED)

abstract class FoundationAndParkFar(private val alliance: Alliance) : FoundationOnlyAuto(alliance) {
    override fun run() {
        super.run()

        +Turn(drive, 85.0 * alliance.multiplier)

        +MoveDistance(drive, 18.0)

        +Turn(drive, 0.0 * alliance.multiplier)

        +MoveDistance(drive, -12.0)
    }
}

@Autonomous(name="BlueFoundationAndParkFar")
class BlueFoundationAndParkFar() : FoundationAndParkFar(Alliance.BLUE)

@Autonomous(name="RedFoundationAndParkFar")
class RedFoundationAndParkFar() : FoundationAndParkFar(Alliance.RED)