package uk.ac.cam.cl.bravo.PlanetBuilder;

import java.awt.Color;
import java.util.Random;

import com.hackoeur.jglm.*;

class Triangle {


	private static double TIME = 0;
	private static final double CLIMATEFACTOR = 0.5;

    public enum Status {
        ICE, SETTLEMENT, DESERT, VEGETATION, GROUND
    }
    Status status;

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
    private float[] models = null;
    private float[] modelsColors = null;

    public float[] getmodels() {
        if ((status == Status.SETTLEMENT && WorldOptions.getInstance().isInhabitants()) || status==Status.VEGETATION) {
            return models;
        }
        else {
            return null;
        }
    }
    public float[] getmodelsColors() { return modelsColors; }
    
	private boolean isSettlement(){

		float avgX = (v1.getX() + v2.getX() + v3.getX()) / 3f;
		float avgY = (v1.getY() + v2.getY() + v3.getY()) / 3f;
		float avgZ = (v1.getZ() + v2.getZ() + v3.getZ()) / 3f;
		
		return isAboveSea() && (fillingSettlementNoise < WorldOptions.getInstance().getSettlementLevel()/100f);
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
		

		
		
		if (avgHeight() > (1f - WO.getIceFactor() / 100f) && isAboveSea()) {
            status = Status.ICE;
			//ice
			Color iceColor1 = new Color(176, 196, 222, 255);
			Color iceColor2 = new Color(230, 230, 250, 255);

			c1 = interpolate(iceColor1, iceColor2, v1.getHeightNoise());
			c2 = interpolate(iceColor1, iceColor2, v2.getHeightNoise());
			c3 = interpolate(iceColor1, iceColor2, v3.getHeightNoise());

            models = null;

		} else if (isSettlement()) {
            status = Status.SETTLEMENT;
			//settlement
			Color settlementColor1 = new Color(105, 105, 105, 255);
			Color settlementColor2 = new Color(169, 169, 169, 255);

			c1 = interpolate(settlementColor1, settlementColor2, v1.getClimateNoise());
			c2 = interpolate(settlementColor1, settlementColor2, v2.getClimateNoise());
			c3 = interpolate(settlementColor1, settlementColor2, v3.getClimateNoise());

            float[] modelsPos = getmidnormal();

            Vec4 pos = new Vec4(modelsPos[0], modelsPos[1], modelsPos[2], 1.0f);
            Vec3 dir = new Vec3(modelsPos[3], modelsPos[4], modelsPos[5]);

            Vec3 rotationDir = dir.cross(new Vec3(0,1,0)).getUnitVector();
            float angle = -dir.angleInRadians(new Vec3(0,1,0));
            float rndAngle = (float)((new Random()).nextInt(90) * 0.0174532925);

            Mat4 rotationMat = Matrices.rotate(angle, rotationDir);
            Mat4 rotationMat2 = Matrices.rotate(rndAngle, dir);
            rotationMat = rotationMat2.multiply(rotationMat);
            Mat4 translationMat = new Mat4((Vec4)rotationMat.getColumn(0), (Vec4)rotationMat.getColumn(1), (Vec4)rotationMat.getColumn(2), pos);

            int vertexCount = StructureModel.getVertexArray().length / 3;
            models = new float[vertexCount * 3];
            modelsColors = new float[vertexCount * 4];

            float scale = 1f/(float)Math.pow(2f, GlobalOptions.getInstance().getDetailLevel() + 2f);
            float heightScale = 1.0f + (new Random()).nextFloat();
            float rndColor = (new Random()).nextFloat() * 15f / 100f;

            for (int i = 0; i < vertexCount; i++) {
                float x = StructureModel.getVertexArray()[i*3 + 0] * scale;
                float y = StructureModel.getVertexArray()[i*3 + 1] * scale * heightScale;
                float z = StructureModel.getVertexArray()[i*3 + 2] * scale;
                float w = 1f;

                Vec4 col0 = translationMat.getColumn(0);
                Vec4 col1 = translationMat.getColumn(1);
                Vec4 col2 = translationMat.getColumn(2);
                Vec4 col3 = translationMat.getColumn(3);

                float newX = (col0.getX()*x + col1.getX()*y + col2.getX()*z + col3.getX()*w);
                float newY = (col0.getY()*x + col1.getY()*y + col2.getY()*z + col3.getY()*w);
                float newZ = (col0.getZ()*x + col1.getZ()*y + col2.getZ()*z + col3.getZ()*w);

                models[i*3 + 0] = newX;
                models[i*3 + 1] = newY;
                models[i*3 + 2] = newZ;

                modelsColors[i*4 + 0] = 0.5f + rndColor;
                modelsColors[i*4 + 1] = 0.5f + rndColor;
                modelsColors[i*4 + 2] = 0.5f + rndColor;
                modelsColors[i*4 + 3] = 1.0f;
            }
            
		} else if (fillingTypeNoise < desertBound && isAboveSea()) {
            status = Status.DESERT;
			//desert
			Color desertColor1 = new Color(218, 165, 32, 255);
			Color desertColor2 = new Color(184, 134, 11, 255);

			c1 = interpolate(desertColor1, desertColor2, v1.getClimateNoise());
			c2 = interpolate(desertColor1, desertColor2, v2.getClimateNoise());
			c3 = interpolate(desertColor1, desertColor2, v3.getClimateNoise());

            models = null;

		} else if (fillingTypeNoise > 1 - (WO.getVegetationFactor() / 100f) && isAboveSea()) {
            status = Status.VEGETATION;
			//vegetation
			Color vegColor1 = new Color(0, 139, 25, 255);
			Color vegColor2 = new Color(119, 160, 14, 255);

			c1 = interpolate(vegColor1, vegColor2, v1.getClimateNoise());
			c2 = interpolate(vegColor1, vegColor2, v2.getClimateNoise());
			c3 = interpolate(vegColor1, vegColor2, v3.getClimateNoise());

            float[] modelsPos = getmidnormal();

            Vec4 pos = new Vec4(modelsPos[0], modelsPos[1], modelsPos[2], 1.0f);
            Vec3 dir = new Vec3(modelsPos[3], modelsPos[4], modelsPos[5]);

            Vec3 rotationDir = dir.cross(new Vec3(0,1,0)).getUnitVector();
            float angle = -dir.angleInRadians(new Vec3(0,1,0));
            float rndAngle = (float)((new Random()).nextInt(90) * 0.0174532925);

            Mat4 rotationMat = Matrices.rotate(angle, rotationDir);
            Mat4 rotationMat2 = Matrices.rotate(rndAngle, dir);
            rotationMat = rotationMat2.multiply(rotationMat);
            Mat4 translationMat = new Mat4((Vec4)rotationMat.getColumn(0), (Vec4)rotationMat.getColumn(1), (Vec4)rotationMat.getColumn(2), pos);

            int vertexCount = VegetationModel.getVertexArray().length / 3;
            models = new float[vertexCount * 3];
            modelsColors = new float[vertexCount * 4];

            float scale = 1f/(float)Math.pow(2f, GlobalOptions.getInstance().getDetailLevel() + 2f);
            float heightScale = 1.0f + ((new Random()).nextFloat() / 5f);

            float rndColor = (new Random()).nextFloat() * 12f / 100f;

            for (int i = 0; i < vertexCount; i++) {
                float x = VegetationModel.getVertexArray()[i*3 + 0] * scale;
                float y = VegetationModel.getVertexArray()[i*3 + 1] * scale * heightScale;
                float z = VegetationModel.getVertexArray()[i*3 + 2] * scale;
                float w = 1f;

                Vec4 col0 = translationMat.getColumn(0);
                Vec4 col1 = translationMat.getColumn(1);
                Vec4 col2 = translationMat.getColumn(2);
                Vec4 col3 = translationMat.getColumn(3);

                float newX = (col0.getX()*x + col1.getX()*y + col2.getX()*z + col3.getX()*w);
                float newY = (col0.getY()*x + col1.getY()*y + col2.getY()*z + col3.getY()*w);
                float newZ = (col0.getZ()*x + col1.getZ()*y + col2.getZ()*z + col3.getZ()*w);

                models[i*3 + 0] = newX;
                models[i*3 + 1] = newY;
                models[i*3 + 2] = newZ;

                modelsColors[i*4 + 0] = 0.32f + rndColor;
                modelsColors[i*4 + 1] = 0.57f + rndColor;
                modelsColors[i*4 + 2] = 0.11f + rndColor;
                modelsColors[i*4 + 3] = 1.0f;
            }

		} else  {
            status = Status.GROUND;

			c1 = interpolate(WO.getGroundStart(), WO.getGroundEnd(), v1.getHeightNoise());
			c2 = interpolate(WO.getGroundStart(), WO.getGroundEnd(), v2.getHeightNoise());
			c3 = interpolate(WO.getGroundStart(), WO.getGroundEnd(), v3.getHeightNoise());

            models = null;

		}

	}


