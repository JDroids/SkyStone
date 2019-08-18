package org.firstinspires.ftc.teamcode.dogecv;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.BlankDetector;
import com.disnodeteam.dogecv.detectors.DogeCVDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.opencv.core.Mat;
import org.opencv.core.Point;

/**
 * Created by LeviG on 1/20/2019
 * <p>
 * This class is useful for finding the size you want to crop your camera image to.
 */
@Autonomous(group = "DogeCV")
public class CroppingExample extends OpMode {
    // Where to put the top left corner of the crop in pixel (x, y) coordinates
    private final Point TL_CORNER_CROP_POS = new Point(200, 200);
    // Where to put the bottom right corner of the crop in pixel (x, y) coordinates
    private final Point BR_CORNER_CROP_POS = new Point(400, 400);

    //This is an example of an anonymous implementation of the DogeCV Detector
    private DogeCVDetector detector = new BlankDetector();

    @Override
    public void init() {
        // Set up detector
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize it with the app context and camera

        // Sets the top left corner of the new image
        detector.cropTLCorner = TL_CORNER_CROP_POS;
        // Sets the bottom right corner of the new image,
        detector.cropBRCorner = BR_CORNER_CROP_POS;

        // Enables the detector
        detector.enable();
    }

    @Override
    public void loop() {
        // Add information about the crop
        telemetry.addLine()
                .addData("Top Left Crop Position", TL_CORNER_CROP_POS)
                .addData("Bottom Right Crop Position", BR_CORNER_CROP_POS);
        // Update telemetry
        telemetry.update();
    }

    @Override
    public void stop() {
        // Disable the detector
        if (detector != null) detector.disable();
    }
}
