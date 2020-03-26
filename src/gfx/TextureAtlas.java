package gfx;

import java.awt.Rectangle;

import main.Main;

public class TextureAtlas {
	
	private Texture atlas;
	
	private int tileWidth;
	private int tileHeight;
	
	public TextureAtlas(String path, int tileWidth, int tileHeight) {
		atlas = Main.textures.getResource(path);
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}
	
	/**
	 * Returns a normalized rectangle given a texture id.
	 * Here, a texture id is determined by its 2d coordinates in the atlas.
	 * @param id
	 * @return Rectangle
	 */
	public Rectangle getNormalizedTile(int id) {
		int tilesPerWidth = atlas.getWidth() / this.tileWidth;
		int x = id % tilesPerWidth;
		int y = id / tilesPerWidth;
		return new Rectangle((x * tileWidth) / atlas.getWidth(), (y * tileHeight) / atlas.getHeight(), 
				tileWidth / atlas.getWidth(), tileHeight / atlas.getHeight());
	}
}
