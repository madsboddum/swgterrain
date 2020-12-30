package dk.madsboddum.swgterrain.api;

import java.io.File;

public class TerrainEngineFactory {
	
	/**
	 * Creates a {@link TerrainEngine}
	 * @param terrainFile .trn file to create an engine based on
	 * @return created engine
	 */
	public TerrainEngine create(File terrainFile) {
		return new TerrainEngine(terrainFile);
	}
}
