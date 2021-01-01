package dk.madsboddum.swgterrain.internal.terrainDetail.layers;

import org.apache.mina.core.buffer.IoBuffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAffectorHeightConstant {
	
	@Nested
	class Load {
		
		private AffectorHeightConstant constant;
		private final int transform_type = 3;
		private final float height_val = 324f;
		
		
		@BeforeEach
		public void setup() {
			constant = new AffectorHeightConstant();
			IoBuffer buffer = IoBuffer.allocate(Integer.BYTES + Float.BYTES);
			
			buffer.putInt(transform_type);
			buffer.putFloat(height_val);
			
			buffer.flip();
			
			constant.loadData(buffer);
		}
		
		@Test
		public void testTransformType() {
			assertEquals(transform_type, constant.transform_type);
		}
		
		@Test
		public void testHeightValue() {
			assertEquals(height_val, constant.height_val);
		}
	}
	
	@Nested
	class Process {
		
		@Test
		public void testReturnsBaseHeightWithTransformValue0() {
			AffectorHeightConstant constant = new AffectorHeightConstant();
			constant.transform_type = 1;
			float x = 0;
			float z = 0;
			float transform_value = 0;
			float base_height = 1234;
			
			float processed = constant.process(x, z, transform_value, base_height, null);
			
			assertEquals(base_height, processed);
		}
		
		@Test
		public void testTransformType1() {
			AffectorHeightConstant constant = new AffectorHeightConstant();
			constant.transform_type = 1;
			constant.height_val = 5;
			float x = 0;
			float z = 0;
			float transform_value = 10;
			float base_height = 20;
			
			float processed = constant.process(x, z, transform_value, base_height, null);
			
			assertEquals(70, processed);
		}
	
	}
}
