package me.assetmanagers;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager<V> {

	private Map<String, V> resources;
	
	public ResourceManager() {
		this.resources = new HashMap<>();
	}
	
	public V getResource(String filename) {
		if (this.resources.containsKey(filename))
			return this.resources.get(filename);
		return null;
	}
}
