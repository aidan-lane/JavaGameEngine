package main;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.IntBuffer;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL13;
import org.lwjgl.system.MemoryStack;

import gamestate.Game;
import gamestate.GameStateManager;
import gamestate.Menu;
import gfx.ShaderProgram;
import gfx.TexturedRect;
import manager.TextureManager;

public class Main implements Runnable {
	
	private long window;
	public static final int VIRTUAL_WIDTH = 1280;
	public static final int VIRTUAL_HEIGHT = 720;
	
	private static int SCREEN_HEIGHT;
	
	private GameStateManager gsm;
	
	// static managers
	public static TextureManager textures = new TextureManager();
	
	// testing
	TexturedRect r;
	ShaderProgram p;
	
	private void init() {
		System.out.println("Running LWJGL Version " + Version.getVersion());
		
		if (!glfwInit())
			throw new IllegalStateException("Cannot initialize GLFW");
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		window = glfwCreateWindow(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, "Game", NULL, NULL);
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
			SCREEN_HEIGHT = vidmode.height();
			
			glfwSetWindowPos(
				window,
				(pWidth.get(0) / 4),
				(pHeight.get(0) / 4)
			);
		}
		
		gsm = new GameStateManager();
		gsm.addState("MENU", new Menu(gsm));
		gsm.addState("GAME", new Game(gsm));
		gsm.setState("GAME");
		
		glfwMakeContextCurrent(window);
		glfwSwapInterval(1); // enable v-sync
		glfwShowWindow(window);
	}
	
	public void run() {
		init();
		
		// configure global render options
		GL.createCapabilities();
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		glMatrixMode(GL_PROJECTION);
		glOrtho(0.f, VIRTUAL_WIDTH, VIRTUAL_HEIGHT, 0.f, -1.f, 1.f);
		glMatrixMode(GL_MODELVIEW);
		
		GL13.glActiveTexture(0); 
		
		// window resize event
		glfwSetWindowSizeCallback(window, new GLFWWindowSizeCallback() {
			@Override
			public void invoke(long window, int width, int height) {
				float targetAspectRatio = (float) VIRTUAL_WIDTH / VIRTUAL_HEIGHT;
				int tWidth = width;
				int tHeight = (int)(tWidth / targetAspectRatio + 0.5f);
				if (tHeight > SCREEN_HEIGHT) {
					tHeight = SCREEN_HEIGHT;
					tWidth = (int)(tHeight * targetAspectRatio + 0.5f);
				}
				
				float wRatio = (float) VIRTUAL_WIDTH / tWidth;
				float hRatio = (float) VIRTUAL_HEIGHT / tHeight;
				
				glViewport(0, 0, width, height);
				
				glMatrixMode(GL_PROJECTION);
				glPushMatrix();
				glLoadIdentity();
				glOrtho(0, VIRTUAL_WIDTH*wRatio, VIRTUAL_HEIGHT*hRatio, 0, -1, 1);
				glMatrixMode(GL_MODELVIEW);
				glPushMatrix();
				glLoadIdentity();
			}
		});
		
		// testing
		r = new TexturedRect(50, 50, 100, 100, Main.textures.getResource("res/test.png"));
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
		this.gsm.update(delta);
		glfwPollEvents();
	}
	
	private void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		this.gsm.render();
		
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
