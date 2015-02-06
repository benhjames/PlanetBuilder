package uk.ac.cam.cl.bravo.PlanetBuilder;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class MainWindow implements GLEventListener {
    @Override
    public void init(GLAutoDrawable drawable) {
        //Nothing yet
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        //Nothing yet
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        Icosahedron icosahedron = new Icosahedron();
        float[] vertices = icosahedron.getVertices();
        int vertexCount = icosahedron.getVertexCount();

        //Move camera
        gl.glTranslatef(0.0f, 0.0f, 0.0f);

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glBegin(GL.GL_TRIANGLES);

        for(int i = 0; i < vertexCount; i++) {
            gl.glColor3f(0, (float)i/vertexCount, (float)(i%3)/3);
            gl.glVertex3fv(vertices, i * 3);
        }

        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }
}
