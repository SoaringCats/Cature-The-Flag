package ch.jamiete.war.runnables;

import org.bukkit.World;

public class Weather implements Runnable {
    private final World world;

    public Weather(final World world) {
        this.world = world;
    }

    @Override
    public void run() {
        this.world.setStorm(false);
        this.world.setThundering(false);
    }

}
