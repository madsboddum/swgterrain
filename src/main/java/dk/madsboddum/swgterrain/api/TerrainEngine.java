package dk.madsboddum.swgterrain.api;

import dk.madsboddum.swgterrain.internal.IffFile;
import dk.madsboddum.swgterrain.internal.TerrainVisitor;

import java.io.File;

public class TerrainEngine {
	
	private final TerrainVisitor visitor;
	
	TerrainEngine(File terrainFile) {
		visitor = new TerrainVisitor();
		IffFile.readFile(terrainFile, visitor);
	}
	
	/**
	 * Returns the height at the specified location.
	 * @param x coordinate in the terrain
	 * @param z coordinate in the terrain
	 * @return height coordinate in the terrain, based on given {@code x} and {@code z} parameters
	 */
	public double getHeight(double x, double z) {
		return visitor.getHeight((float) x, (float) z);
	}
	
	/**
	 * Determines if the specified point in the terrain is submerged in water.
	 * @param x coordinate in the terrain
	 * @param z coordinate in the terrain
	 * @return {@code true} if underwater and {@code false} if not
	 */
	public boolean isWater(double x, double z) {
		return visitor.isWater((float) x, (float) z);
	}
}
