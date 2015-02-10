package uk.ac.cam.cl.bravo.PlanetBuilder;

import javax.media.opengl.GL;
import javax.media.opengl.GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import com.jogamp.common.nio.Buffers;
import java.nio.FloatBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class MainWindow implements GLEventListener {

	private double prevTime, theta = 0;

	private String vertexShaderString;
	private String fragmentShaderString;

	private int vertShader;
	private int fragShader;
	private int shaderProgram;
	private int modelViewProjectionMatrix;
	private int[] vboHandles;

	static private final int VERTICES_IDX = 0;
	static private final int NORMALS_IDX = 1;

	@Override
	public void init(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();
		vertShader = gl.glCreateShader(GL3.GL_VERTEX_SHADER);
		fragShader = gl.glCreateShader(GL3.GL_FRAGMENT_SHADER);

		try {
			byte[] encoded = Files.readAllBytes(Paths.get("res/vertexShader.shdr"));
			vertexShaderString = new String(encoded, StandardCharsets.UTF_8);
			encoded = Files.readAllBytes(Paths.get("res/fragmentShader.shdr"));
			fragmentShaderString = new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.err.println("Error: vertex or fragment shader not found.");
			e.printStackTrace();
			System.exit(1);
		}

		// Make the shader strings compatible with OpenGL 3 core if needed
		if(gl.isGL3core()) {
			System.out.println("GL3 core detected: explicit add #version 150 to shaders");
			vertexShaderString = "#version 150\n" + vertexShaderString;
			fragmentShaderString = "#version 150\n" + fragmentShaderString;
		}

		//Compile the vertexShader String into a program.
		String[] vlines = new String[] { vertexShaderString };
		int[] vlengths = new int[] { vlines[0].length() };
		gl.glShaderSource(vertShader, vlines.length, vlines, vlengths, 0);
		gl.glCompileShader(vertShader);

		//Check compile status.
		int[] compiled = new int[1];
		gl.glGetShaderiv(vertShader, GL3.GL_COMPILE_STATUS, compiled,0);
		if(compiled[0] == 0) {
			int[] logLength = new int[1];
			gl.glGetShaderiv(vertShader, GL3.GL_INFO_LOG_LENGTH, logLength, 0);

			byte[] log = new byte[logLength[0]];
			gl.glGetShaderInfoLog(vertShader, logLength[0], null, 0, log, 0);

			System.err.println("Error compiling the vertex shader: " + new String(log));
			System.exit(1);
		}

		//Compile the fragmentShader String into a program.
		String[] flines = new String[] { fragmentShaderString };
		int[] flengths = new int[] { flines[0].length() };
		gl.glShaderSource(fragShader, flines.length, flines, flengths, 0);
		gl.glCompileShader(fragShader);

		//Check compile status.
		gl.glGetShaderiv(fragShader, GL3.GL_COMPILE_STATUS, compiled,0);
		if(compiled[0] == 0) {
			int[] logLength = new int[1];
			gl.glGetShaderiv(fragShader, GL3.GL_INFO_LOG_LENGTH, logLength, 0);

			byte[] log = new byte[logLength[0]];
			gl.glGetShaderInfoLog(fragShader, logLength[0], null, 0, log, 0);

			System.err.println("Error compiling the fragment shader: " + new String(log));
			System.exit(1);
		}

		//Each shaderProgram must have one vertex shader and one fragment shader.
		shaderProgram = gl.glCreateProgram();
		gl.glAttachShader(shaderProgram, vertShader);
		gl.glAttachShader(shaderProgram, fragShader);

		//Associate attribute ids with the attribute names inside the vertex shader.
		gl.glBindAttribLocation(shaderProgram, 0, "attribute_Position");
		gl.glBindAttribLocation(shaderProgram, 1, "attribute_Normal");

		gl.glLinkProgram(shaderProgram);

		//Get a id number to the uniform_Projection matrix so that we can update it.
		modelViewProjectionMatrix = gl.glGetUniformLocation(shaderProgram, "uniform_Projection");

		vboHandles = new int[2];
		gl.glGenBuffers(2, vboHandles, 0);

		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();
		gl.glDetachShader(shaderProgram, vertShader);
		gl.glDeleteShader(vertShader);
		gl.glDetachShader(shaderProgram, fragShader);
		gl.glDeleteShader(fragShader);
		gl.glDeleteProgram(shaderProgram);
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();

		gl.glClearColor(0, 0, 0, 0.5f);
		gl.glClear(GL3.GL_STENCIL_BUFFER_BIT |
					GL3.GL_COLOR_BUFFER_BIT |
					GL3.GL_DEPTH_BUFFER_BIT);

		gl.glUseProgram(shaderProgram);

		float[] modelViewProjection;
		float[] identityMatrix = {
			1.0f, 0.0f, 0.0f, 0.0f,
			0.0f, 1.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 1.0f, 0.0f,
			0.0f, 0.0f, 0.0f, 1.0f,
		};

		double currTime = System.currentTimeMillis();
		theta += (currTime - prevTime) * 0.01f;
		prevTime = currTime;
		if(theta >= 360 || theta < 0) {
			theta = theta % 360;
		}

		modelViewProjection = translate(identityMatrix, 0.0f, 0.0f, 0.0f);
		modelViewProjection = rotate(modelViewProjection, (float)theta, 0.0f, 1.0f, 0.0f);

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
