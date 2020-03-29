package entity;

import java.awt.Rectangle;

public class GameObject {

	protected String name;
	
	protected float x;
	protected float y;
	
	protected int width;
	protected int height;
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 
				(int) x + width, (int) y + height);
	}
}
