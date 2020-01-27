package component;

import org.luaj.vm2.LuaValue;

import entity.Entity;

public class MovementComponent extends Component {
	
	private float x, y, rotation;
	
	public MovementComponent(Entity e, LuaValue table) {
		super(e, table);
		
		x = table.get("x").tofloat();
		y = table.get("y").tofloat();
		rotation = table.get("rotation").tofloat();
	}
}
