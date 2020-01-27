package component;

import org.luaj.vm2.LuaValue;

import entity.Entity;

public class GraphicsComponent extends Component{

	public GraphicsComponent(Entity e, LuaValue table) {
		super(e, table);
	}
}
