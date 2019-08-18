package org.firstinspires.ftc.teamcode.dogecv.roverruckus;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(group="DogeCV")
public class GoldAlignExample extends OpMode {
    // Detector object
    private GoldAlignDetector detector;

    @Override
    public void init() {
        // Set up detector:
        // Create detector
        detector = new GoldAlignDetector();
        // Initialize it with the app context and camera
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        // Set detector to use default settings
        detector.useDefaults();

        // Optional tuning:
        // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignSize = 100;
        // How far from center frame to offset this alignment zone.
        detector.alignPosOffset = 0;
        // How much to downscale the input frames
        detector.downscale = 0.4;

        // Can also be PERFECT_AREA
        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA;
        // if using PERFECT_AREA scoring
        //detector.perfectAreaScorer.perfectArea = 10000;

        // Setting the weight of the area scorer and ratio scorer
        detector.maxAreaScorer.weight = 0.005;
        detector.ratioScorer.weight = 5;

        // Ratio adjustment
        detector.ratioScorer.perfectRatio = 1.0;

        detector.enable(); // Start the detector!
    }

    @Override
    public void loop() {
        telemetry.addLine()
                .addData("Is Aligned?", detector.getAligned())
                .addData("X Pos", detector.getXPosition());
        telemetry.update();
    }

    @Override
    public void stop() {
        // Disable the detector
        if (detector != null) detector.disable();
    }
}
