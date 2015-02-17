#if __VERSION__ >= 130
	#define varying in
	out vec4 mgl_FragColor;
	#define texture2D texture
	#define gl_FragColor mgl_FragColor
#endif

#ifdef GL_ES
	precision mediump float;
	precision mediump int;
#endif

varying vec4 varying_Color;
varying vec4 varying_Position;

float mag(vec4 v) {
    return (v.x * v.x) + (v.y * v.y) + (v.z * v.z);
}

void main (void)
{
    //float m = (mag(varying_Position) - 0.8) * 2.5;
	gl_FragColor = varying_Color;// + vec4(m,m,m,1.0f);
}