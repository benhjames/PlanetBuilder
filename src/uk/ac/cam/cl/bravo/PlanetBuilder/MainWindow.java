package uk.ac.cam.cl.bravo.PlanetBuilder;

import javax.imageio.ImageIO;
import javax.media.opengl.*;

import com.hackoeur.jglm.*;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

import java.awt.image.BufferedImage;
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

	private Mat4 planetMatrix = new Mat4(1.0f);
	private int planetVertexCount;
	private int skyboxVertexCount;

	private int modelMatrix;
	private int cameraMatrix;
	private int projectionMatrix;
	private int worldToCameraMatrix;
	private int shaderTexture;
	private int[] vboHandles;

	private Texture skyboxTexture;

	static private final int VERTICES_IDX = 0;
	static private final int NORMALS_IDX = 1;
	static private final int SKYBOX_IDX = 2;

	private Camera camera = new Camera();

	@Override
	public void init(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();

		loadShaders(drawable);

		vboHandles = new int[3];
		gl.glGenBuffers(3, vboHandles, 0);

		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);

		try {
			skyboxTexture = TextureIO.newTexture(GL.GL_TEXTURE_CUBE_MAP);

			String[] suffixes = {"xpos", "xneg", "ypos",
					                    "yneg", "zpos", "zneg"};
			int[] targets = {GL3.GL_TEXTURE_CUBE_MAP_POSITIVE_X,
			                        GL3.GL_TEXTURE_CUBE_MAP_NEGATIVE_X,
					                GL3.GL_TEXTURE_CUBE_MAP_POSITIVE_Y,
					                GL3.GL_TEXTURE_CUBE_MAP_NEGATIVE_Y,
					                GL3.GL_TEXTURE_CUBE_MAP_POSITIVE_Z,
					                GL3.GL_TEXTURE_CUBE_MAP_NEGATIVE_Z};

			for(int i = 0; i < suffixes.length; i++ ) {
				BufferedImage img = ImageIO.read(new File("res/" + suffixes[i] + ".png"));
				skyboxTexture.updateImage(gl, AWTTextureIO.newTextureData(gl.getGLProfile(), img, false), targets[i]);
			}

			skyboxTexture.setTexParameteri(gl, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
			skyboxTexture.setTexParameteri(gl, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
			skyboxTexture.setTexParameteri(gl, GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP_TO_EDGE);
			skyboxTexture.setTexParameteri(gl, GL.GL_TEXTURE_WRAP_T, GL.GL_CLAMP_TO_EDGE);
		} catch (IOException e) {
			System.err.println("Error: Could not create skybox texture.");
			e.printStackTrace();
			System.exit(1);
		}

		World.getInstance().initializeGlobal();
		World.getInstance().finalizeWorld();

		createPlanet(drawable);
		createSkybox(drawable);
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
		modelMatrix = gl.glGetUniformLocation(grassShaderProgram, "uniform_Model");
		cameraMatrix = gl.glGetUniformLocation(grassShaderProgram, "uniform_Camera");


		skyboxVertShader = Shader.createShader(drawable, GL3.GL_VERTEX_SHADER, "res/skybox-shader.vp");
		skyboxFragShader = Shader.createShader(drawable, GL3.GL_FRAGMENT_SHADER, "res/skybox-shader.fp");
		skyboxShaderProgram = gl.glCreateProgram();
		gl.glAttachShader(skyboxShaderProgram, skyboxVertShader);
		gl.glAttachShader(skyboxShaderProgram, skyboxFragShader);
		gl.glBindAttribLocation(skyboxShaderProgram, 0, "aPosition");
		gl.glLinkProgram(skyboxShaderProgram);
		projectionMatrix = gl.glGetUniformLocation(skyboxShaderProgram, "uProjectionMatrix");
		worldToCameraMatrix = gl.glGetUniformLocation(skyboxShaderProgram, "uWorldToCameraMatrix");
		shaderTexture = gl.glGetUniformLocation(skyboxShaderProgram, "uTexture");
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();
		gl.glDetachShader(grassShaderProgram, grassVertShader);
		gl.glDeleteShader(grassVertShader);
		gl.glDetachShader(grassShaderProgram, grassFragShader);
		gl.glDeleteShader(grassFragShader);
		gl.glDeleteProgram(grassShaderProgram);
		gl.glDetachShader(skyboxShaderProgram, skyboxVertShader);
		gl.glDeleteShader(skyboxVertShader);
		gl.glDetachShader(skyboxShaderProgram, skyboxFragShader);
		gl.glDeleteShader(skyboxFragShader);
		gl.glDeleteProgram(skyboxShaderProgram);
	}

	private void createPlanet(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();

		float[] vertices = World.getInstance().getSurfaceVertexArray();
		float[] normals = World.getInstance().getSurfaceVertexArray();

		FloatBuffer vertexBuffer = Buffers.newDirectFloatBuffer(vertices);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[VERTICES_IDX]);

		int numBytes = vertices.length * 4;
		gl.glBufferData(GL.GL_ARRAY_BUFFER, numBytes, vertexBuffer, GL.GL_STATIC_DRAW);

		FloatBuffer normalBuffer = Buffers.newDirectFloatBuffer(normals);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[NORMALS_IDX]);
		gl.glVertexAttribPointer(1, 3, GL3.GL_FLOAT, false, 0, 0);

		numBytes = normals.length * 4;
		gl.glBufferData(GL.GL_ARRAY_BUFFER, numBytes, normalBuffer, GL.GL_STATIC_DRAW);

		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);

		planetVertexCount = vertices.length / 3;
	}

	private void createSkybox(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();

		float[] skyboxCoords = {-1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f,
				                       -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f};
		FloatBuffer skyboxBuffer = Buffers.newDirectFloatBuffer(skyboxCoords);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[SKYBOX_IDX]);
		int numBytes = skyboxCoords.length * 4;
		gl.glBufferData(GL.GL_ARRAY_BUFFER, numBytes, skyboxBuffer, GL.GL_STATIC_DRAW);

		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);

		skyboxVertexCount = skyboxCoords.length / 2;
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();

		gl.glClearColor(0, 0, 0, 0.5f);
		gl.glClear(GL3.GL_STENCIL_BUFFER_BIT |
					GL3.GL_COLOR_BUFFER_BIT |
					GL3.GL_DEPTH_BUFFER_BIT);

		gl.glUseProgram(skyboxShaderProgram);

		gl.glUniformMatrix4fv(projectionMatrix, 1, false, camera.projection().getBuffer().array(), 0);
		gl.glUniformMatrix4fv(worldToCameraMatrix, 1, false, camera.view().getBuffer().array(), 0);

		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[SKYBOX_IDX]);
		gl.glDisable(GL.GL_DEPTH_TEST);

		gl.glActiveTexture(GL.GL_TEXTURE0);
		skyboxTexture.bind(gl);
		skyboxTexture.enable(gl);
		gl.glUniform1i(shaderTexture, 0);

		gl.glVertexAttribPointer(0, 2, GL3.GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(0);
		gl.glDrawArrays(GL3.GL_TRIANGLES, 0, skyboxVertexCount);
		gl.glDisableVertexAttribArray(0);

		skyboxTexture.disable(gl);

		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);

		gl.glEnable(GL.GL_DEPTH_TEST);

		// END SKYBOX DRAWING, BEGIN DRAWING WORLD

		gl.glUseProgram(grassShaderProgram);

		double currTime = System.currentTimeMillis();

        if(GlobalOptions.getInstance().isAutoPan()) {
            theta += (currTime - prevTime) * 0.01f;
            if (theta >= 360 || theta < 0) {
                theta = theta % 360;
            }
        }

        prevTime = currTime;

        camera.setPosition(new Vec3((float)Math.sin(Math.toRadians(theta)) * 3.0f,
				                           0.0f,
				                           (float)Math.cos(Math.toRadians(theta)) * 3.0f));
		camera.lookAt(new Vec3(0.0f, 0.0f, 0.0f));

		gl.glUniformMatrix4fv(cameraMatrix, 1, false, camera.matrix().getBuffer().array(), 0);
		gl.glUniformMatrix4fv(modelMatrix, 1, false, planetMatrix.getBuffer().array(), 0);

		gl.glEnableVertexAttribArray(0);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[VERTICES_IDX]);
		gl.glVertexAttribPointer(0, 3, GL3.GL_FLOAT, false, 0, 0);

		gl.glEnableVertexAttribArray(1);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[NORMALS_IDX]);
		gl.glVertexAttribPointer(1, 3, GL3.GL_FLOAT, false, 0, 0);

		gl.glDrawArrays(GL3.GL_TRIANGLES, 0, planetVertexCount);

		gl.glDisableVertexAttribArray(0);
		gl.glDisableVertexAttribArray(1);

		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);

	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

	}
}
