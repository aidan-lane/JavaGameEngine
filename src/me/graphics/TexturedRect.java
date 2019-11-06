package me.graphics;

import static org.lwjgl.opengl.GL20.*;

public class TexturedRect {

	private float x;
	private float y;
	private float width;
	private float height;
	
	public TexturedRect(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	
	public TexturedRect(float w, float h) {
		this(0, 0, w, h);
	}
	
	public void render() {
		glBegin(GL_QUADS);
			glVertex2f(this.x, this.y);
			glVertex2f(this.x + this.width, this.y);
			glVertex2f(this.x + this.width, this.y + this.height);
			glVertex2f(this.x, this.y + this.height);
		glEnd();
	}
}
