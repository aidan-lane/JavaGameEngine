package me.assetmanagers;

import me.graphics.TextureLoader;
import me.main.Logger;

public class TextureManager extends ResourceManager<Integer> {

	@Override
	public Integer getResource(String filename) {
		Integer tex = super.getResource(filename);
		if (tex != null) return tex;
		
		Logger.LOG("Loaded texture: \"" + filename + "\"");
		int textureID = TextureLoader.loadTexture(filename);
		return new Integer(textureID);
	}
}
