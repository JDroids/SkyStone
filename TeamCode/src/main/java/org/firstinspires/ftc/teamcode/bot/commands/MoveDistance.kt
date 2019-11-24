package org.firstinspires.ftc.teamcode.bot.commands

import com.acmerobotics.roadrunner.profile.MotionConstraints
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator
import com.acmerobotics.roadrunner.profile.MotionState
import com.acmerobotics.roadrunner.trajectory.Trajectory
import com.disnodeteam.dogecommander.Command
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.bot.subsystems.Drive

class MoveDistance(private val drive: Drive, private val distance: Double) : Command {

    private val profile = MotionProfileGenerator.generateSimpleMotionProfile(
            MotionState(0.0, 0.0, 0.0),
            MotionState(distance*(36/21.5), 0.0, 0.0),
            Drive.constraints.maxVel,
            Drive.constraints.maxAccel
    )
    private val timer = ElapsedTime()

    override fun start() {
        timer.reset()
    }

    override fun periodic() {
        drive.motorPowers = Drive.DriveMotorPowers(profile[timer.seconds()].v/Drive.constraints.maxVel, 0.0, 0.0)
    }

    override fun stop() {
        drive.motorPowers = Drive.DriveMotorPowers.STOP
    }

    override fun isCompleted() = timer.seconds() >= profile.duration()
}