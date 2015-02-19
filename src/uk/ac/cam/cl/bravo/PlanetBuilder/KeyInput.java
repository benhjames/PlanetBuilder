package uk.ac.cam.cl.bravo.PlanetBuilder;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

public class KeyInput implements KeyEventDispatcher {
    MainWindow window;

    public KeyInput(MainWindow mainWindow) {
        window = mainWindow;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            switch (e.getKeyCode()) {
				case 37: System.out.println("left");
					break;
				case 39: System.out.println("right");
					break;
				default: break;
			}
        } else if (e.getID() == KeyEvent.KEY_RELEASED) {
        }
        return false;
    }
}
