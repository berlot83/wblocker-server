package com.molokotech;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.processing.edges.CannyEdgeDetector;
import org.openimaj.math.geometry.point.Point2d;
import org.openimaj.math.geometry.point.Point2dImpl;
import org.openimaj.video.VideoDisplay;
import org.openimaj.video.VideoDisplayListener;
import org.openimaj.video.capture.VideoCapture;
import org.openimaj.video.capture.VideoCaptureException;

public class CounterInOut {
    public static void main(String[] args) throws VideoCaptureException {
        VideoCapture vc = new VideoCapture(640, 480);
        VideoDisplay<MBFImage> vd = VideoDisplay.createVideoDisplay(vc);
        vd.addVideoListener(
                new VideoDisplayListener<MBFImage>() {
                    public void beforeUpdate(MBFImage frame) {

                        frame.processInplace(new CannyEdgeDetector());
                        float separator = (frame.getWidth() * 0.05f);
                        // Linea 1
                        Point2d p1 = new Point2dImpl((frame.getWidth() / 2 - separator), frame.getHeight() / 2);
                        Point2d p2 = new Point2dImpl(separator, frame.getHeight() / 2);
                        frame.drawLine(p1, p2, 3, RGBColour.BLUE);


                        // Linea 2
                        Point2d p3 = new Point2dImpl((frame.getWidth() / 2 + separator), frame.getHeight() / 2);
                        Point2d p4 = new Point2dImpl(frame.getWidth() - separator, frame.getHeight() / 2);
                        frame.drawLine(p3, p4, 3, RGBColour.RED);
                    }

                    public void afterUpdate(VideoDisplay<MBFImage> display) {
                    }
                });

    }
}
