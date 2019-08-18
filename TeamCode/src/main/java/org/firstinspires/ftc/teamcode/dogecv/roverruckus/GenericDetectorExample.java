package org.firstinspires.ftc.teamcode.dogecv.roverruckus;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.GenericDetector;
import com.disnodeteam.dogecv.filters.DogeCVColorFilter;
import com.disnodeteam.dogecv.filters.HSVColorFilter;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;

/**
 * This examples shows how to make your own detector using GenericDetector; this is only recommended
 * if no detector exists for your use case.
 */
@Autonomous(group="DogeCV")
public class GenericDetectorExample extends OpMode {
    // Detector object
    GenericDetector detector = new GenericDetector();

    @Override
    public void init() {
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());

        detector.useDefaults();

        detector.ratioScorer.perfectRatio = 1.0;
        detector.ratioScorer.weight = 3.0;
        detector.maxAreaScorer.weight = 1.0;

        detector.colorFilter = new HSVColorFilter(new Scalar(55, 67, 95), new Scalar(20, 40, 30));

        detector.enable();
    }

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

    @Override
    public void stop() {
        // Disable the detector
        if(detector != null) detector.disable();
    }
}
