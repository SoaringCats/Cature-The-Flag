package ch.jamiete.war.listeners;

import java.util.Random;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import ch.jamiete.war.War;
import ch.jamiete.war.runnables.Weather;

public class WeatherChange implements Listener {
    private final War war;
    private boolean changing = false;

    public WeatherChange(final War war) {
        this.war = war;
    }

    @EventHandler
    public void onWeatherChange(final WeatherChangeEvent event) {
        if (this.changing) {
            return;
        }
        final Random random = new Random();
        final int randINT = random.nextInt(25);
        if (randINT == 0) {
            this.changing = true;
            event.getWorld().setStorm(true);
            event.getWorld().setThundering(true);
            this.war.getServer().getScheduler().scheduleSyncDelayedTask(this.war, new Weather(event.getWorld()), 2400L);
        } else {
            this.changing = true;
            event.getWorld().setStorm(false);
        }
        this.changing = false;
    }

}
