package org.firstinspires.ftc.teamcode.bot

import com.disnodeteam.dogecommander.DogeBot
import com.disnodeteam.dogecommander.Subsystem
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.bot.subsystems.MecanumDrive

class Bot(hardwareMap: HardwareMap) : DogeBot() {
    val drive: MecanumDrive by lazy {MecanumDrive(hardwareMap)}

    override fun init() {
        addSubsystem(drive)
    }

    private operator fun Subsystem.unaryPlus() {
        addSubsystem(this)
    }
}