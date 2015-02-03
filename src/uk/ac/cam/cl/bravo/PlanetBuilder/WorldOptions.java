package uk.ac.cam.cl.bravo.PlanetBuilder;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

public class WorldOptions implements Serializable {
    private long seed;

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

    public float getTerrainFactor() {
        return terrainFactor;
    }

    public void setTerrainFactor(float terrainFactor) {
        if (terrainFactor >= 0.0f && terrainFactor <= 100.0f) {
            this.terrainFactor = terrainFactor;
        }
    }

    public float getVegetationFactor() {
        return vegetationFactor;
    }

    public void setVegetationFactor(float vegetationFactor) {
        if (vegetationFactor >= 0.0f && vegetationFactor <= 100.0f) {
            this.vegetationFactor = vegetationFactor;
        }
    }

    public float getDesertFactor() {
        return desertFactor;
    }

    public void setDesertFactor(float desertFactor) {
        if (desertFactor >= 0.0f && desertFactor <= 100.0f) {
            this.desertFactor = desertFactor;
        }
    }

    public float getIceFactor() {
        return iceFactor;
    }

    public void setIceFactor(float iceFactor) {
        if (iceFactor >= 0.0f && iceFactor <= 100.0f) {
            this.iceFactor = iceFactor;
        }
    }

    public float getWaterFactor() {
        return waterFactor;
    }

    public void setWaterFactor(float waterFactor) {
        if (waterFactor >= 0.0f && waterFactor <= 100.0f) {
            this.waterFactor = waterFactor;
        }
    }

    public float getSettlementLevel() {
        return settlementLevel;
    }

    public void setSettlementLevel(float settlementLevel) {
        if (settlementLevel >= 0.0f && settlementLevel <= 100.0f) {
            this.settlementLevel = settlementLevel;
        }
    }

    public boolean isPlanetRings() {
        return planetRings;
    }

    public void setPlanetRings(boolean planetRings) {
        this.planetRings = planetRings;
    }

    public boolean isInhabitants() {
        return inhabitants;
    }

    public void setInhabitants(boolean inhabitants) {
        this.inhabitants = inhabitants;
    }

    public Color getGroundStart() {
        return groundStart;
    }

    public void setGroundStart(Color groundStart) {
        this.groundStart = groundStart;
    }

    public Color getGroundEnd() {
        return groundEnd;
    }

    public void setGroundEnd(Color groundEnd) {
        this.groundEnd = groundEnd;
    }

    public Color getWaterStart() {
        return waterStart;
    }

    public void setWaterStart(Color waterStart) {
        this.waterStart = waterStart;
    }

    public Color getWaterEnd() {
        return waterEnd;
    }

    public void setWaterEnd(Color waterEnd) {
        this.waterEnd = waterEnd;
    }

    public long getSeed() {
        return seed;
    }

    public void newSeed() {
        Random r = new Random();
        this.seed = r.nextLong();
    }

    public static void writeToFile(WorldOptions w, String pathname) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(pathname);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(w);
        objectOut.close();
        fileOut.close();
    }
}
