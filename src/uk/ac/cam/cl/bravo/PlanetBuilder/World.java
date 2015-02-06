package uk.ac.cam.cl.bravo.PlanetBuilder;

class World{

    //Initialize triangle mesh and noise values for the triangles and the verticies
    public void initializeGlobal(GlobalOptions GO){
        //TODO
    }

    //Adjust the verticies based on the noise values and assign fillings
    public void finalizeWorld(WorldOptions WO) {
        //TODO
    }

    private Triangle[] SurfaceTriangles;
    private Triangle[] CloudTriangles;
    private Triangle[] SeaTriangles;

    //get the surface triangle mesh
    public Triangle[] getSurface(){
        return SurfaceTriangles;
    }

    //get the cloud triangle mesh
    public Triangle[] getCloud(){
        return CloudTriangles;
    }

    //get the sea triangle mesh
    public Triangle[] getSea(){
        return SeaTriangles;
    }
}