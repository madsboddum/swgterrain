package dk.madsboddum.swgterrain.internal.terrainDetail;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class BitmapGroup {
	
	private Vector<BitmapFamily> bitmapFamilies;
	private Map<Integer, TargaBitmap> bitmaps;
	
	public BitmapGroup() {
		bitmapFamilies = new Vector<BitmapFamily>();
		bitmaps = new HashMap<Integer, TargaBitmap>();
	}

	public Vector<BitmapFamily> getBitmapFamilies() {
		return bitmapFamilies;
	}

	public Map<Integer, TargaBitmap> getBitmaps() {
		return bitmaps;
	}
	
	public void addBitmapFamily(BitmapFamily bitmapFamily) {
		Path path = Paths.get("clientdata/" + bitmapFamily.getFile());
		boolean exists = Files.exists(path);
		
		if(exists) {
			bitmapFamilies.add(bitmapFamily);
			bitmaps.put(bitmapFamily.getVar1(), bitmapFamily.getBitmap());
		}
	}

}
