package system;

import java.util.ArrayList;

import component.Component;

public abstract class System {

	protected ArrayList<Component> components = new ArrayList<>();
	
	public void add(Component c) {
		this.components.add(c);
	}
	
	public abstract void update(double delta);
	public abstract void render();
}
