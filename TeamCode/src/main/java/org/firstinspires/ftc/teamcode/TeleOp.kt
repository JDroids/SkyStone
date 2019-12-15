package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.bot.commands.teleop.ArcadeDrive
import org.firstinspires.ftc.teamcode.bot.commands.teleop.ControlDeposit
import org.firstinspires.ftc.teamcode.bot.commands.teleop.ControlIntake
import org.firstinspires.ftc.teamcode.bot.commands.teleop.ControlFoundation

@TeleOp
class TeleOp : OpModeTemplate(shouldIntakeDeploy = true) {
    private val intakePivotLeft by lazy {hardwareMap.get(Servo::class.java, "intakeDownL")}
    private val intakePivotRight by lazy {hardwareMap.get(Servo::class.java, "intakeDownR")}

    override fun initOpMode() {
        intakePivotLeft
        intakePivotRight
    }

    override fun run() {
        intakePivotLeft.position = 1.0
        intakePivotRight.position = 1.0

        commander.runCommandsParallel(
                ArcadeDrive(drive, gamepad1),
                ControlIntake(intake, gamepad2),
                ControlFoundation(foundationMechanism, gamepad2)
                // ControlDeposit(deposit, gamepad2)
        )
    }
}