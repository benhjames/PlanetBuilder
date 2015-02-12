package uk.ac.cam.cl.bravo.PlanetBuilder;

import java.util.ArrayList;

class World{
	private static final double SUBDIVISIONMULT = 5;
	
    public void initializeGlobal(GlobalOptions GO){
    	int subdivisions = (int) (GO.getDetailLevel() * SUBDIVISIONMULT);
        SurfaceTriangles = Icosahedron.generateIcosahedron(subdivisions);
        SeaTriangles = Icosahedron.generateIcosahedron(subdivisions);
        CloudTriangles = Icosahedron.generateIcosahedron(subdivisions);
    }

    public void finalizeWorld(WorldOptions WO) {
        for (Triangle T: SurfaceTriangles){
        	T.assignSurface(WO);
        }
        for (Triangle T: CloudTriangles){
        	T.assignCloud(WO);
        }
        for (Triangle T: SeaTriangles){
        	T.assignSea(WO);
        }
    }

    private ArrayList<Triangle> SurfaceTriangles;
    private ArrayList<Triangle> CloudTriangles;
    private ArrayList<Triangle> SeaTriangles;

    public ArrayList<Triangle> getSurface(){
        return SurfaceTriangles;
    }

    public ArrayList<Triangle> getCloud(){
        return CloudTriangles;
    }

    public ArrayList<Triangle> getSea(){
        return SeaTriangles;
    }
}