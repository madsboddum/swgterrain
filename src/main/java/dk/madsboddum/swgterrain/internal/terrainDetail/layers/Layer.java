package dk.madsboddum.swgterrain.internal.terrainDetail.layers;

import dk.madsboddum.swgterrain.internal.TerrainVisitor;
import org.apache.mina.core.buffer.IoBuffer;

import java.nio.charset.Charset;

public abstract class Layer {
	
	private String name;
	protected LayerType type;
	private boolean enabled;
	
	public Layer() {
		type = LayerType.NONE;
	}
	
	public String getCustomName() {
		return name;
	}
	
	public LayerType getType() {
		return type;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void loadHeader(IoBuffer buffer) throws Exception {
		enabled = buffer.getInt() != 0;
		name = buffer.getString(Charset.forName("US-ASCII").newDecoder());
	}
	
	public abstract void loadData(IoBuffer buffer) throws Exception;

	public abstract float process(float x, float y, float transform_value, float base_value, TerrainVisitor ti);
}
