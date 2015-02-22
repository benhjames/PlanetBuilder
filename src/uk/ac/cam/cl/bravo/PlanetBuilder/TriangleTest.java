package uk.ac.cam.cl.bravo.PlanetBuilder;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class TriangleTest{
	
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
        WO.setSettlementLevel(100);
        WO.setTerrainFactor(0);
        WO.setWaterFactor(0);
        WO.setVegetationFactor(0);
        WO.setIceFactor(0);
        WO.setDesertFactor(0);
        WO.setGroundStart(Color.GREEN);
        WO.setGroundEnd(Color.GREEN);
        WO.setWaterStart(Color.BLUE);
        WO.setWaterEnd(Color.BLUE);
        
        T = new Triangle(new Vertex(1f,0f,0f), new Vertex(0f,1f,0f), new Vertex(0f, 0f, 1f));
        
        
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
        //assert(Color.BLUE.equals( T.getC1()));
        //assert(Color.BLUE.equals( T.getC2()));
        //assert(Color.BLUE.equals( T.getC3()));
    
    }
    
    @Test
    public void testCloudUpdate() {
    	
        T.assignCloud(WO);
    
    }
    
    @Test
    public void testmodel() {
    	T.assignSurface(WO);
        float[] m = T.getmodels();
        for (float f: m)
        	System.out.println(f);
    
    }
}