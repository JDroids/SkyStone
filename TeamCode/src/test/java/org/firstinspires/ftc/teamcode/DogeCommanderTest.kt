package org.firstinspires.ftc.teamcode

import com.disnodeteam.dogecommander.*
import org.firstinspires.ftc.teamcode.bot.Bot
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

        override fun isCompleted() = periodicTimesRun >= 5
    }

    @Test
    fun `Does dogeCommander run commands`() {
        val command = TestCommand()

        val commander = DogeCommander(DogeOpMode { true })
        val bot = mock(Bot::class.java)

        commander.setBot(bot)
        commander.init()

        commander.runCommand(command)

        assert(command.startTimesRun == 1)
        assert(command.periodicTimesRun == 5)
        assert(command.stopTimesRun == 1)
    }

    @Test
    fun `does DogeBot update Subsystems`() {
        val bot = object : DogeBot() {
            val subsystem = mock(Subsystem::class.java)

            init {
                addSubsystem(subsystem)
            }
        }

        val commander = DogeCommander(DogeOpMode { true })

        commander.setBot(bot)
        commander.init()

        verify(bot.subsystem, times(1)).initHardware()

        commander.start()
        Thread.sleep(1000)
        verify(bot.subsystem, atLeast(2)).periodic()
    }
}