package uk.ac.cam.cl.bravo.PlanetBuilder;

//Main class which launches the graphics environment


import com.jogamp.opengl.util.*;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainClass {

    public static void main(String[] args) {
        System.out.println("Planet Builder launched");
        //Prevents Linux locking crashes
        GLProfile.initSingleton();
        GLProfile glp = GLProfile.get(GLProfile.GL3);
        GLCapabilities glc = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(glc);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        Frame controls = new CommandAndControl();
        controls.setVisible(true);

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

	    FPSAnimator animator = new FPSAnimator(canvas, 60);
	    animator.start();
    }

}
