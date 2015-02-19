package uk.ac.cam.cl.bravo.PlanetBuilder;


import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Vector;

public class Leap {

    private static MainWindow window;
    private static Controller controller = new Controller();
    private static Frame lastFrame = controller.frame();

    private static final float translationProb = 0.5f;
    private static final float translationMulti = 0.3f;
    private static final float zoomMulti = 0.3f;

    public static void setWindow(MainWindow mainWindow) {
        window = mainWindow;
    }

    public static void updateLeapControls() {
        if (controller.isConnected()) {
            Frame thisFrame = controller.frame();

            Vector translation = thisFrame.translation(lastFrame);
            if (thisFrame.translationProbability(lastFrame) > translationProb) {
                double xTheta = translation.getX() * translationMulti;
                double yTheta = -1 * translation.getZ() * translationMulti; //Our and leap's co'ord systems are different
                window.getCamera().circle(xTheta, yTheta);

                float zoom = translation.getY() * zoomMulti;
                window.getCamera().zoom(zoom);
            }

            lastFrame = thisFrame;
        }
    }
}
