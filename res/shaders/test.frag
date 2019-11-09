#version 120 core

uniform sampler2D texture1;

void main(){
	vec4 color = texture2D(texture1, gl_TexCoord[0].st);
	vec3 lum = vec3(0.299, 0.587, 0.114);
	gl_FragColor = vec4(vec3(dot(color.rgb, lum)), color.a);
}
