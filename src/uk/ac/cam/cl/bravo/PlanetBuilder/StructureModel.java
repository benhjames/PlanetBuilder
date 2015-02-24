package uk.ac.cam.cl.bravo.PlanetBuilder;

/**
 * Created by Alfie on 24/02/15.
 */
public class StructureModel {
    private static float[] vertexArray = null;

    public static float[] getVertexArray() {
        if (vertexArray == null) {
            float x = 1.0f;
            float y = 1.0f;
            float z = 1.0f;

            vertexArray = new float[] {
                    x,y-1,z,
                    x,y+1,z,
                    x,y-1,-z,
                    x,y+1,z,
                    x,y+1,-z,
                    x,y-1,-z,
                    x,y+1,z,
                    -x,y+1,z,
                    x,y+1,-z,
                    -x,y+1,z,
                    -x,y+1,-z,
                    x,y+1,-z,
                    x,y+1,-z,
                    -x,y+1,-z,
                    -x,y-1,-z,
                    -x,y-1,-z,
                    x,y-1,-z,
                    x,y+1,-z,
                    -x,y-1,-z,
                    -x,y+1,-z,
                    -x,y+1,z,
                    -x,y-1,-z,
                    -x,y+1,z,
                    -x,y-1,z,
                    -x,y+1,z,
                    x,y+1,z,
                    x,y-1,z,
                    x,y-1,z,
                    -x,y-1,z,
                    -x,y+1,z,
                    x,y-1,z,
                    x,y-1,-z,
                    -x,y-1,z,
                    x,y-1,-z,
                    -x,y-1,-z,
                    -x,y-1,z
            };

        }
        return vertexArray;
    }
}
