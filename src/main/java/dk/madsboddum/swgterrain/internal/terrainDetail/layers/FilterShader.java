package dk.madsboddum.swgterrain.internal.terrainDetail.layers;

import dk.madsboddum.swgterrain.internal.TerrainVisitor;
import org.apache.mina.core.buffer.IoBuffer;

public class FilterShader extends FilterLayer {

	@Override
	public float process(float x, float y, float transform_value,
			float base_value, TerrainVisitor ti, FilterRectangle rectangle) {
		// TODO Auto-generated method stub
		return base_value;
	}

	@Override
	public void loadData(IoBuffer buffer) throws Exception {
		buffer.getInt(); // shader id
	}

	@Override
	public float process(float x, float y, float transform_value,
			float base_value, TerrainVisitor ti) {
		// TODO Auto-generated method stub
		return base_value;
	}

}
