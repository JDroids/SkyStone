package org.firstinspires.ftc.teamcode

import com.disnodeteam.dogecommander.Subsystem
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class DogeCommanderTest : OpModeTemplate() {
    class CountingSubsystem : Subsystem {
        var periodicCalls = 0
            private set

        override fun initHardware() {

        }

        override fun periodic() {
            periodicCalls++
        }
    }


    override fun run() {
        val countingSubsystem = CountingSubsystem()

        commander.registerSubsystem(countingSubsystem)

        while (opModeIsActive()) {
            telemetry.addData("Periodic Calls", countingSubsystem.periodicCalls)
        }
    }
}