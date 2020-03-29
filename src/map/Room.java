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
		
		int i = 0;
		while(scanner.hasNextInt()) {
			int c = scanner.nextInt();
			
			if (i == 0) {
				width = c;
			}
			else if (i == 1) {
				height = c;
				this.tileData = new int[width][height];
			}
			else {
				tileData[(i - 2) % width][(i - 2) / width] = c;
			}
			
			i++;
		}
	}
}
