package gamestate;

import map.Map;

public class Game extends GameState {
	
	public Game(GameStateManager gsm) {
		this.gsm = gsm;
	}

	public void init() {
		Map map = new Map(10, 10, 10);
		map.generateLayout();
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
