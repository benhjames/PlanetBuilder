package uk.ac.cam.cl.bravo.PlanetBuilder;

class Vertex {

	private static double TIME = 0;
	
    // 3 coordiantes
    private double x,y,z;

    //Noise for the height
    private double heightNoise;

    //Noise for the climate
    private double climateNoise;

    //Constructor with no argument- initializes everything to 0
    public Vertex(){}

    //Constructor with the 3 coordinates as arguments
    public Vertex(double x1, double y1, double z1) {
        x = x1;
        y = y1;
        z = z1;
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


    public void update(WorldOptions WO) {
        double heightNoise = Noise.noise(x, y, z, TIME, (int) WO.getSeed() + Seeds.HeightSeed);
        double climateNoise = Noise.noise(x, y, z, TIME, (int) WO.getSeed() + Seeds.ClimateSeed);
        
        double oldDistance = Math.sqrt(x*x + y*y + z*z);
        double newDistance = WO.getTerrainFactor() * heightNoise;
        
        x *= newDistance / oldDistance;
        y *= newDistance / oldDistance;
        z *= newDistance / oldDistance;
        
    }

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}


}