package dk.madsboddum.swgterrain.api;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.*;

public class TestTerrainEngine {
	
	private TerrainEngine engine;
	
	@Before
	public void setup() throws URISyntaxException {
		URL resource = TestTerrainEngine.class.getClassLoader().getResource("corellia.trn");	// Corellia has both land and water. Useful.
		assert resource != null;
		URI uri = resource.toURI();
		
		engine = new TerrainEngine(new File(uri));
	}
	
	@Test
	public void testCanReadHeightCoordinate() {
		double actual = engine.getHeight(1234, 3214);
		double expected = 141.53;
		
		assertEquals("Unexpected height coordinate", expected, actual, 0.01);
	}
	
	@Test
	public void testCanDetectWaterPositive() {
		boolean water = engine.isWater(6700, 2700);
		
		assertTrue("The ocean on Corellia should be considered water", water);
	}
	
	@Test
	public void testCanDetectWaterNegative() {
		boolean water = engine.isWater(-6600, 2100);
		
		assertFalse("The mountains on Corellia should not be considered water", water);
	}
}
