package uk.ac.cam.cl.bravo.PlanetBuilder;

import java.awt.Color;

class Triangle {


	private static double TIME = 0;
	private static final double CLIMATEFACTOR = 0.5;

	//3 Verticies
	private Vertex v1, v2, v3;

	private Color c1, c2, c3;

	private final double fillingTypeNoise;
	private final double fillingSettlementNoise;

	private Color interpolate(Color C1, Color C2, double weight) {
		weight = 1f - weight;

		float red = (float) (C1.getRed() * weight + C2.getRed() * (1f-weight)) ;
		float green = (float) (C1.getGreen() * weight + C2.getGreen() * (1f-weight)) ;
		float blue = (float) (C1.getBlue() * weight + C2.getBlue() * (1f-weight)) ;
		float alpha = (float) (C1.getAlpha() * weight + C2.getAlpha() * (1f-weight)) ;

		return new Color(red / 255f, green / 255f, blue / 255f, alpha / 255f);
	}
	
	private boolean isSettlement(){

		float avgX = (v1.getX() + v2.getX() + v3.getX()) / 3f;
		float avgY = (v1.getY() + v2.getY() + v3.getY()) / 3f;
		float avgZ = (v1.getZ() + v2.getZ() + v3.getZ()) / 3f;
		
		boolean aboveSea = Math.sqrt(avgX*avgX + avgY*avgY + avgZ*avgZ ) > 0.75f + WorldOptions.getInstance().getWaterFactor() / 250f;
		return aboveSea && (fillingSettlementNoise < WorldOptions.getInstance().getSettlementLevel()/100f);
	}
	
	private boolean isAboveSea(){

		float avgX = (v1.getX() + v2.getX() + v3.getX()) / 3f;
		float avgY = (v1.getY() + v2.getY() + v3.getY()) / 3f;
		float avgZ = (v1.getZ() + v2.getZ() + v3.getZ()) / 3f;
		
		boolean aboveSea = Math.sqrt(avgX*avgX + avgY*avgY + avgZ*avgZ ) > 0.75f + WorldOptions.getInstance().getWaterFactor() / 250f;
		return aboveSea;
	}
	
	private float avgHeight() {
		return (float) (v1.getHeightNoise() + v2.getHeightNoise() + v3.getHeightNoise()) / 3f;
	}
	
	public void assignSurface(WorldOptions WO){
		v1.updateHeight(WO);
		v2.updateHeight(WO);
		v3.updateHeight(WO);


		//Assing Type: settlement/vegetation/land

		double desertBound =  WO.getDesertFactor()/100f;
		double vegetationBound = desertBound + WO.getVegetationFactor() / 100f;
		double iceBound = vegetationBound + WO.getIceFactor()/100f;

		if (iceBound > 1){
			vegetationBound /= iceBound;
			desertBound /= iceBound;
			iceBound = 1;
		}
		

		
		
		if (avgHeight() > (WO.getIceFactor() / 200f)) {
			//ice
			Color iceColor1 = new Color(176, 196, 222, 255);
			Color iceColor2 = new Color(230, 230, 250, 255);

			c1 = interpolate(iceColor1, iceColor2, v1.getHeightNoise());
			c2 = interpolate(iceColor1, iceColor2, v2.getHeightNoise());
			c3 = interpolate(iceColor1, iceColor2, v3.getHeightNoise());

		} else if (isSettlement()) {
			//settlement
			Color settlementColor1 = new Color(105, 105, 105, 255);
			Color settlementColor2 = new Color(169, 169, 169, 255);

			c1 = interpolate(settlementColor1, settlementColor2, v1.getClimateNoise());
			c2 = interpolate(settlementColor1, settlementColor2, v2.getClimateNoise());
			c3 = interpolate(settlementColor1, settlementColor2, v3.getClimateNoise());

		} else if (fillingTypeNoise < desertBound && isAboveSea()) {
			//desert
			Color desertColor1 = new Color(218, 165, 32, 255);
			Color desertColor2 = new Color(184, 134, 11, 255);

			c1 = interpolate(desertColor1, desertColor2, v1.getClimateNoise());
			c2 = interpolate(desertColor1, desertColor2, v2.getClimateNoise());
			c3 = interpolate(desertColor1, desertColor2, v3.getClimateNoise());

		} else if (fillingTypeNoise > 1 - (WO.getVegetationFactor() / 100f) && isAboveSea()) {
			//vegetation
			Color vegColor1 = new Color(139, 69, 19, 255);
			Color vegColor2 = new Color(160, 82, 45, 255);

			c1 = interpolate(vegColor1, vegColor2, v1.getClimateNoise());
			c2 = interpolate(vegColor1, vegColor2, v2.getClimateNoise());
			c3 = interpolate(vegColor1, vegColor2, v3.getClimateNoise());

		} else  {
			c1 = interpolate(WO.getGroundStart(), WO.getGroundEnd(), v1.getHeightNoise());
			c2 = interpolate(WO.getGroundStart(), WO.getGroundEnd(), v2.getHeightNoise());
			c3 = interpolate(WO.getGroundStart(), WO.getGroundEnd(), v3.getHeightNoise());

		}

	}


	public void assignCloud(WorldOptions WO){
		v1.updateHeight(WO, 1.5f);
		v2.updateHeight(WO, 1.5f);
		v3.updateHeight(WO, 1.5f);

		Color cloudColor = new Color(224, 255, 255, 127);
		Color transparent = new Color(0, 0, 0, 0);
		c1 = interpolate(cloudColor, transparent, v1.getClimateNoise() * CLIMATEFACTOR);
		c2 = interpolate(cloudColor, transparent, v2.getClimateNoise() * CLIMATEFACTOR);
		c3 = interpolate(cloudColor, transparent, v3.getClimateNoise() * CLIMATEFACTOR);
	}

	public void assignSea(WorldOptions WO){
		v1.updateHeight(WO, 0.75f + WO.getWaterFactor() / 250f);
		v2.updateHeight(WO, 0.75f + WO.getWaterFactor() / 250f);
		v3.updateHeight(WO, 0.75f + WO.getWaterFactor() / 250f);

		c1 = interpolate(WO.getWaterStart(), WO.getWaterEnd(), v1.getHeightNoise());
		c2 = interpolate(WO.getWaterStart(), WO.getWaterEnd(), v2.getHeightNoise());
		c3 = interpolate(WO.getWaterStart(), WO.getWaterEnd(), v3.getHeightNoise());

		c1 = new Color(c1.getRed(), c1.getBlue(), c1.getGreen(), 160);
		c2 = new Color(c2.getRed(), c2.getBlue(), c2.getGreen(), 160);
		c3 = new Color(c3.getRed(), c3.getBlue(), c3.getGreen(), 160);

	}

	public Triangle(Vertex V1, Vertex V2, Vertex V3){
		v1 = V1;
		v2 = V2;
		v3 = V3;

		float avgX = (v1.getX() + v2.getX() + v3.getX()) / 3f;
		float avgY = (v1.getY() + v2.getY() + v3.getY()) / 3f;
		float avgZ = (v1.getZ() + v2.getZ() + v3.getZ()) / 3f;

		fillingTypeNoise = Noise.noise(avgX, avgY, avgZ, TIME , (int) WorldOptions.getInstance().getSeed() + Seeds.FillingTypeSeed);
		fillingSettlementNoise = Noise.noise(avgX, avgY, avgZ, TIME , (int) WorldOptions.getInstance().getSeed() + Seeds.FillingSettlementSeed);
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