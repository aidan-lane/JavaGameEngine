package me.gamestate;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptException;

public class GameState {

	private GameStateManager gsm;
	private Invocable invocable;
	
	public GameState(String path, GameStateManager gsm) {
		this.gsm = gsm;
		try {
			this.gsm.engine.eval(new FileReader(path));
		} catch (FileNotFoundException | ScriptException e) {
			e.printStackTrace();
		}
		invocable = (Invocable) this.gsm.engine;
	}
	
	public void init() {
		try {
			invocable.invokeFunction("init", this);
		} catch (NoSuchMethodException | ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public void update(double delta) {
		try {
			invocable.invokeFunction("update");
		} catch (NoSuchMethodException | ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public void render() {
		
	}
	
	public void exit() {
		
	}
}
