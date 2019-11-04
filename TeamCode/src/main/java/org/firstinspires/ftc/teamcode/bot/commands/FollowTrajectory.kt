package org.firstinspires.ftc.teamcode.bot.commands

import com.acmerobotics.roadrunner.trajectory.Trajectory
import com.disnodeteam.dogecommander.Command
import org.firstinspires.ftc.teamcode.bot.subsystems.Drive

class FollowTrajectory(private val drive: Drive, private val trajectory: Trajectory) : Command {
    override fun start() {
        Drive.follower.followTrajectory(trajectory)
    }

    override fun periodic() {
        Drive.follower.update(drive.poseEstimate)
    }

    override fun stop() {
        drive.motorPowers = Drive.DriveMotorPowers.STOP
    }

    override fun isCompleted() = !Drive.follower.isFollowing()
}