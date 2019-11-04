package org.firstinspires.ftc.teamcode.bot.commands

import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.control.PIDCoefficients
import com.acmerobotics.roadrunner.profile.MotionProfile
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator
import com.disnodeteam.dogecommander.Command
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.teamcode.bot.subsystems.Drive
import com.acmerobotics.roadrunner.profile.MotionState
import com.qualcomm.robotcore.util.ElapsedTime
import com.acmerobotics.roadrunner.control.PIDFController
import com.acmerobotics.roadrunner.drive.DriveSignal
import com.acmerobotics.roadrunner.geometry.Pose2d


class Turn(private val drive: Drive, angle: Double,
           angleUnit: AngleUnit=AngleUnit.DEGREES) : Command {
    private lateinit var turnProfile: MotionProfile
    private val timer = ElapsedTime()
    private val radiansToTurn = angleUnit.toRadians(angle)

    private val controller = PIDFController(PIDCoefficients(1.0, 0.0, 0.0))


    override fun start() {
        val heading = drive.poseEstimate.heading

        turnProfile = MotionProfileGenerator.generateSimpleMotionProfile(
                MotionState(heading, 0.0, 0.0, 0.0),
                MotionState(heading + radiansToTurn, 0.0, 0.0, 0.0),
                Drive.constraints.maxAngVel,
                Drive.constraints.maxAngAccel,
                Drive.constraints.maxAngJerk
        )

        timer.reset()
    }

    override fun periodic() {
        val targetState = turnProfile[timer.seconds()]
        val correction = controller.update(drive.poseEstimate.heading, targetState.v)

        drive.setDriveSignal(DriveSignal(
                Pose2d(0.0, 0.0, targetState.v + correction),
                Pose2d(0.0, 0.0, targetState.a)
        ))
    }

    override fun stop() {
        drive.motorPowers = Drive.DriveMotorPowers.STOP
    }

    override fun isCompleted() = timer.seconds() >= turnProfile.duration()

    @Config
    companion object {
        @JvmField var TURN_PID_COEFFICIENTS = PIDCoefficients(0.1, 0.0, 0.0)
    }

}