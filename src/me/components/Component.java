package me.components;

import org.luaj.vm2.LuaValue;

import me.entity.Entity;

public abstract class Component {
	
	public Entity entity;
	protected LuaValue table;
	
	public Component(Entity e, LuaValue table) {
		this.entity = e;
		this.table = table;
	}
}
