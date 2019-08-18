package org.firstinspires.ftc.teamcode.dogecv.roverruckus;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldDetector;
import com.disnodeteam.dogecv.filters.LeviColorFilter;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Rect;

@Autonomous(group="DogeCV")
public class WebcamGoldExample extends OpMode {
    //Webcam object
    WebcamName webcamName;

    // DogeCV detector
    GoldDetector detector;

    @Override
    public void init() {
        telemetry.addData("Status", "DogeCV 2019.1 - Webcam Gold Example");

        webcamName = hardwareMap.get(WebcamName.class, "Webcam 1"); //Retrieves the webcam from the hardware map

        detector = new GoldDetector(); // Create a Gold Detector

        //Sets the Vuforia license key. ALWAYS SET BEFORE INIT!
        detector.VUFORIA_KEY = "---INSERT YOUR KEY HERE---";
        
        //Inits the detector. Choose which camera to use, and whether to detect VuMarks here
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance(), DogeCV.CameraMode.WEBCAM, false, webcamName);

        //Sets basic detector settings
        detector.yellowFilter = new LeviColorFilter(LeviColorFilter.ColorPreset.YELLOW, 100); // Create new filter
        detector.useDefaults(); // Use default settings
        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // Uncomment if using PERFECT_AREA scoring

        detector.enable();
    }

    @Override
    public void loop() {
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

        telemetry.update();
    }

    @Override
    public void stop() {
        // Disable the detector
        if (detector != null) detector.disable();
    }

}