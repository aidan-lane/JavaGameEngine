package component;

import java.awt.Rectangle;

import org.luaj.vm2.LuaValue;

import entity.Entity;

public class CollisionComponent extends Component {

	private Rectangle bounds;
	
	public CollisionComponent(Entity e, LuaValue table) {
		super(e, table);
	}
	
	public Rectangle getBounds() {
		return this.bounds;
	}
}
