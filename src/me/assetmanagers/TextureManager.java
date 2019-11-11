package me.assetmanagers;

import me.graphics.Texture;
import me.graphics.TextureLoader;
import me.main.Logger;

public class TextureManager extends ResourceManager<Texture> {

	/**
	 * @param filename Filename of texture including directory path
	 * @return Texture object containing id and size information
	 */
	@Override
	public Texture getResource(String filename) {
		Texture tex = super.getResource(filename);
		if (tex != null) return tex;
		
		Logger.LOG("Loaded texture: \"" + filename + "\"");
		Texture texture = TextureLoader.loadTexture(filename);
		return texture;
	}
}
