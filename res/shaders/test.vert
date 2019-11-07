#version 120 core

out vec4 vertColor;
uniform vec3 uColor;

void main() {
	gl_Position = gl_ProjectionMatrix * gl_ModelViewMatrix * gl_Vertex;
	vertColor = vec4(uColor, 1.0);
}
