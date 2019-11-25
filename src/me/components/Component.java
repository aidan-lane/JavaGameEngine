package me.components;

import me.entity.Entity;

public abstract class Component {
	
	public Entity entity;
	
	public Component(Entity e) {
		this.entity = e;
	}
}
