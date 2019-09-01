package org.firstinspires.ftc.teamcode

import com.disnodeteam.dogecommander.DogeBot
import com.disnodeteam.dogecommander.DogeCommander
import com.disnodeteam.dogecommander.DogeOpMode
import com.qualcomm.hardware.bosch.BNO055IMU
import org.firstinspires.ftc.teamcode.bot.Bot

fun BNO055IMU.init() {
    val parameters = BNO055IMU.Parameters()

    parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS
    parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC
    parameters.calibrationDataFile = "BNO055IMUCalibration.json" // see the calibration sample opmode
    parameters.loggingEnabled = true
    parameters.loggingTag = "IMU"

    this.initialize(parameters)
}