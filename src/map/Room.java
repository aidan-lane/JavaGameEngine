package map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Room {
	
	private int width;
	private int height;
	protected int[][] tileData;
	
	public Room(String path) {
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.width = scanner.nextInt();
		this.height = scanner.nextInt();
		this.tileData = new int[width][height];
		
		int i = 0;
		while(scanner.hasNextInt()) {
			tileData[i % width][i / width] = scanner.nextInt();
			i++;
		}
	}
	
	public void render() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int tileType = tileData[i][j];
				Map.textureMap.renderTile(tileType, i*64, j*64, 64, 64);
			}
		}
	}
}
