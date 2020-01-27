package gfx;

import static org.lwjgl.opengl.GL20.*;

public class TexturedRect extends Rect {
	
	private Texture texture;
	
	/**
	 * @param x Starting x coordinate
	 * @param y Starting y coordinate
	 * @param w Default width of rect (size can change independent of texture size)
	 * @param h Default height of rect
	 * @param tex Texture object that holds unique id and texture size
	 */
	public TexturedRect(float x, float y, float w, float h, Texture tex) {
		super(x, y, w, h);
		this.texture = tex;
	}
	
	@Override
	public void render() {
		this.render(0.0f, 0.0f, this.texture.getWidth(), this.texture.getHeight(), 
				this.texture.getWidth(), this.texture.getHeight());
	}
	
	/**
	 * @param t1, t2, t3, t4 Pixel coordinate of sub-image of texture.
	 * @param tw final width of texture
	 * @param th final height of texture
	 */
	public void render(float t1, float t2, float t3, float t4, int tw, int th) {
		glBindTexture(GL_TEXTURE_2D, this.texture.getID());
		glEnable(GL_TEXTURE_2D);
		
		glBegin(GL_QUADS);
			glTexCoord2f(t1/tw, t2/th);
			glVertex2f(this.x, this.y);
			glTexCoord2f(t3/tw, t1/th);
			glVertex2f(this.x + this.width, this.y);
			glTexCoord2f(t3/tw, t4/th);
			glVertex2f(this.x + this.width, this.y + this.height);
			glTexCoord2f(t1/tw, t4/th);
			glVertex2f(this.x, this.y + this.height);
		glEnd();
		
		glDisable(GL_TEXTURE_2D);
	    glBindTexture(GL_TEXTURE_2D, 0);
	}
}
