package manager;

import java.util.HashMap;
import java.util.Map;

/**
 * Generic class for resource managers
 * Handles caching and loading in new resources
 *
 * @param <V>: type of resouce being managers
 */
public abstract class ResourceManager<V> {

	private Map<String, V> resources;
	
	/**
	 * Intialize the resource map where the key is the file and
	 * the value is the resouce
	 */
	public ResourceManager() {
		this.resources = new HashMap<>();
	}
	
	/**
	 * Child managers 
	 * @param file File path to resouce
	 * @return V this manager's resource type
	 */
	protected abstract V loadResource(String file);
	
	/**
	 * Gets the resource at the given file path. If it doesn't
	 * exist in memory, load it into a cache
	 * @param file File path to resource
	 * @return V generic resource type
	 */
	public V getResource(String file) {
		// Return resource if it exists in map
		if (this.resources.containsKey(file)) {
			return this.resources.get(file);
		}
		
		// If it doesn't exist, try loading it and putting it in the map
		V resource = loadResource(file);
		this.resources.put(file, resource);
		return resource;
	}
}
