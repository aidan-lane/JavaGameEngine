package me.graphics;

import static org.lwjgl.opengl.GL20.*;

import me.main.Logger;

public class ShaderProgram {

	private int vShader;
	private int fShader;
	private int program;
	
	/**
	 * Creates an OpenGL shader program
	 * 
	 * @param vSource vertex shader source file path
	 * @param fSource fragment shader source file path
	 */
	public ShaderProgram(String vSource, String fSource) {
		vShader = glCreateShader(GL_VERTEX_SHADER);
		fShader = glCreateShader(GL_FRAGMENT_SHADER);
		
		glShaderSource(vShader, vSource);
		glShaderSource(fShader, fSource);
		
		glCompileShader(vShader);
		glCompileShader(fShader);
		
		// output compile status
		Logger.LOG(glGetShaderInfoLog(vShader, glGetShaderi(vShader, GL_INFO_LOG_LENGTH)));
		Logger.LOG(glGetShaderInfoLog(fShader, glGetShaderi(fShader, GL_INFO_LOG_LENGTH)));
		
		// create program and link shaders
		program = glCreateProgram();
		glAttachShader(program, vShader);
		glAttachShader(program, fShader);
		glLinkProgram(program);
		Logger.LOG(glGetProgramInfoLog(program, glGetProgrami(program, GL_INFO_LOG_LENGTH)));
		
		// no longer need individual shaders
		glDeleteShader(vShader);
		glDeleteShader(fShader);
	}
	
	/**
	 * Uses shader program in current OpenGL context
	 */
	public void use() {
		glUseProgram(program);
	}
}
