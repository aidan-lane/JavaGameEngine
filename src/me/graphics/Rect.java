package me.graphics;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

public class Rect {

	protected float x, y, width, height;
	
	public Rect(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
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
