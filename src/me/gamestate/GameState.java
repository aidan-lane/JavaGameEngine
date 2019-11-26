package me.gamestate;

import org.luaj.vm2.LuaValue;

import me.components.MovementComponent;
import me.entity.Entity;

public class GameState {

	private GameStateManager gsm;
	private LuaValue stateValue;
	
	private int curUID;
	
	public GameState(String name, String stateFile, GameStateManager gsm) {
		this.gsm = gsm;
		
		gsm.globals.get("dofile").call(LuaValue.valueOf(stateFile));
		stateValue = gsm.globals.get(name);
	}
	
	public void init() {
		LuaValue initFunc = stateValue.get("init");
		initFunc.call();
		
		createEntity("test", "res/scripts/test.lua");
	}
	
	public void update(double delta) {
		
	}
	
	public void render() {
		
	}
	
	public void exit() {
		
	}
	
	public Entity createEntity(String type, String scriptPath) {
		gsm.globals.get("dofile").call(LuaValue.valueOf(scriptPath));
		Entity entity = new Entity(curUID++, type);
		LuaValue entityTable = gsm.globals.get(type);
		
		MovementComponent mc = new MovementComponent(entity, entityTable.get("MovementComponent"));
		entity.add(mc);
		
		return entity;
	}
}
