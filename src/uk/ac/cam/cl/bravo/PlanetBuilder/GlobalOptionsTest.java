package uk.ac.cam.cl.bravo.PlanetBuilder;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class GlobalOptionsTest {
    private static final float DELTA = 1e-15f;
    private GlobalOptions go;

    @Before
    public void setUp() {
        go = GlobalOptions.getInstance();
    }

    @Test
    public void testInitialise() {
        assertNotNull(go);
        assertEquals(go.isRenderHigh(), true);
        assertEquals(go.getDetailLevel(), 50.0f, DELTA);
    }

    @Test
    public void testSettersAndGettersValid() {
        float f = (float) Math.random() * 100f;

        go.setDetailLevel(f);
        assertEquals(f, go.getDetailLevel(),DELTA);

        boolean b = new Random().nextBoolean();

        go.setRenderHigh(b);
        assertEquals(b, go.isRenderHigh());
    }

    @Test
    public void testInvalidSetters() {
        float validIn = (float) Math.random() * 100f;

        //Set valid inputs
        go.setDetailLevel(validIn);

        float invalidIn = 0.0f;
        while (invalidIn >= 0.0 && invalidIn <= 100.0f) {//Ensure invalid input
            invalidIn = (float) ((Math.random() - 0.5) * 2 * Float.MAX_VALUE);
        }

        go.setDetailLevel(invalidIn);
        assertEquals(validIn, go.getDetailLevel(), DELTA);
    }
}