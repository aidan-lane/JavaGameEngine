package gamestate;

import org.luaj.vm2.LuaValue;

import component.CollisionComponent;
import component.Component;
import component.GraphicsComponent;
import component.MovementComponent;
import entity.Entity;
import system.CollisionSystem;

public class GameState {

	private GameStateManager gsm;
	private LuaValue stateValue;
	
	// unique ID set to each newly created entity
	private int curUID;
	
	// Systems
	CollisionSystem collisionSystem = new CollisionSystem();
	
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
		collisionSystem.update(delta);
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
			Component mc = new MovementComponent(entity, movementTableVal);
			entity.add(mc);
			// add component to its respective system
		}
		
		LuaValue graphicsTableVal = entityTable.get("GraphicsComponent");
		if (graphicsTableVal.isvalidkey()) {
			Component gc = new GraphicsComponent(entity, graphicsTableVal);
			entity.add(gc);
		}
		
		LuaValue collisionTableVal = entityTable.get("CollisionComponent");
		if (collisionTableVal.isvalidkey()) {
			Component cc = new CollisionComponent(entity, collisionTableVal);
			entity.add(cc);
			collisionSystem.add(cc);
		}
		
		return entity;
	}
}
