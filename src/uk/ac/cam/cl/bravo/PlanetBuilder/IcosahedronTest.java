package uk.ac.cam.cl.bravo.PlanetBuilder;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class IcosahedronTest{
	
	private ArrayList<Triangle> triangles = null;
	
	public IcosahedronTest() {};
	
    @Before
    public void setUp() {
        triangles = Icosahedron.generateIcosahedron(3);
        
    }
    
    @Test
    public void testgenerateIcosahedron() {
        assertNotNull(triangles);
        System.out.println(triangles.size());
        assertEquals(triangles.size(), 20*4*4*4);
    }
    
}