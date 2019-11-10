package me.main;

import org.lwjgl.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import me.assetmanagers.TextureManager;
import me.graphics.ShaderProgram;
import me.graphics.TexturedRect;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main implements Runnable {
	
	private long window;
	
	public static TextureManager textures = new TextureManager();
	
	int tex;
	TexturedRect r;
	
	ShaderProgram p;
	
	private void init() {
		System.out.println("Running LWJGL Version " + Version.getVersion());
		
		if (!glfwInit())
			throw new IllegalStateException("Cannot initialize GLFW");
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		window = glfwCreateWindow(1280, 720, "Game", NULL, NULL);
		if (window == NULL)
			throw new RuntimeException("Failed to create GLFW window");
		
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
				glfwSetWindowShouldClose(window, true);
		});
		
		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);
			
			glfwGetWindowSize(window, pWidth, pHeight);
			
			//GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			glfwSetWindowPos(
				window,
				(pWidth.get(0) / 4),
				(pHeight.get(0) / 4)
			);
		}
		
		glfwMakeContextCurrent(window);
		glfwSwapInterval(1); // enable v-sync
		glfwShowWindow(window);
	}
	
	public void run() {
		init();
		
		// configure global render options
		GL.createCapabilities();
		
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
		
		glMatrixMode(GL_PROJECTION);
		glOrtho(0.f, 1280, 720, 0.f, -1.f, 1.f);
		glMatrixMode(GL_MODELVIEW);
		
		GL13.glActiveTexture(0); 
		
		// testing
		tex = Main.textures.getResource("res/test.png");
		r = new TexturedRect(50, 50, 100, 100, tex);
		p = new ShaderProgram("res/shaders/test.vert", "res/shaders/test.frag");
		
		int fps = 60;
		double ticksPerSecond = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while (!glfwWindowShouldClose(window)) {
			now = System.nanoTime();
			delta += (now - lastTime) / ticksPerSecond;
			lastTime = now;
			
			while (delta >= 1) {
				update(delta);
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				System.out.println("FPS: " + frames);
				timer += 1000;
				frames = 0;
			}
		}
		
		// cleanup
		glfwFreeCallbacks(window);
		Logger.close();
	}
	
	private void update(double delta) {
		glfwPollEvents();
	}
	
	private void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		// test render
		p.use();
		p.setUniform("texture1", 0);
		r.render();
		p.unbind();

		glfwSwapBuffers(window);
	}
	
	public static void main(String[] args) {
		new Main().run();
		new Logger();
	}

}
