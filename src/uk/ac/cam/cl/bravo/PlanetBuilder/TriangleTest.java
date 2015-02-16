package uk.ac.cam.cl.bravo.PlanetBuilder;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

class TriangleTest{
	
	private World w;
	private GlobalOptions GO;
	private WorldOptions WO;
	private Triangle T;
	
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
        
        T = new Triangle(new Vertex(0f,0f,1f), new Vertex(0f,1f,0f), new Vertex(1f, 0f, 0f));
        
    }
    
    @Test
    public void testSurfaceUpdate() {
    	
        T.assignSurface(WO);
        assert(Color.GREEN.equals( T.getC1()));
        assert(Color.GREEN.equals( T.getC2()));
        assert(Color.GREEN.equals( T.getC3()));
        
    }
    
    @Test
    public void testSeaUpdate() {
    	
        T.assignSea(WO);
        assert(Color.BLUE.equals( T.getC1()));
        assert(Color.BLUE.equals( T.getC2()));
        assert(Color.BLUE.equals( T.getC3()));
    
    }
    
    @Test
    public void testCloudUpdate() {
    	
        T.assignCloud(WO);
    
    }
}