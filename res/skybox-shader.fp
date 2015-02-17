#if __VERSION__ >= 130
	#define varying out
	#define attribute in
	out vec4 mgl_FragColor;
	#define texture2D texture
	#define gl_FragColor mgl_FragColor
#endif

#ifdef GL_ES
	precision mediump float;
	precision mediump int;
#endif

uniform samplerCube uTexture;

smooth attribute vec3 eyeDirection;

void main() {
    gl_FragColor = texture(uTexture, eyeDirection);
}