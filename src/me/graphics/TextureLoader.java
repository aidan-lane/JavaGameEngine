package me.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import me.main.Logger;

import static org.lwjgl.opengl.GL20.*;

public class TextureLoader {

	private static final int BYTES_PER_PIXEL = 4;
	
	private static BufferedImage loadImage(String filename) {
		try {
			return ImageIO.read(new File(filename));
		} catch (IOException e) {
			Logger.LOG("Could not load image: " + filename);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param filename complete path of texture png
	 * @return Texture object containing OpenGL id and size
	 */
	public static Texture loadTexture(String filename) {
		BufferedImage image = loadImage(filename);
		int width = image.getWidth();
		int height = image.getHeight();
		
		int[] pixels = new int[width * height];
		image.getRGB(0, 0, width, height, pixels, 0, width);
		
		ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * BYTES_PER_PIXEL);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixel = pixels[y * width + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF));
				buffer.put((byte) ((pixel >> 8) & 0xFF));
				buffer.put((byte) (pixel & 0xFF));
				buffer.put((byte) ((pixel >> 24) & 0xFF));
			}
		}
		
		buffer.flip();
		
		int textureID = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, textureID);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		
		return new Texture(textureID, image.getWidth(), image.getHeight());
	}
	
	/**
	 * @param id unloads texture of with specified id
	 */
	public static void unloadTexture(int id) {
		GL11.glDeleteTextures(id);
	}
}
