package me.gamestate;

import java.util.HashMap;
import java.util.Map;

public class GameStateManager {

	private Map<String, GameState> states;
	private String currentState;
	
	/**
	 * GameStateManager controls the current state of the game
	 */
	public GameStateManager() {
		states = new HashMap<>();
	}
	
	/**
	 * @param path Path to JavaScript state file
	 * @param name Name of state to be referenced by other states
	 */
	public void addState(GameState state, String name) {
		if (states.containsKey(name))
			throw new RuntimeException("State already exists");
		states.put(name, state);
	}
	
	/**
	 * @param state Name of state to switch to
	 */
	public void setState(String state) {
		if (!states.containsKey(state))
			throw new RuntimeException("State does not exist");
		
		if (currentState != null)
			states.get(currentState).exit();
		this.currentState = state;
		states.get(currentState).init();
	}
	
	public void update(double delta) {
		if (states.size() > 0 && states.containsKey(currentState))
			states.get(currentState).update(delta);
	}
	
	public void render() {
		if (states.size() > 0 && states.containsKey(currentState))
			states.get(currentState).render();
	}
}
