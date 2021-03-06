package uk.ac.cam.cl.bravo.PlanetBuilder;

import java.awt.Color;
import java.io.*;
import java.util.Random;

public class WorldOptions implements Serializable {

    private static WorldOptions wo;

    private int seed;

    private float terrainFactor;
    private float vegetationFactor;
    private float desertFactor;
    private float iceFactor;
    private float waterFactor;
    private float settlementLevel;

    private boolean planetRings;
    private boolean inhabitants;

    private Color groundStart;
    private Color groundEnd;
    private Color waterStart;
    private Color waterEnd;

    private int iterations;
    private double persistence;

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
        //World.getInstance().initializeGlobal();
        World.getInstance().finalizeWorld();
        MainWindow.updatePlanet();
    }

    public double getPersistence() {
        return persistence;
    }

    public void setPersistence(double persistence) {
        if(persistence >= 0.0 && persistence <= 1.0)
            this.persistence = persistence;
        //World.getInstance().initializeGlobal();
        World.getInstance().finalizeWorld();
        MainWindow.updatePlanet();
    }

    protected WorldOptions() {
        //Randomly generate all settings.
        Random r = new Random();
        terrainFactor = r.nextFloat() * 50.0f + 50.0f;
        vegetationFactor = r.nextFloat() * 30.0f + 20.0f;
        desertFactor = r.nextFloat() * 30.0f + 20.0f;
        iceFactor = r.nextFloat() * 30.0f + 20.0f;
        waterFactor = r.nextFloat() * 50.0f + 20.0f;
        settlementLevel = r.nextFloat() * 20.0f + 20.0f;
        persistence = r.nextFloat() * 0.4f + 0.3f;
        iterations = r.nextInt(5) + 5;

        planetRings = r.nextBoolean();
        inhabitants = true;

        groundStart = new Color(r.nextInt());
        groundEnd = new Color(r.nextInt());
        waterStart = new Color(r.nextInt());
        waterEnd = new Color(r.nextInt());

        this.newSeed();
    }

    public static void replaceInstance() {
        wo = new WorldOptions();
        World.getInstance().initializeGlobal();
        World.getInstance().finalizeWorld();
    }

    public static WorldOptions getInstance() {
        if (wo == null) {
            wo = new WorldOptions();
        }
        return wo;
    }


    public static void readFromFile(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        wo = (WorldOptions) objIn.readObject();
    }

    public float getTerrainFactor() {
        return terrainFactor;
    }

    public void setTerrainFactor(float terrainFactor) {
        if (terrainFactor >= 0.0f && terrainFactor <= 100.0f) {
            this.terrainFactor = terrainFactor;
        }
        World.getInstance().finalizeWorld();
	    MainWindow.updatePlanet();
    }

    public float getVegetationFactor() {
        return vegetationFactor;
    }

    public void setVegetationFactor(float vegetationFactor) {
        if (vegetationFactor >= 0.0f && vegetationFactor <= 100.0f) {
            this.vegetationFactor = vegetationFactor;

        }
        World.getInstance().finalizeWorld();
	    MainWindow.updatePlanet();
    }

    public float getDesertFactor() {
        return desertFactor;
    }

    public void setDesertFactor(float desertFactor) {
        if (desertFactor >= 0.0f && desertFactor <= 100.0f) {
            this.desertFactor = desertFactor;
        }
        World.getInstance().finalizeWorld();
	    MainWindow.updatePlanet();
    }

    public float getIceFactor() {
        return iceFactor;
    }

    public void setIceFactor(float iceFactor) {
        if (iceFactor >= 0.0f && iceFactor <= 100.0f) {
            this.iceFactor = iceFactor;
        }
        World.getInstance().finalizeWorld();
	    MainWindow.updatePlanet();
    }

    public float getWaterFactor() {
        return waterFactor;
    }

    public void setWaterFactor(float waterFactor) {
        if (waterFactor >= 0.0f && waterFactor <= 100.0f) {
            this.waterFactor = waterFactor;
        }
        World.getInstance().finalizeWorld();
	    MainWindow.updatePlanet();
    }

    public float getSettlementLevel() {
        return settlementLevel;
    }

    public void setSettlementLevel(float settlementLevel) {
        if (settlementLevel >= 0.0f && settlementLevel <= 100.0f) {
            this.settlementLevel = settlementLevel;
        }
        World.getInstance().finalizeWorld();
	    MainWindow.updatePlanet();
    }

    public boolean isPlanetRings() {
        return planetRings;
    }

    public void setPlanetRings(boolean planetRings) {
        this.planetRings = planetRings;
        World.getInstance().finalizeWorld();
	    MainWindow.updatePlanet();
    }

    public boolean isInhabitants() {
        return inhabitants;
    }

    public void setInhabitants(boolean inhabitants) {
        this.inhabitants = inhabitants;
        World.getInstance().finalizeWorld();
	    MainWindow.updatePlanet();
    }

    public Color getGroundStart() {
        return groundStart;
    }

    public void setGroundStart(Color groundStart) {
        this.groundStart = groundStart;
        World.getInstance().finalizeWorld();
	    MainWindow.updatePlanet();
    }

    public Color getGroundEnd() {
        return groundEnd;
    }

    public void setGroundEnd(Color groundEnd) {
        this.groundEnd = groundEnd;
	    World.getInstance().finalizeWorld();
	    MainWindow.updatePlanet();
    }

    public Color getWaterStart() {
        return waterStart;
    }

    public void setWaterStart(Color waterStart) {
        this.waterStart = waterStart;
        World.getInstance().finalizeWorld();
	    MainWindow.updatePlanet();
    }

    public Color getWaterEnd() {
        return waterEnd;
    }

    public void setWaterEnd(Color waterEnd) {
        this.waterEnd = waterEnd;
        World.getInstance().finalizeWorld();
	    MainWindow.updatePlanet();
    }

    public int getSeed() {
        return seed;
    }

    public void newSeed() {
        Random r = new Random();
        this.seed = r.nextInt();
    }

    public static void writeToFile(WorldOptions w, String pathname) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(pathname);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(w);
        objectOut.close();
        fileOut.close();
    }

    //Equals and hash code auto generated by IDE

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorldOptions that = (WorldOptions) o;

        if (Float.compare(that.desertFactor, desertFactor) != 0) return false;
        if (Float.compare(that.iceFactor, iceFactor) != 0) return false;
        if (inhabitants != that.inhabitants) return false;
        if (planetRings != that.planetRings) return false;
        if (seed != that.seed) return false;
        if (Float.compare(that.settlementLevel, settlementLevel) != 0) return false;
        if (Float.compare(that.terrainFactor, terrainFactor) != 0) return false;
        if (Float.compare(that.vegetationFactor, vegetationFactor) != 0) return false;
        if (Float.compare(that.waterFactor, waterFactor) != 0) return false;
        if (!groundEnd.equals(that.groundEnd)) return false;
        if (!groundStart.equals(that.groundStart)) return false;
        if (!waterEnd.equals(that.waterEnd)) return false;
        if (!waterStart.equals(that.waterStart)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (seed ^ (seed >>> 32));
        result = 31 * result + (terrainFactor != +0.0f ? Float.floatToIntBits(terrainFactor) : 0);
        result = 31 * result + (vegetationFactor != +0.0f ? Float.floatToIntBits(vegetationFactor) : 0);
        result = 31 * result + (desertFactor != +0.0f ? Float.floatToIntBits(desertFactor) : 0);
        result = 31 * result + (iceFactor != +0.0f ? Float.floatToIntBits(iceFactor) : 0);
        result = 31 * result + (waterFactor != +0.0f ? Float.floatToIntBits(waterFactor) : 0);
        result = 31 * result + (settlementLevel != +0.0f ? Float.floatToIntBits(settlementLevel) : 0);
        result = 31 * result + (planetRings ? 1 : 0);
        result = 31 * result + (inhabitants ? 1 : 0);
        result = 31 * result + groundStart.hashCode();
        result = 31 * result + groundEnd.hashCode();
        result = 31 * result + waterStart.hashCode();
        result = 31 * result + waterEnd.hashCode();
        return result;
    }
}
