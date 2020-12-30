package dk.madsboddum.swgterrain.api;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TerrainEngineFactory {
	
	private static final Map<File, TerrainEngine> engineCache = Collections.synchronizedMap(new HashMap<>());
	
	/**
	 * Creates a {@link TerrainEngine}
	 * @param terrainFile .trn file to create an engine based on
	 * @return created engine
	 */
	public TerrainEngine create(File terrainFile) {
		synchronized (engineCache) {
			TerrainEngine engine = engineCache.get(terrainFile);
			
			if (engine == null) {
				engine = new TerrainEngine(terrainFile);
				engineCache.put(terrainFile, engine);
			}
			
			return engine;
		}
	}
}
