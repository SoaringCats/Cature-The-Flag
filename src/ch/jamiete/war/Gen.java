package ch.jamiete.war;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class Gen extends ChunkGenerator {
    @SuppressWarnings("unused")
    private final War war;

    public Gen(final War war) {
        this.war = war;
    }

    private int coords(final int x, final int y, final int z) {
        return (x * 16 + z) * 128 + y;
    }

    @Override
    public byte[] generate(final World world, final Random rand, final int chunkx, final int chunkz) {
        final byte[] blocks = new byte[32768];
        int x, y, z;
        for (x = 0; x < 16; ++x) {
            for (z = 0; z < 16; ++z) {
                blocks[this.coords(x, 0, z)] = (byte) Material.AIR.getId();
                for (y = 1; y < 16; ++y) {
                    blocks[this.coords(x, y, z)] = (byte) Material.AIR.getId();
                }
                blocks[this.coords(x, 16, z)] = (byte) Material.AIR.getId();
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

    @Override
    public List<BlockPopulator> getDefaultPopulators(final World world) {
        return new ArrayList<BlockPopulator>();
    }

    @Override
    public Location getFixedSpawnLocation(final World world, final Random random) {
        return new Location(world, 0, 20, 0);
    }

}