	public void assignCloud(WorldOptions WO){
		v1.updateHeight(WO, 1.25f);
		v2.updateHeight(WO, 1.25f);
		v3.updateHeight(WO, 1.25f);

        double n = 0.125 + (v1.getClimateNoise() + v2.getClimateNoise() + v3.getClimateNoise()) / 3 - 0.5;
        if (n < 0) {
            c1 = new Color(255, 255, 255, 127);
            c2 = new Color(255, 255, 255, 127);
            c3 = new Color(255, 255, 255, 127);
        } else {
            c1 = new Color(0, 0, 0, 0);
            c2 = new Color(0, 0, 0, 0);
            c3 = new Color(0, 0, 0, 0);
        }
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
	
	private float[] getmidnormal(){
		float[] result = new float[6];
		result[0] = (v1.getX() + v2.getX() + v3.getX()) / 3f;
		result[1] = (v1.getY() + v2.getY() + v3.getY()) / 3f;
		result[2] = (v1.getZ() + v2.getZ() + v3.getZ()) / 3f;
		
		result[3] = ((v2.getY()- v1.getY()) * (v3.getZ() - v1.getZ()) - (v2.getZ() - v1.getZ()) * (v3.getY() - v1.getY()));
		result[4] = ((v2.getZ()- v1.getZ()) * (v3.getX() - v1.getX()) - (v2.getX() - v1.getX()) * (v3.getZ() - v1.getZ()));
		result[5] = ((v2.getX()- v1.getX()) * (v3.getY() - v1.getY()) - (v2.getY() - v1.getY()) * (v3.getX() - v1.getX()));

		double multiplier = -1d / Math.sqrt(result[3] * result[3] + result[4] * result[4] + result[5] * result[5]);

		result[3] *= multiplier;
		result[4] *= multiplier;
		result[5] *= multiplier;
		
		return result;
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
