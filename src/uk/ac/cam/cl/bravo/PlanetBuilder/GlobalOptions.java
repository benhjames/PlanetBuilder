package uk.ac.cam.cl.bravo.PlanetBuilder;

public class GlobalOptions {
    private static GlobalOptions go = null;

    private float detailLevel;
    private boolean renderHigh;

    protected GlobalOptions() {
        //Initialise options with defaults
        detailLevel = 50.0f;
        renderHigh = true;
    }

    public GlobalOptions getInstance() {
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

}
