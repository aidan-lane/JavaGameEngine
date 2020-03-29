package map;

import java.util.ArrayList;

import gfx.TextureAtlas;

/**
 * Map contains data about room layouts in the randomized dungeon 
 */
public class Map {

	public static TextureAtlas textureMap = new TextureAtlas("res/textures/atlas.png", 16, 16);;
	
	private int roomCount;
	private ArrayList<Room> rooms = new ArrayList<>();
	
	
	public Map(int mapWidth, int mapHeight, int roomCount) {
		this.roomCount = roomCount;
	}
	
	public void generateRooms() {
		for (int i = 0; i < roomCount; i++) {
			// todo
		}
	}
}
