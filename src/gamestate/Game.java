package gamestate;

import map.StartingRoom;

public class Game extends GameState {
	
	public Game(GameStateManager gsm) {
		this.gsm = gsm;
	}

	public void init() {
		StartingRoom room = new StartingRoom("res/rooms/StartingRoom.txt");
	}

	public void exit() {

	}

	public void update(double delta) {

	}

	public void render() {
	
	}

	public void input() {
	
	}
}
