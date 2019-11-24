package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.bot.commands.MoveDistance
import org.openftc.revextensions2.ExpansionHubMotor

@Autonomous(name="Park")
class Park : LinearOpMode() {
    private val frontLeftMotor
            by lazy { hardwareMap.get(ExpansionHubMotor::class.java, "frontLeft") }
    private val frontRightMotor
            by lazy { hardwareMap.get(ExpansionHubMotor::class.java, "frontRight") }
    private val backLeftMotor
            by lazy { hardwareMap.get(ExpansionHubMotor::class.java, "backLeft") }
    private val backRightMotor
            by lazy { hardwareMap.get(ExpansionHubMotor::class.java, "backRight") }

    override fun runOpMode() {
        frontLeftMotor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        frontRightMotor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        backLeftMotor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        backRightMotor.mode = DcMotor.RunMode.RUN_USING_ENCODER

        // sets the left side to reverse so power of 1 to all motors goes forwards, not backwards
        frontLeftMotor.direction = DcMotorSimple.Direction.REVERSE
        backLeftMotor.direction = DcMotorSimple.Direction.REVERSE

        waitForStart()

        frontLeftMotor.power = -0.5
        frontRightMotor.power = -0.5
        backLeftMotor.power = -0.5
        backRightMotor.power = -0.5

        sleep(750)

        frontLeftMotor.power = 0.0
        frontRightMotor.power = 0.0
        backLeftMotor.power = 0.0
        backRightMotor.power = 0.0

    }
}

@Autonomous
class Park2 : OpModeTemplate() {
    override fun initOpMode() {

    }

    override fun run() {
        +MoveDistance(drive, -50.0)
    }
}