package me.graphics;

import static org.lwjgl.opengl.GL20.*;

public class TexturedRect extends Rect {
	
	private int textureID;
	
	public TexturedRect(float x, float y, float w, float h, int tex) {
		super(x, y, w, h);
		this.textureID = tex;
	}
	
	@Override
	public void render() {
		this.render(0.0f, 0.0f, 1.0f, 1.0f);
	}
	
	public void render(float t1, float t2, float t3, float t4) {
		glBindTexture(GL_TEXTURE_2D, this.textureID);
		glBegin(GL_QUADS);
			glTexCoord2f(t1, t2);
			glVertex2f(this.x, this.y);
			glTexCoord2f(t3, t2);
			glVertex2f(this.x + this.width, this.y);
			glTexCoord2f(t3, t4);
			glVertex2f(this.x + this.width, this.y + this.height);
			glTexCoord2f(t1, t4);
			glVertex2f(this.x, this.y + this.height);
		glEnd();
	}
}
