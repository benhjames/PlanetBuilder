package uk.ac.cam.cl.bravo.PlanetBuilder;

public class GlobalOptions {
    private static GlobalOptions go = null;

    protected GlobalOptions() {
        //Initialise options with defaults
    }

    public GlobalOptions getInstance() {
        if (go == null) {
            go = new GlobalOptions();
        }
        return go;
    }
}
