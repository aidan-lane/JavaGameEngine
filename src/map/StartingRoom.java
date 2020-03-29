package map;

public class StartingRoom extends Room {
	
	public StartingRoom(String path) {
		super(path);
		
		for(int i=0;i<this.tileData[0].length;i++) {
			for(int j=0;j<this.tileData.length;j++) {
				System.out.print(this.tileData[j][i] + " ");
			}
			System.out.println();
		}
		
	}
}
