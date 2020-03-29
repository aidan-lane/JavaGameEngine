package map;

import java.util.ArrayList;

/**
 * Map contains data about room layouts in the randomized dungeon 
 */
public class Map {

	// TextureAtlas textureMap = new TextureAtlas("res/textures/map.png", 32, 32);
	
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
