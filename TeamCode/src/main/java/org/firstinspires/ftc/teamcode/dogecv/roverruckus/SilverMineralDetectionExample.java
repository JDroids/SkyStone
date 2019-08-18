package org.firstinspires.ftc.teamcode.dogecv.roverruckus;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.SilverDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.opencv.core.Rect;
import org.opencv.core.Size;

@Autonomous(group="DogeCV")
public class SilverMineralDetectionExample extends OpMode {
    /* Detector object */
    private SilverDetector detector;

    @Override
    public void init() {
        telemetry.addData("Status", "DogeCV 2019.1 - Silver Detector Example");

        // Setup detector
        detector = new SilverDetector(); // Create detector
        detector.setAdjustedSize(new Size(480, 270)); // Set detector size
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize detector with app context and camera
        detector.useDefaults(); // Set default detector settings
        // Optional tuning

        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005;

        detector.ratioScorer.weight = 5;
        detector.ratioScorer.perfectRatio = 1.0;
        detector.enable(); // Start detector
    }

    /*
     * Code to run REPEATEDLY when the driver hits PLAY
     */
    @Override
    public void loop() {
        // Gets the rectangle from what the detector found
        Rect rect = detector.getFoundRect();

        // Adds to telemetry about what the detector detected
        // (whether or not the detector found anything, and if so it's location)
        telemetry.addLine()
                .addData("Is Found", detector.isFound())
                .addData("Location",
                        detector.isFound() ?
                                Integer.toString((int) (rect.x + rect.width*0.5)) + ", "
                                        + Integer.toString((int) (rect.y+0.5*rect.height))
                                : "N/A");

        // Update telemetry
        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        // Disable the detector
        if (detector != null) detector.disable();
    }

}
