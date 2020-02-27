package util;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class QuadTree<T> {

	public static final int MAX_OBJECTS = 10;
	public static final int MAX_LEVELS = 5;
	
	private int level;
	private Rectangle bounds;
	
	private ArrayList<T> objects;
	private List<QuadTree<T>> nodes;
	
	public QuadTree(int level, Rectangle bounds) {
		this.level = level;
		this.bounds = bounds;
		nodes = new ArrayList<>();
		objects = new ArrayList<>();
	}
	
	/**
	 * Recursively removes all objects in each quadtree, and resets tree to root
	 */
	public void clear() {
		objects.clear();
		
		for (QuadTree<T> node : nodes) {
			node.clear();
			nodes.remove(node);
		}
	}
	
	/**
	 * Splits root into 4 quadtrees, each taking a quarter of the previous space
	 */
	private void split() {
		
	}
	
	public void add(T t) {
		
	}
}
