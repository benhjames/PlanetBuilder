package uk.ac.cam.cl.bravo.PlanetBuilder;

import javax.media.opengl.GL3;
import javax.media.opengl.GLAutoDrawable;

public class ShaderProgram {

	private int vertShader;
	private int fragShader;
	private int shaderProgram;

	public ShaderProgram(GLAutoDrawable drawable, String path) {
		GL3 gl = drawable.getGL().getGL3();

		vertShader = Shader.createShader(drawable, GL3.GL_VERTEX_SHADER, path + ".vp");
		fragShader = Shader.createShader(drawable, GL3.GL_FRAGMENT_SHADER, path + ".fp");

		shaderProgram = gl.glCreateProgram();
		gl.glAttachShader(shaderProgram, vertShader);
		gl.glAttachShader(shaderProgram, fragShader);
	}

	public int getShaderProgramLocation() {
		return shaderProgram;
	}

	public void updateUniform1f(GLAutoDrawable drawable, String location, float newValue) {
		GL3 gl = drawable.getGL().getGL3();
		gl.glUniform1f(gl.glGetUniformLocation(shaderProgram, location), newValue);
	}

	public void updateUniformMatrix4fv(GLAutoDrawable drawable, String location, int count,
	                                   boolean transpose, float[] data, int offset) {
		GL3 gl = drawable.getGL().getGL3();
		gl.glUniformMatrix4fv(gl.glGetUniformLocation(shaderProgram, location), count, transpose, data, offset);
	}

	public void deleteShaderProgram(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();
		gl.glDetachShader(shaderProgram, vertShader);
		gl.glDeleteShader(vertShader);
		gl.glDetachShader(shaderProgram, fragShader);
		gl.glDeleteShader(fragShader);
		gl.glDeleteProgram(shaderProgram);
	}
}
