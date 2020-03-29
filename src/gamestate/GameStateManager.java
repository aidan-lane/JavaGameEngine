package gamestate;

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
	 * @param stateName Name of state to be referenced by other states
	 * @param state, new state object
	 */
	public void addState(String stateName, GameState state) {
		if (states.containsKey(stateName))
			throw new RuntimeException("State already exists");
		
		states.put(stateName, state);
	}
	
	/**
	 * @param state Name of state to switch to
	 * @throw RuntimeException if state does not exist
	 */
	public void setState(String stateName) {
		if (!states.containsKey(stateName))
			throw new RuntimeException("State does not exist");
		
		if (currentState != null)
			states.get(currentState).exit();
		
		this.currentState = stateName;
		states.get(currentState).init();
	}
	
	public void update(double delta) {
		if (states.containsKey(currentState))
			states.get(currentState).update(delta);
	}
	
	public void render() {
		if (states.containsKey(currentState))
			states.get(currentState).render();
	}
	
	public void input() {
		if (states.containsKey(this.currentState))
			states.get(currentState).input();
	}
}
