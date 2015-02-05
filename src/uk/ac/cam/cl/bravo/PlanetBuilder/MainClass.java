package uk.ac.cam.cl.bravo.PlanetBuilder;

//Main class which launches the graphics environment


import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainClass {

    public static void main(String[] args) {
        System.out.println("Planet Builder launched");
        //Prevents Linux locking crashes
        GLProfile.initSingleton();
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities glc = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(glc);

        Frame frame = new Frame("Planet Builder");
        frame.setSize(1366, 768);
        frame.add(canvas);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        canvas.addGLEventListener(new MainWindow());
    }

}
