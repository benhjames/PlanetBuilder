package uk.ac.cam.cl.bravo.PlanetBuilder;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

class IcosahedronTest{
	
	private ArrayList<Triangle> triangles;
	
    @Before
    public void setUp() {
        triangles = Icosahedron.generateIcosahedron(3);
        assertNotNull(triangles);
    }
    
    @Test
    public void testgenerateIcosahedron() {
        assertEquals(triangles.size(), 20*4*4*4);
    }
    
}