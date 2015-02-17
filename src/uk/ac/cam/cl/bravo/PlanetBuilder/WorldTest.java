package uk.ac.cam.cl.bravo.PlanetBuilder;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class WorldTest{
	
	private World w;
	private GlobalOptions GO;
	private WorldOptions WO;
	
    @Before
    public void setUp() {
        w = World.getInstance();
        GO = GlobalOptions.getInstance();
        GO.setDetailLevel(0.5f); 
        WO = WorldOptions.getInstance();
        WO.setSettlementLevel(20);
        WO.setTerrainFactor(100);
        WO.setWaterFactor(50);
        WO.setVegetationFactor(20);
        WO.setGroundStart(Color.GREEN);
        WO.setGroundEnd(Color.ORANGE);
        WO.setWaterStart(Color.BLUE);
        WO.setWaterEnd(Color.BLUE);
        assertNotNull(w);
    }
    
    @Test
    public void testeverything() {
    	
        w.initializeGlobal(GO);
        w.finalizeWorld(WO);
        assertEquals(w.getCloudVertexArray().length, 9*20*4*4);
        assertEquals(w.getSurfaceVertexArray().length, 9*20*4*4);
        assertEquals(w.getSeaVertexArray().length, 9*20*4*4);

        assertEquals(w.getCloudColorArray().length, 12*20*4*4);
        assertEquals(w.getSurfaceColorArray().length, 12*20*4*4);
        assertEquals(w.getSeaColorArray().length, 12*20*4*4);
    }
}