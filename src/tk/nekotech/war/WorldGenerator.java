package tk.nekotech.war;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class WorldGenerator extends ChunkGenerator {
	@SuppressWarnings("unused")
	private War war;
	
	public WorldGenerator(War war) {
		this.war = war;
	}
	
	public List<BlockPopulator> getDefaultPopulators(World world) {
		return new ArrayList<BlockPopulator>();
	}
	
	public Location getFixedSpawnLocation(World world, Random random) {
		return new Location(world, 0, 20, 0);
	}
	
	private int coords(int x, int y, int z) {
		return (x * 16 + z) * 128 + y;
	}

	public byte[] generate(World world, Random rand, int chunkx, int chunkz) {
		byte[] blocks = new byte[32768];
		int x, y, z;
		for (x = 0; x < 16; ++x) {
			for (z = 0; z < 16; ++z) {
				blocks[coords(x, 0, z)] = (byte) Material.AIR.getId();
				for (y = 1; y < 16; ++y) {
					blocks[coords(x, y, z)] = (byte) Material.AIR.getId();
				}
				blocks[coords(x, 16, z)] = (byte) Material.AIR.getId();
			}
		}
		return blocks;
		/*for (x = 0; x < 16; ++x) {
			for (z = 0; z < 16; ++z) {
				blocks[coords(x, 0, z)] = (byte) Material.BEDROCK.getId();
				for (y = 1; y < 16; ++y) {
					blocks[coords(x, y, z)] = (byte) Material.DIRT.getId();
				}
				blocks[coords(x, 16, z)] = (byte) Material.GRASS.getId();
			}
		}*/
	}

}