package uk.ac.cam.cl.bravo.PlanetBuilder;

/**
 * Created by Alfie on 24/02/15.
 */
public class VegetationModel {
    private static float[] vertexArray = null;

    public static float[] getVertexArray() {
        if (vertexArray == null) {
            vertexArray = new float[] {
                    1.5f,0f,0.5f,
                    1.0f,0.5f,0f,
                    1.5f,0f,-0.5f,
                    1.5f,0f,-0.5f,
                    1.0f,0.5f,0f,
                    0.5f,0f,-0.5f,
                    0.5f,0f,-0.5f,
                    1.0f,0.5f,0f,
                    0.5f,0f,0.5f,
                    0.5f,0f,0.5f,
                    1.0f,0.5f,0f,
                    1.5f,0f,0.5f,
                    1.5f,0f,0.5f,
                    1.0f,0f,0f,
                    1.5f,0f,-0.5f,
                    1.5f,0f,-0.5f,
                    1.0f,0f,0f,
                    0.5f,0f,-0.5f,
                    0.5f,0f,-0.5f,
                    1.0f,0f,0f,
                    0.5f,0f,0.5f,
                    0.5f,0f,0.5f,
                    1.0f,0f,0f,
                    1.5f,0f,0.5f,//
                    -1.5f,0f,0.5f,
                    -1.0f,0.75f,0f,
                    -1.5f,0f,-0.5f,
                    -1.5f,0f,-0.5f,
                    -1.0f,0.75f,0f,
                    -0.5f,0f,-0.5f,
                    -0.5f,0f,-0.5f,
                    -1.0f,0.75f,0f,
                    -0.5f,0f,0.5f,
                    -0.5f,0f,0.5f,
                    -1.0f,0.75f,0f,
                    -1.5f,0f,0.5f,
                    -1.5f,0f,0.5f,
                    -1.0f,0f,0f,
                    -1.5f,0f,-0.5f,
                    -1.5f,0f,-0.5f,
                    -1.0f,0f,0f,
                    -0.5f,0f,-0.5f,
                    -0.5f,0f,-0.5f,
                    -1.0f,0f,0f,
                    -0.5f,0f,0.5f,
                    -0.5f,0f,0.5f,
                    -1.0f,0f,0f,
                    -1.5f,0f,0.5f,//
                    0.5f,0f,1.5f,
                    0f,1.0f,1.0f,
                    -0.5f,0f,1.5f,
                    -0.5f,0f,1.5f,
                    0f,1.0f,1.0f,
                    -0.5f,0f,0.5f,
                    -0.5f,0f,0.5f,
                    0f,1.0f,1.0f,
                    0.5f,0f,0.5f,
                    0.5f,0f,0.5f,
                    0f,1.0f,1.0f,
                    0.5f,0f,1.5f,
                    0.5f,0f,1.5f,
                    0f,0f,1.0f,
                    -0.5f,0f,1.5f,
                    -0.5f,0f,1.5f,
                    0f,0f,1.0f,
                    -0.5f,0f,0.5f,
                    -0.5f,0f,0.5f,
                    0f,0f,1.0f,
                    0.5f,0f,0.5f,
                    0.5f,0f,0.5f,
                    0f,0f,1.0f,
                    0.5f,0f,1.5f
            };
        }
        return vertexArray;
    }
}
