package gamestate;

import map.StartingRoom;

public class Game extends GameState {
	
	StartingRoom room = new StartingRoom("res/rooms/StartingRoom.txt");
	
	public Game(GameStateManager gsm) {
		this.gsm = gsm;
	}

	public void init() {
		
	}

	public void exit() {

	}

	public void update(double delta) {

	}

	public void render() {
		room.render();
	}

	public void input() {
	
	}
}
