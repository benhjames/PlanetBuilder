package uk.ac.cam.cl.bravo.PlanetBuilder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
    private static MainWindow window;

	private static boolean[] keyPressed = new boolean[256];
	private static boolean[] keyHold = new boolean[256];
	private static boolean[] keyReleased = new boolean[256];

	public static void setWindow(MainWindow mainWindow) {
        window = mainWindow;
    }

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyHold[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyHold[e.getKeyCode()] = false;
		keyReleased[e.getKeyCode()] = true;
	}

	public static void update() {
		for (int i=0; i<128; i++) {
			if (keyHold[i]) {
				runAction(i);
			}
			keyPressed[i] = false;
			keyReleased[i] = false;
		}
	}

	public static void runAction(int e) {
		switch (e) {
			case 37: //left
				window.getCamera().circle(-1.0f, 0f);
				break;
			case 39: //right
				window.getCamera().circle(1.0f, 0f);
				break;
			case 38: //up
				window.getCamera().circle(0f, 1.0f);
				break;
			case 40: //down
				window.getCamera().circle(0f, -1.0f);
				break;
			default:
				break;
		}
	}
}
