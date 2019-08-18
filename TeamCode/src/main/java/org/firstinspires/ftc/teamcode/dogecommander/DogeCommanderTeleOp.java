package org.firstinspires.ftc.teamcode.dogecommander;

import com.disnodeteam.dogecommander.DogeCommander;
import com.disnodeteam.dogecommander.DogeOpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dogecommander.bot.Bot;
import org.firstinspires.ftc.teamcode.dogecommander.bot.commands.teleop.TeleOpDriveControl;
import org.firstinspires.ftc.teamcode.dogecommander.bot.commands.teleop.TeleOpIntakeControl;

@TeleOp
public class DogeCommanderTeleOp extends LinearOpMode implements DogeOpMode {
    @Override
    public void runOpMode() {
        DogeCommander commander = new DogeCommander(this);
        Bot bot = new Bot(hardwareMap);

        commander.setBot(bot);
        commander.init();

        waitForStart();

        commander.runCommandsParallel(
                new TeleOpDriveControl(bot.drive, gamepad1),
                new TeleOpIntakeControl(bot.intake, gamepad1)
        );

        commander.stop();
    }
}
