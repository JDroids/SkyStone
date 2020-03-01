package org.firstinspires.ftc.teamcode

import com.qualcomm.hardware.bosch.BNO055IMU
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.openftc.easyopencv.OpenCvCamera
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvInternalCamera

fun BNO055IMU.init() {
    val parameters = BNO055IMU.Parameters()

    parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS
    parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC
    parameters.calibrationDataFile = "BNO055IMUCalibration.json" // see the calibration sample opmode
    parameters.loggingEnabled = true
    parameters.loggingTag = "IMU"

    this.initialize(parameters)
}

val BNO055IMU.robotHeadingRadians
    get() = this.angularOrientation.firstAngle.toDouble()

enum class Alliance(val multiplier: Int) {
    BLUE(1),
    RED(-1)
}

fun getCamera(opMode: LinearOpMode): OpenCvCamera {
    val cameraMonitorViewId = opMode.hardwareMap.appContext.resources.getIdentifier(
            "cameraMonitorViewId", "id", opMode.hardwareMap.appContext.packageName)

    return OpenCvCameraFactory.getInstance()
            .createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId)
}