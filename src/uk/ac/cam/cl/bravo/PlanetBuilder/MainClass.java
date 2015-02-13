package uk.ac.cam.cl.bravo.PlanetBuilder;

//Main class which launches the graphics environment


import com.jogamp.opengl.util.*;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainClass {

    public static JFrame window;

    public static int windowWidth = 1466;
    public static int windowHeight = 768;

    public static int controlWidth = 200;
    public static int controlHeight = windowHeight;

	public static int canvasWidth = windowWidth - controlWidth;
	public static int canvasHeight = windowHeight;

    public static void main(String[] args) {
        System.out.println("Planet Builder launched");
        //Prevents Linux locking crashes
        GLProfile.initSingleton();
        GLProfile glp = GLProfile.get(GLProfile.GL3);
        GLCapabilities glc = new GLCapabilities(glp);
        GLJPanel canvas = new GLJPanel(glc);
        canvas.setSize(canvasWidth, canvasHeight);

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

        JPanel controls = new CommandAndControl();

        window = new JFrame("PlanetBuilder");
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        window.setSize(windowWidth, windowHeight);
        window.setResizable(false);
        window.getContentPane().add(canvas, BorderLayout.CENTER);
        window.getContentPane().add(controls, BorderLayout.EAST);
        window.setVisible(true);

        canvas.addGLEventListener(new MainWindow());

	    FPSAnimator animator = new FPSAnimator(canvas, 60);
	    animator.start();
    }

}
