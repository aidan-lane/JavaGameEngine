package gfx;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_INFO_LOG_LENGTH;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniform3f;
import static org.lwjgl.opengl.GL20.glUniform4f;
import static org.lwjgl.opengl.GL20.glUseProgram;

import java.io.BufferedReader;
import java.io.FileReader;

import org.lwjgl.opengl.GL20;

import main.Engine;

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
		
		glShaderSource(vShader, loadFile(vSource));
		glShaderSource(fShader, loadFile(fSource));
		
		glCompileShader(vShader);
		glCompileShader(fShader);
		
		// output compile status
		Engine.logger.log(glGetShaderInfoLog(vShader, glGetShaderi(vShader, GL_INFO_LOG_LENGTH)));
		Engine.logger.log(glGetShaderInfoLog(fShader, glGetShaderi(fShader, GL_INFO_LOG_LENGTH)));
		
		// create program and link shaders
		program = glCreateProgram();
		glAttachShader(program, vShader);
		glAttachShader(program, fShader);
		glLinkProgram(program);
		Engine.logger.log(glGetProgramInfoLog(program, glGetProgrami(program, GL_INFO_LOG_LENGTH)));
		
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
	
	public void unbind() {
		glUseProgram(0);
	}
	
	private int getUniformLocation(String name) {
		int loc = glGetUniformLocation(this.program, name);
		if(loc == -1)
			throw new RuntimeException("Uniform " + name + " does not exist in shader program: " + this.program);
		return loc;
	}
	
	public void setUniform(String name, float a) {
		glUniform1f(getUniformLocation(name), a);
	}
	
	public void setUniform(String name, float a, float b) {
		glUniform2f(getUniformLocation(name), a, b);
	}
	
	public void setUniform(String name, float a, float b, float c) {
		glUniform3f(getUniformLocation(name), a, b, c);
	}
	
	public void setUniform(String name, float a, float b, float c, float d) {
		glUniform4f(getUniformLocation(name), a, b, c, d);
	}
	
	public void setUniform(String name, int t) {
		GL20.glUniform1i(getUniformLocation(name), 0);
	}
	
	/**
	 * Given a filename, will return as formatted String
	 * 
	 * @param filename path of shader source
	 * @return a String of the file's contents
	 */
	private String loadFile(String filename) {
		StringBuilder code = new StringBuilder();
		String line = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while ((line = reader.readLine()) != null) {
				code.append(line);
				code.append("\n");
			}
			reader.close();
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to load shader from ["+filename+"]", e);
		}
		return code.toString();
	}
}
