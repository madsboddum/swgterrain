package dk.madsboddum.swgterrain.api;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class TestTerrainEngine {
	
	private TerrainEngine engine;
	
	private static TerrainEngine createEngine(String fileName) throws URISyntaxException {
		URL resource = TestTerrainEngine.class.getClassLoader().getResource(fileName);
		assert resource != null;
		URI uri = resource.toURI();
		
		return new TerrainEngine(new File(uri));
	}
	
	@BeforeEach
	public void setup() throws URISyntaxException {
		engine = createEngine("corellia.trn");
	}
	
	@Nested
	class HeightCoordinateReading {
		
		@Test
		public void testCanReadHeightCoordinate() {
			double actual = engine.getHeight(1234, 3214);
			double expected = 141.53;
			
			assertEquals(expected, actual, 0.01, "Unexpected height coordinate");
		}
	}
	
	@Nested
	class WaterDetection {
		
		@Test
		public void testCanDetectWaterPositive() {
			boolean water = engine.isWater(6700, 2700);
			
			assertTrue(water, "The ocean on Corellia should be considered water");
		}
		
		@Test
		public void testCanDetectWaterNegative() {
			boolean water = engine.isWater(-6600, 2100);
			
			assertFalse(water, "The mountains on Corellia should not be considered water");
		}
	
	}
	
	@Nested
	class SupportsTerrains {
		
		@ParameterizedTest(name = "{0}")
		@ValueSource(strings = {
				"corellia.trn",
				"dantooine.trn",	// Dantooine has some odd empty nodes that require special handling
		})
		public void load(String fileName) throws URISyntaxException {
			TerrainEngine engine = createEngine(fileName);
			
			// Ensure that the terrain's actually being loaded, even if this logic is later moved out of the constructor of TerrainEngine
			engine.getHeight(0, 0);
		}
	}
}
