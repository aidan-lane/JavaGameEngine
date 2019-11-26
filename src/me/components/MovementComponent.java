package me.components;

import org.luaj.vm2.LuaValue;

import me.entity.Entity;

public class MovementComponent extends Component {
	
	private float x, y, rotation;
	
	public MovementComponent(Entity e, LuaValue t) {
		super(e, t);
		
		x = table.get("x").tofloat();
		y = table.get("y").tofloat();
		rotation = table.get("rotation").tofloat();
	}
}
