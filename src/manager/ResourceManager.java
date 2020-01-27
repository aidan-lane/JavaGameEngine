package manager;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager<V> {

	private Map<String, V> resources;
	
	public ResourceManager() {
		this.resources = new HashMap<>();
	}
	
	/**
	 * @param filename Path to file including directory path
	 * @return an object contained in the manager with path as key
	 */
	public V getResource(String filename) {
		if (this.resources.containsKey(filename))
			return this.resources.get(filename);
		return null;
	}
}
