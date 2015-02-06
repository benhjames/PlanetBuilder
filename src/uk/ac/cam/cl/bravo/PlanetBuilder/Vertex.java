package uk.ac.cam.cl.bravo.PlanetBuilder;

class Vertex {

    // 3 coordiantes
    public double x,y,z;

    //Noise for the height
    private double heightNoise;

    //Noise for the climate
    private double climateNoise;

    //Constructor with no argument- initializes everything to 0
    public Vertex(){}

    //Constructor with the 3 coordinates as arguments
    public Vertex(double x1, double y1, double z1) {
        x = x1;
        y = y1;
        z = z1;
    }

    //Returns the height ie the distance from the origin
    public double getHeight(){
        return Math.sqrt(x*x + y*y + z*z);
    }


}