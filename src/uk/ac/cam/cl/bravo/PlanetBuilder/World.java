package uk.ac.cam.cl.bravo.PlanetBuilder;

import java.awt.Color;
import java.util.ArrayList;

class World{
	private static final double SUBDIVISIONMULT = 1;
	
	// SINGLETON
	private static World instance = new World();
	public static World getInstance() {
		return instance;
	}
	
	private World(){
		
	}
	
	
	// Initalize Global Options
    public void initializeGlobal(){
        GlobalOptions GO = GlobalOptions.getInstance();
    	int subdivisions = (int) (GO.getDetailLevel() * SUBDIVISIONMULT);
        SurfaceTriangles = Icosahedron.generateIcosahedron(subdivisions);
        SeaTriangles = Icosahedron.generateIcosahedron(subdivisions);
        CloudTriangles = Icosahedron.generateIcosahedron(subdivisions);
    }

    
    // Finalize World Options
    public void finalizeWorld() {
        WorldOptions WO = WorldOptions.getInstance();
        for (Triangle T: SurfaceTriangles){
        	T.assignSurface(WO);
        }
        for (Triangle T: CloudTriangles){
        	T.assignCloud(WO);
        }
        for (Triangle T: SeaTriangles){
        	T.assignSea(WO);
        }
        
        surfaceVertexArray = GenerateVertexArray(SurfaceTriangles);
        seaVertexArray = GenerateVertexArray(SeaTriangles);
        cloudVertexArray = GenerateVertexArray(CloudTriangles);

        surfaceColorArray = GenerateColorArray(SurfaceTriangles);
        seaColorArray = GenerateColorArray(SeaTriangles);
        cloudColorArray = GenerateColorArray(CloudTriangles);

    }

    private float[] GenerateVertexArray(ArrayList<Triangle> Triangles) {
		float[] result = new float[Triangles.size() * 9];
		int i = 0;
		for (Triangle t:Triangles) {
			insertVertex(t.getV1(), i, result);
			i += 3;
			insertVertex(t.getV2(), i, result);
			i += 3;
			insertVertex(t.getV3(), i, result);
			i += 3;
		}
		return result;
	}

	private void insertVertex(Vertex v1, int i, float[] result) {
		result[i] = (float) v1.getX();
		result[i+1] = (float) v1.getY();
		result[i+2] = (float) v1.getZ();
	}

	private float[] GenerateColorArray(ArrayList<Triangle> Triangles) {
		float[] result = new float[Triangles.size() * 12];
		int i = 0;
		for (Triangle t:Triangles) {
			insertColor(t.getC1(), i, result);
			i += 4;
			insertColor(t.getC2(), i, result);
			i += 4;
			insertColor(t.getC3(), i, result);
			i += 4;
		}
		return result;
	}


	private void insertColor(Color c1, int i, float[] result) {		
		
		result[i] = c1.getRed() / 255;
		result[i+1] = c1.getGreen() / 255;
		result[i+2] = c1.getBlue() / 255;
		result[i+3] = c1.getAlpha() / 255;
		
	}


	private ArrayList<Triangle> SurfaceTriangles = new ArrayList<Triangle>();
    private ArrayList<Triangle> CloudTriangles = new ArrayList<Triangle>();
    private ArrayList<Triangle> SeaTriangles = new ArrayList<Triangle>();
    
    private float[] surfaceVertexArray = null;
    private float[] seaVertexArray = null;
    private float[] cloudVertexArray = null;
    
    private float[] surfaceColorArray = null;
    private float[] seaColorArray = null;
    private float[] cloudColorArray = null;
    
    public float[] getSurfaceVertexArray() {
    	return surfaceVertexArray;
    }

	public float[] getSeaVertexArray() {
		return seaVertexArray;
	}

	public float[] getCloudVertexArray() {
		return cloudVertexArray;
	}

	public float[] getSurfaceColorArray() {
		return surfaceColorArray;
	}

	public float[] getSeaColorArray() {
		return seaColorArray;
	}

	public float[] getCloudColorArray() {
		return cloudColorArray;
	}
    
}
