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
    
    public void assignLand(WorldOptions WO){
    	v1.update(WO);
    	v2.update(WO);
    	v3.update(WO);
    	
    	double avgX = (v1.x + v2.x + v3.x) / 3;
    	double avgY = (v1.y + v2.y + v3.y) / 3;
    	double avgZ = (v1.z + v2.z + v3.z) / 3;
    	//Filling
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
    
    public float[] getFloatArray(){
    	float[] result = new float[9];
    	
    	result[0] = (float) v1.x;
    	result[1] = (float) v1.y;
    	result[2] = (float) v1.z;
    	result[3] = (float) v2.x;
    	result[4] = (float) v2.y;
    	result[5] = (float) v2.z;
    	result[6] = (float) v3.x;
    	result[7] = (float) v3.y;
    	result[8] = (float) v3.z;
    	
    	return result;
    }
}