package org.firstinspires.ftc.teamcode.bot.commands

import android.util.Log
import com.arcrobotics.ftclib.vision.SkystoneDetector
import com.disnodeteam.dogecommander.Command
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.EpsilonSkyStonePipeline
import org.openftc.easyopencv.OpenCvCamera
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation
import org.openftc.easyopencv.OpenCvInternalCamera


class DetectSkystone(private val opMode: LinearOpMode, private val camera: OpenCvCamera) : Command {
    private val cameraMonitorViewId = opMode.hardwareMap.appContext.resources.getIdentifier(
            "cameraMonitorViewId", "id", opMode.hardwareMap.appContext.packageName)

    val pipeline = SkystoneDetector()

    init {
        camera.openCameraDevice()

        camera.setPipeline(pipeline)

        camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT)
    }

    override fun start() {

    }

    override fun periodic() {
        opMode.telemetry.addData("Skystone Position",
                pipeline.skystonePosition)

        opMode.telemetry.update()
    }

    override fun stop() {
        camera.stopStreaming()
    }

    override fun isCompleted() = false // opMode.isStarted
}