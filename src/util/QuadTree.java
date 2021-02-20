package util;

import java.awt.Rectangle;
import java.util.ArrayList;

import entity.GameObject;

public class QuadTree {

	public static final int MAX_OBJECTS = 10;
	public static final int MAX_LEVELS = 5;
	
	private int level;
	private Rectangle bounds;
	
	private ArrayList<GameObject> objects;
	private QuadTree[] nodes;
	
	public QuadTree(int level, Rectangle bounds) {
		this.level = level;
		this.bounds = bounds;
		nodes = new QuadTree[4];
		objects = new ArrayList<>();
	}
	
	/**
	 * Recursively removes all objects in each quadtree, and resets tree to root
	 */
	public void clear() {
		objects.clear();
		
		for (int i = 0; i < nodes.length; i++) {
			if(nodes[i] != null) {
				nodes[i].clear();
				nodes[i] = null;
			}
		}
	}
	
	/**
	 * Splits root into 4 quadtrees, each taking a quarter of the previous space
	 */
	private void split() {
		int subWidth = this.bounds.width;
		int subHeight = this.bounds.height;
		int x = this.bounds.x;
		int y = this.bounds.y;
		int newLevel = this.level + 1;
		
		nodes[0] = new QuadTree(newLevel, new Rectangle(x, y, subWidth, subHeight));
		nodes[1] = new QuadTree(newLevel, new Rectangle(x + subWidth, y, subWidth, subHeight));
		nodes[2] = new QuadTree(newLevel, new Rectangle(x, y + subHeight, subWidth, subHeight));
		nodes[3] = new QuadTree(newLevel, new Rectangle(x + subWidth, y + subHeight, subWidth, subHeight));
	}
	
	private int getIndex(Rectangle pRect) {
		int index = -1;
		int verticalMidpoint = this.bounds.x + (bounds.width / 2);
		int horizontalMidpoint = this.bounds.y = (bounds.height / 2);
		
		boolean topQuadrant = (pRect.y < horizontalMidpoint && pRect.y + pRect.height < horizontalMidpoint);
		boolean bottomQuadrant = (pRect.y > horizontalMidpoint);
		
		if(pRect.x < verticalMidpoint && pRect.x + pRect.width < verticalMidpoint) {
			if(topQuadrant) index = 1;
			else if(bottomQuadrant) index = 2;
		}
		else if(pRect.x > verticalMidpoint) {
			if(topQuadrant) index = 0;
			else if(bottomQuadrant) index = 3;
		}
		
		return index;
	}
	
	/**
	 * Inserts a game object into the quad tree
	 * @param o GameObject to be inserted
	 */
	public void insert(GameObject o) {
		if(this.nodes[0] != null) {
			int index = getIndex(o.getBounds());
			
			if(index != -1) {
				nodes[index].insert(o);
				return;
			}
		}
		
		this.objects.add(o);
		
		if(objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
			if(nodes[0] == null) {
				this.split();
			}
			
			int i = 0;
			while(i < objects.size()) {
				int index = getIndex(objects.get(i).getBounds());
				if(index != -1)
					nodes[index].insert(objects.remove(i));
				else
					i++;
			}
		}
	}
	
	/**
	 * Get's objects within given object's quad
	 * @param returnObjects List of game objects
	 * @param o Game object to be centered on
	 * @return ArrayList<GameObject> list of new objects in quad
	 */
	public ArrayList<GameObject> retrieve(ArrayList<GameObject> returnObjects, GameObject o) {
		int index = getIndex(o.getBounds());
		if(index != -1 && nodes[0] != null) {
			nodes[index].retrieve(returnObjects, o);
		}
		
		returnObjects.addAll(objects);
		return returnObjects;
	}
}
