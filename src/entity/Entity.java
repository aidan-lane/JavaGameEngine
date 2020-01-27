package entity;

import java.util.HashMap;
import java.util.Map;

import component.Component;

public class Entity {

	private int UID;
	private String type;
	private Map<Class<?>, Component> components;
	
	public Entity(int UID, String type) {
		this.UID = UID;
		this.type = type;
		components = new HashMap<>();
	}
	
	public Component get(Class<?> c) {
		return components.get(c);
	}
	
	public void add(Component c) {
		if (components.containsKey(c.getClass()))
			throw new RuntimeException("Cannot add duplicate components!");
		components.put(c.getClass(), c);
	}
	
	public String getType() {
		return type;
	}
	
	public int getUID() {
		return this.UID;
	}
}
