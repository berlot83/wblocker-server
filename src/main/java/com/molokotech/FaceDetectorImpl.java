package com.molokotech;
import org.openimaj.image.FImage;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.colour.Transforms;
import org.openimaj.image.processing.face.detection.DetectedFace;
import org.openimaj.image.processing.face.detection.FaceDetector;
import org.openimaj.image.processing.face.detection.HaarCascadeDetector;
import org.openimaj.video.VideoDisplay;
import org.openimaj.video.VideoDisplayListener;
import org.openimaj.video.capture.VideoCapture;
import org.openimaj.video.capture.VideoCaptureException;

import java.util.List;

public class FaceDetectorImpl {
    public static void main(String[] args) throws VideoCaptureException {
        VideoCapture vc = new VideoCapture(320, 240);
        VideoDisplay<MBFImage> vd = VideoDisplay.createVideoDisplay(vc);
        vd.addVideoListener(
                new VideoDisplayListener<MBFImage>() {
                    public void beforeUpdate(MBFImage frame) {
                    	FaceDetector<DetectedFace, FImage> fd = new HaarCascadeDetector(40);
                        List<DetectedFace> faces = fd.detectFaces(Transforms.calculateIntensity(frame));
                        for (DetectedFace face : faces) {
                            frame.drawShape(face.getBounds(), RGBColour.GREEN);
                        }
                    }

                    public void afterUpdate(VideoDisplay<MBFImage> display) {
                    }
                });

    }

}
