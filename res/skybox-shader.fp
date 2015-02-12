#if __VERSION__ >= 130
	// GLSL 130+ uses in and out
	#define attribute in
	#define varying out
#endif

#ifdef GL_ES
	precision mediump float;
	precision mediump int;
#endif

uniform samplerCube uTexture;

smooth attribute vec3 eyeDirection;

varying vec4 fragmentColor;

void main() {
    fragmentColor = texture(uTexture, eyeDirection);
}