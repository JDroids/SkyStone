package org.firstinspires.ftc.teamcode.bot.subsystems

import android.util.Log
import com.disnodeteam.dogecommander.Subsystem
import com.qualcomm.hardware.lynx.LynxModule
import com.qualcomm.robotcore.hardware.HardwareMap
import java.lang.Exception

class BulkReadManager(private val hardwareMap: HardwareMap) : Subsystem {
    private val hub1 by lazy {hardwareMap.get(LynxModule::class.java, "Expansion Hub 2")}
    private val hub2 by lazy {hardwareMap.get(LynxModule::class.java, "Expansion Hub 3")}

    override fun initHardware() {
        hub1.bulkCachingMode = LynxModule.BulkCachingMode.MANUAL
        hub2.bulkCachingMode = LynxModule.BulkCachingMode.MANUAL
    }

    override fun periodic() {
        listOf(hub1, hub2).forEach {
            try {
                val field = it::class.java.getDeclaredField("lastBulkData")

                field.isAccessible = true
                field.set(it, it.bulkData)

                it.clearBulkCache()
            }
            catch (e: NoSuchFieldException) {
                e.printStackTrace()
            }
            catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
    }
}