package uk.ac.cam.cl.bravo.PlanetBuilder;

class Triangle {

    //3 Verticies
    public Vertex v1, v2, v3;

    //Filling
    private Filling f;

    // Returns the Filling
    public Filling getFilling() {
        return f;
    }

    //Constructor with the 3 Verticies as argumants
    public Triangle(Vertex V1, Vertex V2, Vertex V3){
        v1 = V1;
        v2 = V2;
        v3 = V3;
    }
}