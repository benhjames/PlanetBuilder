package uk.ac.cam.cl.bravo.PlanetBuilder;

class Vertex {

	private static final float SURFACEVARIATION = 0.5f;

	private static double TIME = 0;
	
	private static int nextId = 0;
	public final int id;
	
    // 3 coordiantes
    private float x,y,z;
    private final float originalX, originalY, originalZ; 

    //Noise for the height
    private final double heightNoise;

    //Noise for the climate
    private final double climateNoise;

    //Constructor with the 3 coordinates as arguments
    public Vertex(float x1, float y1, float z1) {
    	
        id = nextId;
        nextId++;
        
        x = x1;
        y = y1;
        z = z1;
        
        Normalize();
        
        originalX = x;
        originalY = y;
        originalZ = z;
        
        heightNoise = Noise.noise(originalX, originalY, originalZ, TIME, (int) WorldOptions.getInstance().getSeed() + Seeds.HeightSeed);
        climateNoise = Noise.noise(originalX, originalY, originalZ, TIME, (int) WorldOptions.getInstance().getSeed() + Seeds.ClimateSeed);
    }

    public void Normalize(){
    	double Distance = Math.sqrt(x*x + y*y + z*z);
    	x /= Distance;
    	y /= Distance;
    	z /= Distance;
    }
    
    //Returns the height ie the distance from the origin
    public double getHeightNoise(){
        return heightNoise;
    }
    
    public double getClimateNoise(){
        return climateNoise;
    }


    public void updateHeight(WorldOptions WO) {
    	
        float newDistance = (float) (SURFACEVARIATION * WO.getTerrainFactor() * heightNoise + (1f - SURFACEVARIATION / 2f)) ;

        x = originalX * newDistance;
        y = originalY * newDistance;
        z = originalZ * newDistance;
        
    }
    
    public void updateHeight(WorldOptions WO, float height) {
    	
        float newDistance = height;

        x = originalX * newDistance;
        y = originalY * newDistance;
        z = originalZ * newDistance;
        
    }

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}


}