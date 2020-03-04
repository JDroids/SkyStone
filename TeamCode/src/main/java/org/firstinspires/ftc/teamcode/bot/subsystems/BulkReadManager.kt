package org.firstinspires.ftc.teamcode.bot.subsystems

import com.disnodeteam.dogecommander.Subsystem
import com.qualcomm.hardware.lynx.LynxModule
import com.qualcomm.robotcore.hardware.HardwareMap

class BulkReadManager(private val hardwareMap: HardwareMap) : Subsystem {
    private val hub1 by lazy {hardwareMap.get(LynxModule::class.java, "Expansion Hub 2")}
    private val hub2 by lazy {hardwareMap.get(LynxModule::class.java, "Expansion Hub 3")}

    override fun initHardware() {
        hub1.bulkCachingMode = LynxModule.BulkCachingMode.MANUAL
        hub2.bulkCachingMode = LynxModule.BulkCachingMode.MANUAL
    }

    override fun periodic() {
        hub1.clearBulkCache()
        hub2.clearBulkCache()
    }
}