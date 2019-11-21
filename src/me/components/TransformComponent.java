package me.components;

public class TransformComponent extends Component {
	
	private float x, y, rotation;
	
	TransformComponent(float x, float y, float rot) {
		this.x = x;
		this.y = y;
		this.rotation = rot;
	}
}
