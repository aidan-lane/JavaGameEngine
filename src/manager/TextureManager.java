package manager;

import gfx.Texture;
import gfx.TextureLoader;
import main.Engine;

/**
 * Static manager for loading texture assets
 */
public class TextureManager extends ResourceManager<Texture> {

	protected Texture loadResource(String file) {
		Engine.logger.log("Loaded texture: \"" + file + "\"");
		return TextureLoader.loadTexture(file);
	}
}
