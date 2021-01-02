package dk.madsboddum.swgterrain.internal.terrainDetail.layers;

import dk.madsboddum.swgterrain.internal.TerrainVisitor;
import dk.madsboddum.swgterrain.internal.terrainDetail.FractalFamily;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestFilterFractal {
	@Nested
	class Process {
		@Test
		public void testNoiseResultOutsideBoundary() {
			FilterFractal filter = new FilterFractal();
			filter.fractal_id = 25;	// This is used for TerrainVisitor#getFractal()
			filter.min = 0.0f;
			filter.max = 0.075f;
			filter.step = 1;
			filter.feather_type = 1;
			filter.feather_amount = 0.5f;
			
			float x = -935f;
			float z = 1555f;
			float transform_value = 1.0f;
			float base_value = 85.34916f;
			FilterRectangle rectangle = new FilterRectangle();
			rectangle.minX = Float.MAX_VALUE;
			rectangle.minZ = Float.MAX_VALUE;
			rectangle.maxX = -Float.MAX_VALUE;
			rectangle.maxZ = -Float.MAX_VALUE;
			
			FractalFamily family = new FractalFamily();
			family.setFractal_id(25);
			family.setFractal_label("FilterFractal 9_1");
			family.setSeed(9);
			family.setUse_bias(0);
			family.setBias(0.553f);
			family.setUse_gain(0);
			family.setGain(0.553f);
			family.setOctaves(2);
			family.setOctaves_arg(4.0f);
			family.setAmplitude(0.255f);
			family.setFreq_x(0.002f);
			family.setFreq_z(0.002f);
			family.setOffset_x(0.0f);
			family.setOffset_y(0.0f);	// TODO weird that this method is named y but is z everywhere else
			family.setCombination_type(3);
			family.setOffset(0.7968128f);
			
			TerrainVisitor visitor = mock(TerrainVisitor.class);
			when(visitor.getFractal(25)).thenReturn(family);
			
			float processed = filter.process(x, z, transform_value, base_value, visitor, rectangle);
			
			assertEquals(0, processed, "A noise result generated outside the min/max values should cause a return value of 0");
		}
	}
}
