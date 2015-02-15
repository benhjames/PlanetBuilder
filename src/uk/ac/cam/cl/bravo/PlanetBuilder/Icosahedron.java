package uk.ac.cam.cl.bravo.PlanetBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* 
 * Based on sphere drawing code from 
 * 
 * http://www.webkinesia.com/online/graphics/notes/normals.php
 * 
 */

public class Icosahedron {
        
		
	
        static final float X = 0.525731112119133606f;
        static final float Z = 0.850650808352039932f;

        static final float vdata[][] = { { -X, 0.0f, Z }, { X, 0.0f, Z },
                        { -X, 0.0f, -Z }, { X, 0.0f, -Z }, { 0.0f, Z, X },
                        { 0.0f, Z, -X }, { 0.0f, -Z, X }, { 0.0f, -Z, -X },
                        { Z, X, 0.0f }, { -Z, X, 0.0f }, { Z, -X, 0.0f },
                        { -Z, -X, 0.0f } };

        static final int tindices[][] = { { 0, 4, 1 }, { 0, 9, 4 }, { 9, 5, 4 },
                        { 4, 5, 8 }, { 4, 8, 1 }, { 8, 10, 1 }, { 8, 3, 10 },
                        { 5, 3, 8 }, { 5, 2, 3 }, { 2, 7, 3 }, { 7, 10, 3 },
                        { 7, 6, 10 }, { 7, 11, 6 }, { 11, 0, 6 }, { 0, 1, 6 },
                        { 6, 1, 10 }, { 9, 0, 11 }, { 9, 11, 2 }, { 9, 2, 5 },
                        { 7, 2, 11 } };
        
        private static ArrayList<Triangle> generateInitialIcosahedron(){
        	ArrayList<Triangle> icosahedron = new ArrayList<>();
            ArrayList<Vertex> verticies = new ArrayList<>();
            
            for (float[] V: vdata){
            	verticies.add(new Vertex(V[0], V[1], V[2]));
            }
            
            for (int[] T: tindices){
            	icosahedron.add(new Triangle(verticies.get(T[0]), verticies.get(T[1]), verticies.get(T[2])));
            }
            
            return icosahedron;
            
        }
        
        public static ArrayList<Triangle> generateIcosahedron(int subdivisions) {
        
                ArrayList<Triangle> icosahedron = generateInitialIcosahedron();

                for (int i = 0; i < subdivisions; ++i) {
                        subdivide(icosahedron);
                }

                return icosahedron;
        }
        
        private static void subdivide(ArrayList<Triangle> icosahedron) {
        	HashMap< UnorderedPair<Integer, Integer> ,Vertex> table = new HashMap< UnorderedPair<Integer, Integer>, Vertex> ();
    		
			ArrayList<Triangle> newTriangles = new ArrayList<Triangle>();
			
			for (Triangle t: icosahedron){
				Vertex v12 = getHalfPoint(t.getV1(), t.getV2(), table);
				Vertex v23 = getHalfPoint(t.getV2(), t.getV3(), table);
				Vertex v31 = getHalfPoint(t.getV3(), t.getV1(), table);
				
				v12.Normalize();
				v23.Normalize();
				v31.Normalize();
				
				newTriangles.add(new Triangle(t.getV1(), v12, v31));
				newTriangles.add(new Triangle(t.getV2(), v12, v23));
				newTriangles.add(new Triangle(t.getV3(), v23, v31));
				newTriangles.add(new Triangle(v12, v23, v31));
			}
			
			icosahedron	= newTriangles;		
		}

		private static Vertex getHalfPoint(Vertex v1, Vertex v2,
			HashMap<UnorderedPair<Integer, Integer>, Vertex> table) {
			
			Vertex result = table.get(new UnorderedPair<Integer, Integer>(v1.id, v2.id));
			if (result == null) {
				result = new Vertex( (v1.getX() + v2.getX())/2f, (v1.getY() + v2.getY())/2f, (v1.getZ() + v2.getZ())/2f );
				table.put(new UnorderedPair<Integer, Integer>(v1.id, v2.id), result);
			}
			
			return result;
		}
}
