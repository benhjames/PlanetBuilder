package uk.ac.cam.cl.bravo.PlanetBuilder;


import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Vector;

public class Leap {

    private static MainWindow window;
    private static Controller controller = new Controller();
    private static Frame lastFrame = controller.frame();

    private static final float translationProb = 0.5f;
    private static final float rotationProb = 0.5f;
    private static final float translationMulti = 0.7f;
    private static final float zoomMulti = 0.2f;

    public static void setWindow(MainWindow mainWindow) {
        window = mainWindow;
    }

    public static void updateLeapControls() {
        if (controller.isConnected()) {
            Frame thisFrame = controller.frame();
            double xTheta = 0;
            double yTheta = 0;

            Vector translation = thisFrame.translation(lastFrame);
            if (thisFrame.translationProbability(lastFrame) > translationProb) {
                xTheta = -1 * translation.getX() * translationMulti;
                yTheta = -1 * translation.getZ() * translationMulti; //Our and leap's co'ord systems are different

                float zoom = translation.getY() * zoomMulti;
                window.getCamera().zoom(zoom);
            }

            if (thisFrame.rotationProbability(lastFrame) > rotationProb) {
                xTheta += thisFrame.rotationAngle(lastFrame, Vector.xAxis());
                yTheta -= thisFrame.rotationAngle(lastFrame, Vector.zAxis());
            }

            window.getCamera().circle(xTheta, yTheta);
            lastFrame = thisFrame;
        }
    }
}
