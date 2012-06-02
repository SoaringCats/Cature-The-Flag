package tk.nekotech.war.runnables;

import org.bukkit.World;

public class Weather implements Runnable {
	private World world;
	
	public Weather(World world) {
		this.world = world;
	}

	@Override
	public void run() {
		world.setStorm(false);
		world.setThundering(false);
	}

}
