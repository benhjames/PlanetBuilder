package uk.ac.cam.cl.bravo.PlanetBuilder;

class Vertex {

	private static final float SURFACEVARIATION = 1.0f;

	private static double TIME = 0;
	
	private static int nextId = 0;
	public final int id;
	
    private float x,y,z;
    private final float originalX, originalY, originalZ; 

    private final double heightNoise;

    private final double climateNoise;

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

	    heightNoise = Noise.fractal_noise(WorldOptions.getInstance().getPersistence(), WorldOptions.getInstance().getIterations(),
                originalX, originalY, originalZ, TIME, WorldOptions.getInstance().getSeed() + Seeds.HeightSeed);
	    climateNoise = Noise.fractal_noise(WorldOptions.getInstance().getPersistence(), WorldOptions.getInstance().getIterations(),
                originalX, originalY, originalZ, TIME, WorldOptions.getInstance().getSeed() + Seeds.ClimateSeed);
    }

    public void Normalize(){
    	double Distance = Math.sqrt(x*x + y*y + z*z);
    	x /= Distance;
    	y /= Distance;
    	z /= Distance;
    }
    
    public double getHeightNoise(){
        return heightNoise;
    }
    
    public double getClimateNoise(){
        return climateNoise;
    }

    public void updateHeight(WorldOptions WO) {
    	float maxDifference = SURFACEVARIATION * WO.getTerrainFactor() / 100f;
        float newDistance = (float) ( maxDifference * heightNoise + (1f - maxDifference / 2f)) ;

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