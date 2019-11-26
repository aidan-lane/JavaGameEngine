package me.gamestate;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import me.entity.Entity;

public class GameState {

	private GameStateManager gsm;
	
	Globals eL;
	private int curUID;
	
	public GameState(String stateFile, GameStateManager gsm) {
		this.gsm = gsm;
		eL = JsePlatform.standardGlobals();
		
		gsm.globals.get("dofile").call(LuaValue.valueOf(stateFile));
	}
	
	public void init() {
		LuaValue initFunc = gsm.globals.get("init");
		initFunc.call();
	}
	
	public void update(double delta) {
		
	}
	
	public void render() {
		
	}
	
	public void exit() {
		
	}
	
	public Entity createEntity(String type, String scriptPath) {
		eL.get("dofile").call(LuaValue.valueOf(scriptPath));
		Entity e = new Entity(curUID++, type);
		
		return e;
	}
}
