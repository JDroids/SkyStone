package org.firstinspires.ftc.teamcode

import com.disnodeteam.dogecommander.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

internal class DogeCommanderTest {
    class TestCommand : Command {
        var startTimesRun = 0
            private set
        var periodicTimesRun = 0
            private set
        var stopTimesRun = 0
            private set

        override fun start() {
            startTimesRun++
        }
        override fun periodic() {
            periodicTimesRun++
        }
        override fun stop() {
            stopTimesRun++
        }

        override fun isCompleted() = periodicTimesRun >= TIMES_TO_RUN

        companion object {
            val TIMES_TO_RUN = 5
        }
    }

    @Test
    fun `does DogeCommander run commands`() {
        val command = TestCommand()

        val commander = DogeCommander(DogeOpMode { true })

        commander.init()

        commander.runCommand(command)

        assert(command.startTimesRun == 1)
        assert(command.periodicTimesRun == TestCommand.TIMES_TO_RUN)
        assert(command.stopTimesRun == 1)
    }

    @Test
    fun `does DogeBot update Subsystems`() {

        val subsystem = mock(Subsystem::class.java)

        val commander = DogeCommander(DogeOpMode { true })
        commander.registerSubsystem(subsystem)

        commander.init()

        verify(subsystem, times(1)).initHardware()

        commander.start()
        Thread.sleep(1000)
        verify(subsystem, atLeast(2)).periodic()
    }
}