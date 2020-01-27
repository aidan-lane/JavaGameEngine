package gamestate;

import org.luaj.vm2.LuaValue;

import component.MovementComponent;
import entity.Entity;

public class GameState {

	private GameStateManager gsm;
	private LuaValue stateValue;
	
	// unique ID set to each newly created entity
	private int curUID;
	
	public GameState(String name, String stateFile, GameStateManager gsm) {
		this.gsm = gsm;
		
		gsm.globals.get("dofile").call(LuaValue.valueOf(stateFile));
		stateValue = gsm.globals.get(name);
	}
	
	public void init() {
		this.curUID = 0;
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
		
		LuaValue movementTableVal = entityTable.get("MovementComponent");
		if (movementTableVal.isvalidkey()) {
			MovementComponent mc = new MovementComponent(entity, movementTableVal);
			entity.add(mc);
			// add component to its respective system
		}
		
		return entity;
	}
}
