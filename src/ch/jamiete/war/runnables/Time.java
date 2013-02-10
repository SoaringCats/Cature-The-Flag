package ch.jamiete.war.runnables;

import org.bukkit.World;
import ch.jamiete.war.War;

public class Time implements Runnable {
    private final War war;

    public Time(final War war) {
        this.war = war;
    }

    @Override
    public void run() {
        for (final World world : this.war.getServer().getWorlds()) {
            world.setTime(14000);
        }
    }

}
