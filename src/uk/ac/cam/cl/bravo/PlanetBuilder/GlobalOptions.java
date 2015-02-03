package uk.ac.cam.cl.bravo.PlanetBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GlobalOptions implements Serializable {
    private static GlobalOptions go = null;

    private float detailLevel;
    private boolean renderHigh;

    protected GlobalOptions() {
        //Initialise options with defaults
        detailLevel = 50.0f;
        renderHigh = true;
    }

    public static GlobalOptions getInstance() {
        if (go == null) {
            go = new GlobalOptions();
        }
        return go;
    }

    //Getters and setters only below

    public float getDetailLevel() {
        return detailLevel;
    }

    public void setDetailLevel(float detailLevel) {
        if (detailLevel >= 0.0f && detailLevel <= 100.0f) {
            this.detailLevel = detailLevel;
        }
    }

    public boolean isRenderHigh() {
        return renderHigh;
    }

    public void setRenderHigh(boolean renderHigh) {
        this.renderHigh = renderHigh;
    }

    public static void writeToFile(GlobalOptions g, String pathname) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(pathname);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(g);
        objectOut.close();
        fileOut.close();
    }

}
