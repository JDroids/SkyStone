package org.firstinspires.ftc.teamcode.bot.commands

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.control.PIDCoefficients
import com.disnodeteam.dogecommander.Command
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.teamcode.bot.subsystems.Drive
import com.acmerobotics.roadrunner.control.PIDFController
import com.qualcomm.robotcore.util.ElapsedTime
import kotlin.math.abs

class Turn (private val drive: Drive, angle: Double,
            angleUnit: AngleUnit=AngleUnit.DEGREES) : Command {
    private val controller = PIDFController(TurnConstants.TURN_PID_COEFFICIENTS)
    private val angleInRadians = angleUnit.toRadians(angle)
    private val target = angleInRadians // by lazy {AngleUnit.normalizeRadians(drive.poseEstimate.heading + angleInRadians)}

    override fun start() {
        controller.targetPosition = target
        controller.setOutputBounds(-1.0, 1.0)
        controller.reset()
    }

    override fun periodic() {
        val position = AngleUnit.normalizeRadians(drive.poseEstimate.heading)
        val controllerResult = controller.update(position)

        drive.motorPowers = Drive.DriveMotorPowers(
                0.0, 0.0, controllerResult
        )

        // FtcDashboard.getInstance().telemetry.addData("Controller Result", controllerResult)
        // FtcDashboard.getInstance().telemetry.addData("Heading", drive.poseEstimate.heading)
        // FtcDashboard.getInstance().telemetry.update()
    }

    override fun stop() {
        drive.motorPowers = Drive.DriveMotorPowers.STOP
    }

    override fun isCompleted() =
            abs(AngleUnit.normalizeRadians(drive.poseEstimate.heading-target)) <=
                    AngleUnit.DEGREES.toRadians(3.0)

    @Config
    object TurnConstants {
        @JvmField var TURN_PID_COEFFICIENTS = PIDCoefficients(1.0, 0.0, 0.0)
    }

}