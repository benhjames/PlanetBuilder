package uk.ac.cam.cl.bravo.PlanetBuilder;

import java.awt.Color;

class Triangle {
	private static double TIME = 0;
	
	
    //3 Verticies
    private Vertex v1, v2, v3;

    private Color c1, c2, c3;
    
    private Color interpolate(Color C1, Color C2, double weight) {
    	int red = (int) (C1.getRed()* weight + C2.getRed() * (1-weight));
    	int green = (int) (C1.getGreen()* weight + C2.getGreen() * (1-weight));
    	int blue = (int) (C1.getBlue()* weight + C2.getBlue() * (1-weight));
    	
    	return new Color(red, green, blue);
    }
    
    public void assignSurface(WorldOptions WO){
    	v1.update(WO);
    	v2.update(WO);
    	v3.update(WO);
    	
    	double avgX = (v1.getX() + v2.getX() + v3.getX()) / 3;
    	double avgY = (v1.getY() + v2.getY() + v3.getY()) / 3;
    	double avgZ = (v1.getZ() + v2.getZ() + v3.getZ()) / 3;
    	
    	//Assing Type: settlement/vegetation/land
    	
    	double fillingTypeNoise = Noise.noise(avgX, avgY, avgZ, TIME , (int) WO.getSeed() + Seeds.FillingTypeSeed);
    	
    	double settlementBound = WO.getSettlementLevel() / 100;
    	double vegetationBound = settlementBound + WO.getVegetationFactor();
    	if (vegetationBound > 1){
    		settlementBound /= vegetationBound;
    		vegetationBound = 1;
    	}
    	
    	if (fillingTypeNoise < settlementBound) {
    		c1 = Color.GRAY;
    		c2 = Color.GRAY;
    		c3 = Color.GRAY;
    	} else if (fillingTypeNoise < vegetationBound) {
    		c1 = Color.GREEN;
    		c2 = Color.GREEN;
    		c3 = Color.GREEN;
    	} else {
    		c1 = interpolate(WO.getGroundStart() , WO.getGroundEnd(), v1.getHeightNoise());
    		c2 = interpolate(WO.getGroundStart() , WO.getGroundEnd(), v2.getHeightNoise());
    		c3 = interpolate(WO.getGroundStart() , WO.getGroundEnd(), v3.getHeightNoise());
    	}
    	
    }
    
    public void assignCloud(WorldOptions WO){
    	v1.update(WO);
    	v2.update(WO);
    	v3.update(WO);
    	
    	
    	c1 = interpolate(Color.BLUE,new Color(0,0,0,0), v1.getClimateNoise());
    	c2 = interpolate(Color.BLUE,new Color(0,0,0,0), v2.getClimateNoise());
    	c3 = interpolate(Color.BLUE,new Color(0,0,0,0), v3.getClimateNoise());
    }
    
    public void assignSea(WorldOptions WO){
    	v1.update(WO);
    	v2.update(WO);
    	v3.update(WO);
    	
    	
    }
    
    //Constructor with the 3 Verticies as argumants
    public Triangle(Vertex V1, Vertex V2, Vertex V3){
        v1 = V1;
        v2 = V2;
        v3 = V3;
    }

	public Vertex getV1() {
		return v1;
	}

	public Vertex getV2() {
		return v2;
	}

	public Vertex getV3() {
		return v3;
	}

	public Color getC1() {
		return c1;
	}
	
	public Color getC2() {
		return c2;
	}
	
	public Color getC3() {
		return c3;
	}
	
	
	
}