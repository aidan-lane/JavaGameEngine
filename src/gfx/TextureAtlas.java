package gfx;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import main.Main;

public class TextureAtlas {
	
	private Texture texture;
	
	private int tileWidth;
	private int tileHeight;
	private int tilesPerWidth;
	
	public TextureAtlas(String path, int tileWidth, int tileHeight) {
		texture = Main.textures.getResource(path);
		
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.tilesPerWidth = texture.getWidth() / this.tileWidth;
	}
	
	public void renderTile(int id, float x, float y, int width, int height) {
		glBindTexture(GL_TEXTURE_2D, this.texture.getID());
		glEnable(GL_TEXTURE_2D);
		
		int tx = id % tilesPerWidth;
		int ty = id / tilesPerWidth;
		
		float t1 = tx * tileWidth;
		float t2 = ty * tileHeight;
		float t3 = tx * tileWidth + tileWidth;
		float t4 = ty * tileHeight + tileHeight;
		
		glBegin(GL_QUADS);
			glTexCoord2f(t1/texture.getWidth(), t2/texture.getHeight());
			glVertex2f(x, y);
			glTexCoord2f(t3/texture.getWidth(), t2/texture.getHeight());
			glVertex2f(x + width, y);
			glTexCoord2f(t3/texture.getWidth(), t4/texture.getHeight());
			glVertex2f(x + width, y + height);
			glTexCoord2f(t1/texture.getWidth(), t4/texture.getHeight());
			glVertex2f(x, y + height);
		glEnd();
		
		glDisable(GL_TEXTURE_2D);
	    glBindTexture(GL_TEXTURE_2D, 0);
	}
}
