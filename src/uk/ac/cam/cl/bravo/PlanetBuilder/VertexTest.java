package uk.ac.cam.cl.bravo.PlanetBuilder;

import static org.junit.Assert.assertNotNull;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

class VertexTest{
	
	private World w;
	private GlobalOptions GO;
	private WorldOptions WO;
	private Vertex V;
	
    @Before
    public void setUp() {
        w = World.getInstance();
        assertNotNull(w);
        
        GO = GlobalOptions.getInstance();
        GO.setDetailLevel(0.5f);
        
        WO = WorldOptions.getInstance();
        WO.setSettlementLevel(20);
        WO.setTerrainFactor(100);
        WO.setWaterFactor(50);
        WO.setVegetationFactor(20);
        WO.setGroundStart(Color.GREEN);
        WO.setGroundEnd(Color.GREEN);
        WO.setWaterStart(Color.BLUE);
        WO.setWaterEnd(Color.BLUE);
        
        V = new Vertex(1f, 0, 0);
        
    }
    
    @Test
    public void testupdateHeight() {
    	
        V.updateHeight(WO, 2f);
        assert(V.getX() == 2f);
        assert(V.getY() == 0f);
        assert(V.getZ() == 0f);
        
    }
    
    @Test
    public void testheightNoise() {
    	
        assert(V.getHeightNoise() <= 1f && V.getHeightNoise() >= 0f);
        
    }
    
    @Test
    public void testclimateNoise() {
    	
        assert(V.getClimateNoise() <= 1f && V.getClimateNoise() >= 0f);
        
    }
    
}