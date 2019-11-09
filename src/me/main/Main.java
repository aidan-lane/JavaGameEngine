package me.main;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
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

public class Main {
	
	private long window;
	
	public static TextureManager textures = new TextureManager();
	
	public void run() {
		System.out.println("Running LWJGL Version " + Version.getVersion());
		
		init();
		loop();
		
		glfwFreeCallbacks(window);
		
		Logger.close();
	}
	
	private void init() {
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
			
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
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
	
	private void loop() {
		GL.createCapabilities();
		
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
		
		glMatrixMode(GL_PROJECTION);
		glOrtho(0.f, 1280, 720, 0.f, -1.f, 1.f);
		glMatrixMode(GL_MODELVIEW);
		
		GL13.glActiveTexture(0); 
		
		int tex = Main.textures.getResource("res/test.png");
		TexturedRect r = new TexturedRect(50, 50, 100, 100, tex);
		
		ShaderProgram p = new ShaderProgram("res/shaders/test.vert", "res/shaders/test.frag");
		
		while (!glfwWindowShouldClose(window)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			// test render
			p.use();
			p.setUniform("texture1", 0);
			r.render();
			p.unbind();

			glfwSwapBuffers(window);
			
			glfwPollEvents();
		}
	}
	
	public static void main(String[] args) {
		new Main().run();
		@SuppressWarnings("unused")
		Logger logger = new Logger();
	}

}
