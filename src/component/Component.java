package component;

import org.luaj.vm2.LuaValue;

import entity.Entity;

public abstract class Component {
	
	public Entity entity;
	protected LuaValue table;
	
	public Component(Entity e, LuaValue table) {
		this.entity = e;
		this.table = table;
	}
}
