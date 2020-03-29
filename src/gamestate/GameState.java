package gamestate;

public abstract class GameState {

	protected GameStateManager gsm;
	
	public abstract void init();
	public abstract void exit();
	
	public abstract void update(double delta);
	public abstract void render();
	public abstract void input();
}
