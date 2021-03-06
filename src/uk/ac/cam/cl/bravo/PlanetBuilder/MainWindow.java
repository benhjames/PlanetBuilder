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
	private float time = 0;

	private ShaderProgram grassShaderProgram;
	private ShaderProgram skyboxShaderProgram;
	private ShaderProgram ringShaderProgram;
	private ShaderProgram waterShaderProgram;

	private Mat4 planetMatrix = new Mat4(1.0f);
	private int planetVertexCount;
	private int waterVertexCount;
    private int cloudVertexCount;
	private int skyboxVertexCount;
	private int ringVertexCount;
    private int modelsVertexCount;

	private int[] vboHandles;

	private Texture skyboxTexture;

	private static boolean planetNeedsUpdate;

	static private final int VERTICES_IDX = 0;
	static private final int COLORS_IDX = 1;
	static private final int SKYBOX_IDX = 2;
	static private final int WATER_VERTICES_IDX = 3;
	static private final int WATER_COLORS_IDX = 4;
	static private final int RING_IDX = 5;
    static private final int CLOUD_VERTICES_IDX = 6;
    static private final int CLOUD_COLORS_IDX = 7;
    static private final int MODELS_VERTICES_IDX = 8;
    static private final int MODELS_COLORS_IDX = 9;

	private Camera camera = new Camera();

    public Camera getCamera() { return camera; }

	@Override
	public void init(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();

		loadShaders(drawable);

		vboHandles = new int[10];
		gl.glGenBuffers(10, vboHandles, 0);

		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);
		gl.glFrontFace(GL.GL_CW);

		try {
			skyboxTexture = TextureIO.newTexture(GL.GL_TEXTURE_CUBE_MAP);

			String[] suffixes = {"RT", "LF", "UP",
					                    "DN", "FT", "BK"};
			int[] targets = {GL3.GL_TEXTURE_CUBE_MAP_POSITIVE_X,
			                        GL3.GL_TEXTURE_CUBE_MAP_NEGATIVE_X,
					                GL3.GL_TEXTURE_CUBE_MAP_POSITIVE_Y,
					                GL3.GL_TEXTURE_CUBE_MAP_NEGATIVE_Y,
					                GL3.GL_TEXTURE_CUBE_MAP_POSITIVE_Z,
					                GL3.GL_TEXTURE_CUBE_MAP_NEGATIVE_Z};

			for(int i = 0; i < suffixes.length; i++ ) {
				BufferedImage img = ImageIO.read(new File("res/skybox/nebula" + suffixes[i] + ".png"));
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
		setupRings(drawable);

        camera.circle(0,25);
        camera.updatePosition();
	}

	private void loadShaders(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();

		grassShaderProgram = new ShaderProgram(drawable, "res/shaders/grass-shader");
		gl.glBindAttribLocation(grassShaderProgram.getShaderProgramLocation(), 0, "attribute_Position");
		gl.glBindAttribLocation(grassShaderProgram.getShaderProgramLocation(), 1, "attribute_Color");
		gl.glLinkProgram(grassShaderProgram.getShaderProgramLocation());

		skyboxShaderProgram = new ShaderProgram(drawable, "res/shaders/skybox-shader");
		gl.glBindAttribLocation(skyboxShaderProgram.getShaderProgramLocation(), 0, "aPosition");
		gl.glLinkProgram(skyboxShaderProgram.getShaderProgramLocation());

		ringShaderProgram = new ShaderProgram(drawable, "res/shaders/ring-shader");
		gl.glBindAttribLocation(ringShaderProgram.getShaderProgramLocation(), 0, "attribute_Position");
		gl.glLinkProgram(ringShaderProgram.getShaderProgramLocation());

		waterShaderProgram = new ShaderProgram(drawable, "res/shaders/water-shader");
		gl.glBindAttribLocation(waterShaderProgram.getShaderProgramLocation(), 0, "attribute_Position");
		gl.glBindAttribLocation(waterShaderProgram.getShaderProgramLocation(), 1, "attribute_Color");
		gl.glLinkProgram(waterShaderProgram.getShaderProgramLocation());
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();
		grassShaderProgram.deleteShaderProgram(drawable);
		skyboxShaderProgram.deleteShaderProgram(drawable);
		ringShaderProgram.deleteShaderProgram(drawable);
		waterShaderProgram.deleteShaderProgram(drawable);
	}

	public static void updatePlanet() {
		planetNeedsUpdate = true;
	}

	private void createPlanet(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();

		float[] vertices = World.getInstance().getSurfaceVertexArray();
		float[] colors = World.getInstance().getSurfaceColorArray();
		float[] waterVertices = World.getInstance().getSeaVertexArray();
		float[] waterColors = World.getInstance().getSeaColorArray();
        float[] cloudVertices = World.getInstance().getCloudVertexArray();
        float[] cloudColors = World.getInstance().getCloudColorArray();
        float[] modelsVertices = World.getInstance().getModelVertexArray();
        float[] modelsColors = World.getInstance().getModelColorArray();

		FloatBuffer vertexBuffer = Buffers.newDirectFloatBuffer(vertices);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[VERTICES_IDX]);

		int numBytes = vertices.length * 4;
		gl.glBufferData(GL.GL_ARRAY_BUFFER, numBytes, vertexBuffer, GL.GL_STATIC_DRAW);

		FloatBuffer colorBuffer = Buffers.newDirectFloatBuffer(colors);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[COLORS_IDX]);

		numBytes = colors.length * 4;
		gl.glBufferData(GL.GL_ARRAY_BUFFER, numBytes, colorBuffer, GL.GL_STATIC_DRAW);

		FloatBuffer waterVertexBuffer = Buffers.newDirectFloatBuffer(waterVertices);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[WATER_VERTICES_IDX]);

		numBytes = waterVertices.length * 4;
		gl.glBufferData(GL.GL_ARRAY_BUFFER, numBytes, waterVertexBuffer, GL.GL_STATIC_DRAW);

		FloatBuffer waterColorBuffer = Buffers.newDirectFloatBuffer(waterColors);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[WATER_COLORS_IDX]);

		numBytes = waterColors.length * 4;
		gl.glBufferData(GL.GL_ARRAY_BUFFER, numBytes, waterColorBuffer, GL.GL_STATIC_DRAW);

        FloatBuffer cloudVertexBuffer = Buffers.newDirectFloatBuffer(cloudVertices);
        gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[CLOUD_VERTICES_IDX]);

        numBytes = cloudVertices.length * 4;
        gl.glBufferData(GL.GL_ARRAY_BUFFER, numBytes, cloudVertexBuffer, GL.GL_STATIC_DRAW);

        FloatBuffer cloudColorBuffer = Buffers.newDirectFloatBuffer(cloudColors);
        gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[CLOUD_COLORS_IDX]);

        numBytes = cloudColors.length * 4;
        gl.glBufferData(GL.GL_ARRAY_BUFFER, numBytes, cloudColorBuffer, GL.GL_STATIC_DRAW);

        FloatBuffer modelsVertexBuffer = Buffers.newDirectFloatBuffer(modelsVertices);
        gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[MODELS_VERTICES_IDX]);

        numBytes = modelsVertices.length * 4;
        gl.glBufferData(GL.GL_ARRAY_BUFFER, numBytes, modelsVertexBuffer, GL.GL_STATIC_DRAW);

        FloatBuffer modelsColorsBuffer = Buffers.newDirectFloatBuffer(modelsColors);
        gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[MODELS_COLORS_IDX]);

        numBytes = modelsColors.length * 4;
        gl.glBufferData(GL.GL_ARRAY_BUFFER, numBytes, modelsColorsBuffer, GL.GL_STATIC_DRAW);

		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);

		planetVertexCount = vertices.length / 3;
		waterVertexCount = waterVertices.length / 3;
        cloudVertexCount = cloudVertices.length / 3;
        modelsVertexCount = modelsVertices.length / 3;

		planetNeedsUpdate = false;
	}

	private void setupRings(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();

		float[] ringCoords = {-2.0f, 0.0f, -2.0f, 2.0f, 0.0f, -2.0f, 2.0f, 0.0f, 2.0f,
				                       -2.0f, 0.0f, -2.0f, 2.0f, 0.0f, 2.0f, -2.0f, 0.0f, 2.0f};
		FloatBuffer ringBuffer = Buffers.newDirectFloatBuffer(ringCoords);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[RING_IDX]);
		int numBytes = ringCoords.length * 4;
		gl.glBufferData(GL.GL_ARRAY_BUFFER, numBytes, ringBuffer, GL.GL_STATIC_DRAW);

		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);

		ringVertexCount = ringCoords.length / 3;
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
		KeyInput.update();
        Leap.updateLeapControls();

		GL3 gl = drawable.getGL().getGL3();

		if(planetNeedsUpdate)
			createPlanet(drawable);

		gl.glClearColor(0, 0, 0, 0.5f);
		gl.glClear(GL3.GL_STENCIL_BUFFER_BIT |
					GL3.GL_COLOR_BUFFER_BIT |
					GL3.GL_DEPTH_BUFFER_BIT);

		gl.glUseProgram(skyboxShaderProgram.getShaderProgramLocation());

		skyboxShaderProgram.updateUniformMatrix4fv(drawable, "uProjectionMatrix", 1, false,
				                                          camera.projection().getBuffer().array(), 0);
		skyboxShaderProgram.updateUniformMatrix4fv(drawable, "uWorldToCameraMatrix", 1, false,
				                                          camera.view().getBuffer().array(), 0);

		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[SKYBOX_IDX]);
		gl.glDisable(GL.GL_DEPTH_TEST);

		gl.glActiveTexture(GL.GL_TEXTURE0);
		skyboxTexture.bind(gl);
		skyboxTexture.enable(gl);
		skyboxShaderProgram.updateUniform1f(drawable, "uTexture", 0);

		gl.glVertexAttribPointer(0, 2, GL3.GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(0);
		gl.glDrawArrays(GL3.GL_TRIANGLES, 0, skyboxVertexCount);
		gl.glDisableVertexAttribArray(0);

		skyboxTexture.disable(gl);

		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);

		gl.glEnable(GL.GL_DEPTH_TEST);

		// END SKYBOX DRAWING, BEGIN DRAWING WORLD

		gl.glUseProgram(grassShaderProgram.getShaderProgramLocation());

		double currTime = System.currentTimeMillis();

        if(GlobalOptions.getInstance().isAutoPan()) {
            theta = (currTime - prevTime) * 0.01f;
            if (theta >= 360 || theta < 0) {
                theta = theta % 360;
            }

			camera.circle(theta,0f);
        }

		grassShaderProgram.updateUniformMatrix4fv(drawable, "uniform_Model", 1, false,
				                                         camera.matrix().getBuffer().array(), 0);
		grassShaderProgram.updateUniformMatrix4fv(drawable, "uniform_Camera", 1, false,
				                                         planetMatrix.getBuffer().array(), 0);

		gl.glEnableVertexAttribArray(0);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[VERTICES_IDX]);
		gl.glVertexAttribPointer(0, 3, GL3.GL_FLOAT, false, 0, 0);

		gl.glEnableVertexAttribArray(1);
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[COLORS_IDX]);
		gl.glVertexAttribPointer(1, 4, GL3.GL_FLOAT, false, 0, 0);

		gl.glEnable(GL.GL_CULL_FACE);

		gl.glDrawArrays(GL3.GL_TRIANGLES, 0, planetVertexCount);

        gl.glEnableVertexAttribArray(0);
        gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[MODELS_VERTICES_IDX]);
        gl.glVertexAttribPointer(0, 3, GL3.GL_FLOAT, false, 0, 0);

        gl.glEnableVertexAttribArray(1);
        gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[MODELS_COLORS_IDX]);
        gl.glVertexAttribPointer(1, 4, GL3.GL_FLOAT, false, 0, 0);

		gl.glDrawArrays(GL3.GL_TRIANGLES, 0, modelsVertexCount);
        
		gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(gl.GL_BLEND);

		gl.glUseProgram(waterShaderProgram.getShaderProgramLocation());

		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[WATER_VERTICES_IDX]);
		gl.glVertexAttribPointer(0, 3, GL3.GL_FLOAT, false, 0, 0);

		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[WATER_COLORS_IDX]);
		gl.glVertexAttribPointer(1, 4, GL3.GL_FLOAT, false, 0, 0);

		waterShaderProgram.updateUniformMatrix4fv(drawable, "uniform_Model", 1, false,
				                                         camera.matrix().getBuffer().array(), 0);
		waterShaderProgram.updateUniformMatrix4fv(drawable, "uniform_Camera", 1, false,
				                                         planetMatrix.getBuffer().array(), 0);

		time = time + (float)(currTime - prevTime) * 0.0001f;
		time = time % (float)(2.0f * Math.PI);
		waterShaderProgram.updateUniform1f(drawable, "uniform_Time", time);

		prevTime = currTime;

		gl.glDrawArrays(GL3.GL_TRIANGLES, 0, waterVertexCount);

		gl.glUseProgram(grassShaderProgram.getShaderProgramLocation());

        gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[CLOUD_VERTICES_IDX]);
        gl.glVertexAttribPointer(0, 3, GL3.GL_FLOAT, false, 0, 0);

        gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[CLOUD_COLORS_IDX]);
        gl.glVertexAttribPointer(1, 4, GL3.GL_FLOAT, false, 0, 0);

		gl.glDisable(GL.GL_DEPTH_TEST);

        gl.glDrawArrays(GL3.GL_TRIANGLES, 0, cloudVertexCount);

		gl.glDisable(gl.GL_BLEND);
        gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDisable(GL.GL_CULL_FACE);

		gl.glDisableVertexAttribArray(0);
		gl.glDisableVertexAttribArray(1);

		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);

		// END WORLD DRAWING, BEGIN DRAWING RING

		if(WorldOptions.getInstance().isPlanetRings()) {
			gl.glEnable(gl.GL_BLEND);
			gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);
			gl.glUseProgram(ringShaderProgram.getShaderProgramLocation());

			ringShaderProgram.updateUniformMatrix4fv(drawable, "uniform_Camera", 1, false,
					                                        camera.matrix().getBuffer().array(), 0);

			gl.glEnableVertexAttribArray(0);
			gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboHandles[RING_IDX]);
			gl.glVertexAttribPointer(0, 3, GL3.GL_FLOAT, false, 0, 0);
			gl.glDrawArrays(GL3.GL_TRIANGLES, 0, ringVertexCount);
			gl.glDisableVertexAttribArray(0);
			gl.glBindBuffer(GL.GL_ARRAY_BUFFER, 0);
			gl.glDisable(gl.GL_BLEND);
		}
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        camera.setViewportAspectRatio((float)width / height);
	}
}
