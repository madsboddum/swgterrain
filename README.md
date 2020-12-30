# The swgterrain project
Reads information from Star Wars Galaxies .trn files.

Example usage:

```java
TerrainEngine engine = TerrainEngineFactory.create(new File("tatooine.trn"));
double x = 3600;
double z = -2400;
double height = engine.getHeight(x, z);
// Use the height at (x,z) for something
```