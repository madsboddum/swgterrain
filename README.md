# The swgterrain project
Reads information from Star Wars Galaxies .trn files.

Example usage:

```java
TerrainEngineFactory engineFactory = new TerrainEngineFactory();
File trnFile = new File("tatooine.trn");
TerrainEngine engine = engineFactory.create(trnFile);
double x = 3600;
double z = -2400;
double height = engine.getHeight(x, z);
// Use the height at (x,z) for something
```