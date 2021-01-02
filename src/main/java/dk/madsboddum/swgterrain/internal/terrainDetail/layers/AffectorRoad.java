package dk.madsboddum.swgterrain.internal.terrainDetail.layers;

import dk.madsboddum.swgterrain.internal.TerrainVisitor;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.mina.core.buffer.IoBuffer;

import java.util.ArrayList;
import java.util.Collection;

public class AffectorRoad extends HeightLayer {
	
	private Collection<Vector2D> positions;
	private float var2;
	private float var3;
	private int featheringType;
	private float featheringAmount;
	private int var6;
	private float var7;
	
	public AffectorRoad() {
		type = LayerType.AROA;
		positions = new ArrayList<>();
	}
	
	@Override
	public void loadData(IoBuffer buffer) throws Exception {
		int positionCount = buffer.getInt();
		
		for (int i = 0; i < positionCount; i++) {
			float x = buffer.getFloat();
			float z = buffer.getFloat();
			Vector2D vector = new Vector2D(x, z);
			
			positions.add(vector);
		}
		
		var2 = buffer.getFloat();	// TODO min val of some kind?
		var3 = buffer.getFloat();	// TODO max val of some kind?
		featheringType = buffer.getInt();
		featheringAmount = buffer.getFloat();
		var6 = buffer.getInt();
		var7 = buffer.getFloat();
	}
	
	@Override
	public float process(float x, float y, float transform_value, float base_value, TerrainVisitor ti) {
		return base_value;	// TODO where's this defined in swgemu core3?
	}
}
