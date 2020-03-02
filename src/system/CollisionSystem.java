package system;

import java.awt.Rectangle;
import java.util.ArrayList;

import component.CollisionComponent;
import component.Component;
import main.Main;
import util.QuadTree;

public class CollisionSystem extends System {

	QuadTree quadTree = new QuadTree(0, new Rectangle(0, 0, Main.VIRTUAL_WIDTH, Main.VIRTUAL_HEIGHT));
	
	public void update(double delta) {
		// update quad tree
		quadTree.clear();
		for (Component c : this.components) {
			quadTree.insert((CollisionComponent) c); // cast to collision component
		}
		
		ArrayList<CollisionComponent> nodeNeighbors = new ArrayList<>();
		for (Component c : this.components) {
			quadTree.retrieve(nodeNeighbors, (CollisionComponent) c);
			
			for (CollisionComponent neighbor : nodeNeighbors) {
				// handle collisions
			}
		}
	}

	public void render() {
		
	}
}
