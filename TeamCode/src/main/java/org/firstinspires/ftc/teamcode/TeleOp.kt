package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.bot.commands.teleop.ArcadeDrive
import org.firstinspires.ftc.teamcode.bot.commands.teleop.ControlDeposit
import org.firstinspires.ftc.teamcode.bot.commands.teleop.ControlIntake
import org.firstinspires.ftc.teamcode.bot.commands.teleop.ControlFoundation

@TeleOp
class TeleOp : OpModeTemplate(shouldIntakeDeploy = true) {
    override fun initOpMode() {}

    override fun run() {
        commander.runCommandsParallel(
                ArcadeDrive(drive, gamepad1),
                ControlIntake(intake, gamepad2),
                ControlFoundation(foundationMechanism, gamepad2)
                // ControlDeposit(deposit, gamepad2)
        )
    }
}