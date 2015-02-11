package uk.ac.cam.cl.bravo.PlanetBuilder;


public class TerrainType {
    static String terrain;

    public static String setTerrain(double colour){


        if(colour>0 && colour<=6.4){
            terrain = "water";
        }
        else if(colour>7.0 && colour<=12.0){
            terrain = "land";
        }
        else if(colour>12.8 && colour<=19.2){
            terrain = "mountain";
        }
        else{
            terrain = "settlement";
        }

        return terrain;

    }
}
