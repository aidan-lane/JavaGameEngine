package me.gamestate;

public abstract class GameState {

	public abstract void init();
	public abstract void update(double delta);
	public abstract void render();
}
