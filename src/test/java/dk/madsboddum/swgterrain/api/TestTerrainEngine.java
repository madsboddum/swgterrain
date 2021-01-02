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
	
	private static TerrainEngine createEngine(String fileName) throws URISyntaxException {
		URL resource = TestTerrainEngine.class.getClassLoader().getResource(fileName);
		assert resource != null;
		URI uri = resource.toURI();
		
		return new TerrainEngine(new File(uri));
	}
	
	@Nested
	class HeightCoordinateReading {
		
		private TerrainEngine engine;
		
		@BeforeEach
		public void setup() throws URISyntaxException {
			engine = createEngine("endor.trn");
		}
		
		@Test
		public void testCanReadHeightCoordinateAtEndorSmugglerOutpostBetweenHilltops() {
			double actual = engine.getHeight(-935, 1555);
			double expected = 76.00;
			
			assertEquals(expected, actual, 0.01, "Unexpected height coordinate");
		}
		
		@Test
		public void testCanReadHeightAtEndorSmugglerOutpostSmallHilltop() {
			double actual = engine.getHeight(-1010, 1525);
			double expected = 74.69;
			
			assertEquals(expected, actual, 0.01, "Unexpected height coordinate");
		}

	}
	
	@Nested
	class WaterDetection {
		
		private TerrainEngine engine;
		
		@BeforeEach
		public void setup() throws URISyntaxException {
			engine = createEngine("corellia.trn");
		}
		
		@Test
		public void testCanDetectWaterPositive() {
			boolean water = engine.isWater(6700, 2700);
			
			assertTrue(water, "The ocean on Corellia should be considered water");
		}
		
		@Test
		public void testCanDetectWaterNegative() {
			boolean water = engine.isWater(-6600, 2100);
			
			assertFalse(water, "The land on Corellia should not be considered water");
		}
	
	}
	
	@Nested
	class SupportsTerrains {
		
		@ParameterizedTest(name = "{0}")
		@ValueSource(strings = {
				"corellia.trn",
				"dantooine.trn",	// Dantooine has some odd empty nodes that require special handling
				"endor.trn",
				"tatooine.trn",
		})
		public void load(String fileName) throws URISyntaxException {
			TerrainEngine engine = createEngine(fileName);
			
			// Ensure that the terrain's actually being loaded, even if this logic is later moved out of the constructor of TerrainEngine
			engine.getHeight(0, 0);
		}
	}
}
