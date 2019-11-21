package me.entity;

import java.util.HashMap;
import java.util.Map;

import me.components.Component;

public class Entity {

	private int UID;
	private Map<Class<?>, Component> components;
	
	public Entity(int UID) {
		this.UID = UID;
		components = new HashMap<>();
	}
	
	public Component get(Component c) {
		return components.get(c.getClass());
	}
	
	
}
