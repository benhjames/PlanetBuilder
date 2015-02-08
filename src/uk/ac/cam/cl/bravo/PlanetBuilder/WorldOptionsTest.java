package uk.ac.cam.cl.bravo.PlanetBuilder;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Random;

import static org.junit.Assert.*;

//Test class for the WorldOptions class
public class WorldOptionsTest {

    private static final float DELTA = 1e-15f;
    private WorldOptions wo;

    @Before
    public void setUp() {
        wo = new WorldOptions();
    }


    @Test
    public void testInitialise() {
        //At the minute, only have default constructor so test is basic!
        assertNotNull(wo);
    }

    @Test
    public void testSettersAndGettersValid() {
        float f = (float) Math.random() * 100f;

        wo.setDesertFactor(f);
        assertEquals(f, wo.getDesertFactor(),DELTA);

        wo.setTerrainFactor(f);
        assertEquals(f, wo.getTerrainFactor(),DELTA);

        wo.setVegetationFactor(f);
        assertEquals(f, wo.getVegetationFactor(),DELTA);

        wo.setIceFactor(f);
        assertEquals(f, wo.getIceFactor(),DELTA);

        wo.setWaterFactor(f);
        assertEquals(f, wo.getWaterFactor(), DELTA);

        wo.setSettlementLevel(f);
        assertEquals(f, wo.getSettlementLevel(), DELTA);

        boolean b = new Random().nextBoolean();

        wo.setInhabitants(b);
        assertEquals(b, wo.isInhabitants());

        wo.setPlanetRings(b);
        assertEquals(b, wo.isPlanetRings());

        Color c = new Color(new Random().nextInt(255));

        wo.setGroundStart(c);
        assertEquals(c, wo.getGroundStart());

        wo.setGroundEnd(c);
        assertEquals(c, wo.getGroundEnd());

        wo.setWaterStart(c);
        assertEquals(c, wo.getWaterStart());

        wo.setWaterEnd(c);
        assertEquals(c, wo.getWaterEnd());
    }

    @Test
    public void testInvalidSetters() {
        float validIn = (float) Math.random() * 100f;

        //Set valid inputs
        wo.setDesertFactor(validIn);
        wo.setTerrainFactor(validIn);
        wo.setVegetationFactor(validIn);
        wo.setIceFactor(validIn);
        wo.setWaterFactor(validIn);
        wo.setSettlementLevel(validIn);

        float invalidIn = 0.0f;
        while (invalidIn>=0.0 && invalidIn <= 100.0f) {//Ensure invalid input
            invalidIn = (float) ((Math.random() - 0.5) * 2 * Float.MAX_VALUE);
        }

        wo.setDesertFactor(invalidIn);
        assertEquals(validIn, wo.getDesertFactor(),DELTA);

        wo.setTerrainFactor(invalidIn);
        assertEquals(validIn, wo.getTerrainFactor(),DELTA);

        wo.setVegetationFactor(invalidIn);
        assertEquals(validIn, wo.getVegetationFactor(),DELTA);

        wo.setIceFactor(invalidIn);
        assertEquals(validIn, wo.getIceFactor(),DELTA);

        wo.setWaterFactor(invalidIn);
        assertEquals(validIn, wo.getWaterFactor(), DELTA);

        wo.setSettlementLevel(invalidIn);
        assertEquals(validIn, wo.getSettlementLevel(), DELTA);
    }

    @Test
    public void testSeed() {
        WorldOptions wo = new WorldOptions();
        wo.newSeed();
        long d = wo.getSeed();
        wo.newSeed();
        assertNotEquals(d, wo.getSeed(), DELTA);
    }

    @Test
    public void testIO() {
        try {
            int hash1 = wo.hashCode();
            WorldOptions.writeToFile(wo, "testWO.pbo");
            wo.readFromFile("testWO.pbo");
            assertEquals(hash1, wo.hashCode());
            Files.deleteIfExists(FileSystems.getDefault().getPath("testWO.pbo"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}