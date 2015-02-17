package uk.ac.cam.cl.bravo.PlanetBuilder;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NoiseTest extends JPanel {
    static int w = 1920;
    static int h = 1200;
    static int z = 0;
    static BufferedImage img;

    public NoiseTest() {
        new RectRunnable();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        for (int x = 0;x < w;x++) {
            for (int y = 0;y < h;y++) {
                double c = Noise.fractal_noise(0.5, 4, (double)x / 256, (double)y / 256, (double)z / 256, 0, 0xABCDEF01);
                if (c > 1 || c < 0)
                    System.out.println("out of bounds");
                img.setRGB(x, y, (int)(0xff * c));
            }
        }
        g2d.drawImage(img, 0, 0, null);
    }

    public static void main(String[] args) {
        img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        JFrame frame = new JFrame();
        frame.add(new NoiseTest());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(w, h);
        frame.setVisible(true);
    }

    class RectRunnable implements Runnable {
        private Thread t;

        public RectRunnable() {
            t = new Thread(this);
            t.start();
        }

        public void run() {
            while (true) {
                repaint();
                z += 1;

                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                }
            }
        }
    }
}
