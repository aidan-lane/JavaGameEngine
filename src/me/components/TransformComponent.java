package me.components;

import me.entity.Entity;

public class TransformComponent extends Component {
	
	private float x, y, rotation;
	
	public TransformComponent(Entity e, float x, float y, float rot) {
		super(e);
		this.x = x;
		this.y = y;
		this.rotation = rot;
	}
}
