package uk.ac.cam.cl.bravo.PlanetBuilder;

/**
 * Created by Alfie on 24/02/15.
 */
public class StructureModel extends Model {
    public StructureModel() {
        vertexArray = new float[]{
                1.0f, 0.0f, 1.0f,
                1.0f, 0.0f, -1.0f,
                -1.0f, 0.0f, 1.0f,
                -1.0f, 0.0f, -1.0f,
                1.0f, 2.0f, 1.0f,
                1.0f, 2.0f, -1.0f,
                -1.0f, 2.0f, 1.0f,
                -1.0f, 2.0f, -1.0f
        };
    }
}
