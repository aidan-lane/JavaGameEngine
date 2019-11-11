package me.graphics;

public class Texture {

	private int id;
	private int texWidth;
	private int texHeight;
	
	/**
	 * @param id The OpenGL global texture id
	 * @param tw Width of the OpenGL texture
	 * @param th Height of the OpenGL texture
	 */
	public Texture(int id, int tw, int th) {
		this.id = id;
		this.texWidth = tw;
		this.texHeight = th;
	}
	
	/**
	 * @return OpenGL texture ID
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * @return Texture's width in pixels
	 */
	public final int getWidth() {
		return texWidth;
	}
	
	/**
	 * @return Texture's height in pixels
	 */
	public final int getHeight() {
		return texHeight;
	}
}
