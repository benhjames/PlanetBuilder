package uk.ac.cam.cl.bravo.PlanetBuilder;

import javax.media.opengl.GL3;
import javax.media.opengl.GLAutoDrawable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Shader {

	static int createShader(GLAutoDrawable drawable, int shaderType, String path) {
		GL3 gl = drawable.getGL().getGL3();
		int shader = gl.glCreateShader(shaderType);

		String shaderSource = "";

		try {
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			shaderSource = new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.err.println("Error: shader source not found.");
			e.printStackTrace();
			System.exit(1);
		}

		// Make the shader strings compatible with OpenGL 3 core if needed
		if (gl.isGL3core()) {
			shaderSource = "#version 150\n" + shaderSource;
		}

		//Compile the vertexShader String into a program.
		String[] vlines = new String[]{shaderSource};
		int[] vlengths = new int[]{vlines[0].length()};
		gl.glShaderSource(shader, vlines.length, vlines, vlengths, 0);
		gl.glCompileShader(shader);

		//Check compile status.
		int[] compiled = new int[1];
		gl.glGetShaderiv(shader, GL3.GL_COMPILE_STATUS, compiled, 0);
		if (compiled[0] == 0) {
			int[] logLength = new int[1];
			gl.glGetShaderiv(shader, GL3.GL_INFO_LOG_LENGTH, logLength, 0);

			byte[] log = new byte[logLength[0]];
			gl.glGetShaderInfoLog(shader, logLength[0], null, 0, log, 0);

			System.err.println("Error compiling the shader: " + new String(log));
			System.exit(1);
		}

		return shader;
	}
}
