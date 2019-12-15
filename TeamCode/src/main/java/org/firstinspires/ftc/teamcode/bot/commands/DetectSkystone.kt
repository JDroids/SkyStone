package org.firstinspires.ftc.teamcode.bot.commands

import android.util.Log
import com.disnodeteam.dogecommander.Command
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.EpsilonSkyStonePipeline
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation
import org.openftc.easyopencv.OpenCvInternalCamera


class DetectSkystone(private val opMode: LinearOpMode) : Command {
    private val cameraMonitorViewId = opMode.hardwareMap.appContext.resources.getIdentifier(
            "cameraMonitorViewId", "id", opMode.hardwareMap.appContext.packageName)

    private val phoneCam = OpenCvCameraFactory.getInstance()
            .createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId)

    private val pipeline = EpsilonSkyStonePipeline()

    override fun start() {
        phoneCam.openCameraDevice()

        phoneCam.setPipeline(pipeline)

        phoneCam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT)
    }

    override fun periodic() {
        opMode.telemetry.addData("Skystone Left",
                pipeline.vumarkLeftBoundary)

        opMode.telemetry.update()
    }

    override fun stop() {
        phoneCam.stopStreaming()
    }

    override fun isCompleted() = false // opMode.isStarted
}