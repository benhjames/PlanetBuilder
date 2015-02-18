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

varying vec4 varying_Position;

float mag(vec4 v) {
    return (v.x * v.x) + (v.y * v.y) + (v.z * v.z);
}

void main (void)
{
    float m = mag(varying_Position) / 2.829f;
    if(m < 0.6f)
	    gl_FragColor = vec4(0.0f, 0.0f, 0.0f, 0.0f);
	else if(m < 0.7f)
	    gl_FragColor = vec4(m, m - 0.5f, m, (m - 0.6f) * 5.0f);
	else if(m < 0.8f)
	    gl_FragColor = vec4(m, m - 0.5f, m, 0.5f);
	else if(m < 0.9f)
	    gl_FragColor = vec4(m, m - 0.6f, m, (0.9f - m) * 5.0f);
	else
	    gl_FragColor = vec4(0.0f, 0.0f, 0.0f, 0.0f);
}