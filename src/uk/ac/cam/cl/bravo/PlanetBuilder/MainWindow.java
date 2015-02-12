package uk.ac.cam.cl.bravo.PlanetBuilder;

import javax.media.opengl.*;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import java.io.File;
import java.nio.FloatBuffer;
import java.io.IOException;

public class MainWindow implements GLEventListener {

	private double prevTime, theta = 0;

	private int grassVertShader;
	private int grassFragShader;
	private int grassShaderProgram;

	private int skyboxVertShader;
	private int skyboxFragShader;
	private int skyboxShaderProgram;

	private int modelViewProjectionMatrix;
	private int cameraMatrix;
	private int[] vboHandles;

	private Texture skyboxTexture;

	static private final int VERTICES_IDX = 0;
	static private final int NORMALS_IDX = 1;

	private Camera camera = new Camera();

	@Override
	public void init(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();

		loadShaders(drawable);

		vboHandles = new int[2];
		gl.glGenBuffers(2, vboHandles, 0);

		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);

		try {
			skyboxTexture = TextureIO.newTexture(new File("res/skybox.jpg"), false);
			skyboxTexture.setTexParameteri(gl, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR );
			skyboxTexture.setTexParameteri(gl, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR );
			skyboxTexture.setTexParameteri(gl, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
			skyboxTexture.setTexParameteri(gl, GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);
		} catch (IOException e) {
			System.err.println("Error: Could not create skybox texture.");
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void loadShaders(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();

		grassVertShader = Shader.createShader(drawable, GL3.GL_VERTEX_SHADER, "res/grass-shader.vp");
		grassFragShader = Shader.createShader(drawable, GL3.GL_FRAGMENT_SHADER, "res/grass-shader.fp");

		//Each shaderProgram must have one vertex shader and one fragment shader.
		grassShaderProgram = gl.glCreateProgram();
		gl.glAttachShader(grassShaderProgram, grassVertShader);
		gl.glAttachShader(grassShaderProgram, grassFragShader);

		//Associate attribute ids with the attribute names inside the vertex shader.
		gl.glBindAttribLocation(grassShaderProgram, 0, "attribute_Position");
		gl.glBindAttribLocation(grassShaderProgram, 1, "attribute_Normal");

		gl.glLinkProgram(grassShaderProgram);

		//Get a id number to the uniform_Projection matrix so that we can update it.
		modelViewProjectionMatrix = gl.glGetUniformLocation(grassShaderProgram, "uniform_Projection");
		cameraMatrix = gl.glGetUniformLocation(grassShaderProgram, "uniform_Camera");


		skyboxVertShader = Shader.createShader(drawable, GL3.GL_VERTEX_SHADER, "res/skybox-shader.vp");
		skyboxFragShader = Shader.createShader(drawable, GL3.GL_FRAGMENT_SHADER, "res/skybox-shader.fp");
		skyboxShaderProgram = gl.glCreateProgram();
		gl.glAttachShader(skyboxShaderProgram, skyboxVertShader);
		gl.glAttachShader(skyboxShaderProgram, skyboxFragShader);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();
		gl.glDetachShader(grassShaderProgram, grassVertShader);
		gl.glDeleteShader(grassVertShader);
		gl.glDetachShader(grassShaderProgram, grassFragShader);
		gl.glDeleteShader(grassFragShader);
		gl.glDeleteProgram(grassShaderProgram);
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();

		gl.glClearColor(0, 0, 0, 0.5f);
		gl.glClear(GL3.GL_STENCIL_BUFFER_BIT |
					GL3.GL_COLOR_BUFFER_BIT |
					GL3.GL_DEPTH_BUFFER_BIT);

		gl.glUseProgram(grassShaderProgram);

		float[] modelViewProjection;
		float[] identityMatrix = {
			1.0f, 0.0f, 0.0f, 0.0f,
			0.0f, 1.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 1.0f, 0.0f,
			0.0f, 0.0f, 0.0f, 1.0f,
		};

        double currTime = System.currentTimeMillis();

        if(GlobalOptions.getInstance().isAutoPan()) {
            theta += (currTime - prevTime) * 0.01f;
            if (theta >= 360 || theta < 0) {
                theta = theta % 360;
            }
        }

        prevTime = currTime;

        modelViewProjection = translate(identityMatrix, 0.0f, 0.0f, 0.0f);
		modelViewProjection = rotate(modelViewProjection, (float)theta, 0.0f, 1.0f, 0.0f);

		gl.glUniformMatrix4fv(cameraMatrix, 1, false, camera.matrix().getBuffer().array(), 0);
		gl.glUniformMatrix4fv(modelViewProjectionMatrix, 1, false, modelViewProjection, 0);

		Icosahedron icosahedron = new Icosahedron();
		float[] vertices = icosahedron.getVertices();
		float[] normals = icosahedron.getVertexNormals();

		FloatBuffer vertexBuffer = Buffers.newDirectFloatBuffer(vertices);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[VERTICES_IDX]);

		int numBytes = vertices.length * 4;
		gl.glBufferData(GL.GL_ARRAY_BUFFER, numBytes, vertexBuffer, GL.GL_STATIC_DRAW);
		vertexBuffer = null;

		FloatBuffer normalBuffer = Buffers.newDirectFloatBuffer(normals);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[NORMALS_IDX]);

		numBytes = normals.length * 4;
		gl.glBufferData(GL.GL_ARRAY_BUFFER, numBytes, normalBuffer, GL.GL_STATIC_DRAW);
		normalBuffer = null;

		gl.glVertexAttribPointer(0, 3, GL3.GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(0);
		gl.glVertexAttribPointer(1, 3, GL3.GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(1);

		gl.glDrawArrays(GL3.GL_TRIANGLES, 0, vertices.length / 3);

		gl.glDisableVertexAttribArray(0);
		gl.glDisableVertexAttribArray(1);

		float[] skyboxCoords = {-1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f,
			-1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f};
		FloatBuffer skyboxBuffer = Buffers.newDirectFloatBuffer(skyboxCoords);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[VERTICES_IDX]);
		numBytes = skyboxCoords.length * 4;
		gl.glBufferData(GL.GL_ARRAY_BUFFER, numBytes, skyboxBuffer, GL.GL_STATIC_DRAW);

		skyboxTexture.enable(gl);
		skyboxTexture.bind(gl);

		gl.glVertexAttribPointer(0, 2, GL3.GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(0);
		//gl.glDrawArrays(GL.TRIANGLES);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

	}

	// Helper functions for matrix manipulation.

	private float[] translate(float[] m, float x, float y, float z) {
		float[] t = { 1.0f, 0.0f, 0.0f, 0.0f,
						0.0f, 1.0f, 0.0f, 0.0f,
						0.0f, 0.0f, 1.0f, 0.0f,
						x, y, z, 1.0f };
		return multiply(m, t);
	}

	private float[] rotate(float[] m, float a, float x, float y, float z){
		float s, c;
		s = (float)Math.sin(Math.toRadians(a));
		c = (float)Math.cos(Math.toRadians(a));
		float[] r = {
			x * x * (1.0f - c) + c,     y * x * (1.0f - c) + z * s, x * z * (1.0f - c) - y * s, 0.0f,
			x * y * (1.0f - c) - z * s, y * y * (1.0f - c) + c,     y * z * (1.0f - c) + x * s, 0.0f,
			x * z * (1.0f - c) + y * s, y * z * (1.0f - c) - x * s, z * z * (1.0f - c) + c,     0.0f,
			0.0f, 0.0f, 0.0f, 1.0f };
		return multiply(m, r);
	}

	private void glMultMatrixf(FloatBuffer a, FloatBuffer b, FloatBuffer d) {
		final int aP = a.position();
		final int bP = b.position();
		final int dP = d.position();
		for (int i = 0; i < 4; i++) {
			final float ai0=a.get(aP+i+0*4),  ai1=a.get(aP+i+1*4),  ai2=a.get(aP+i+2*4),  ai3=a.get(aP+i+3*4);
			d.put(dP+i+0*4 , ai0 * b.get(bP+0+0*4) + ai1 * b.get(bP+1+0*4) + ai2 * b.get(bP+2+0*4) + ai3 * b.get(bP+3+0*4) );
			d.put(dP+i+1*4 , ai0 * b.get(bP+0+1*4) + ai1 * b.get(bP+1+1*4) + ai2 * b.get(bP+2+1*4) + ai3 * b.get(bP+3+1*4) );
			d.put(dP+i+2*4 , ai0 * b.get(bP+0+2*4) + ai1 * b.get(bP+1+2*4) + ai2 * b.get(bP+2+2*4) + ai3 * b.get(bP+3+2*4) );
			d.put(dP+i+3*4 , ai0 * b.get(bP+0+3*4) + ai1 * b.get(bP+1+3*4) + ai2 * b.get(bP+2+3*4) + ai3 * b.get(bP+3+3*4) );
		}
	}

	private float[] multiply(float[] a,float[] b){
		float[] tmp = new float[16];
		glMultMatrixf(FloatBuffer.wrap(a),FloatBuffer.wrap(b),FloatBuffer.wrap(tmp));
		return tmp;
	}

}
