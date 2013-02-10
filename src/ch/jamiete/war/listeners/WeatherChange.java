package ch.jamiete.war.listeners;

import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import ch.jamiete.war.War;
import ch.jamiete.war.runnables.Weather;


public class WeatherChange implements Listener {
	private War war;
	private boolean changing = false;
	
	public WeatherChange(War war) {
		this.war = war;
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		if (changing) {
			return;
		}
		Random random = new Random();
		int randINT = random.nextInt(25);
		if (randINT == 0) {
			changing = true;
			event.getWorld().setStorm(true);
			event.getWorld().setThundering(true);
			war.getServer().getScheduler().scheduleSyncDelayedTask(war, new Weather(event.getWorld()), 2400L);
		} else {
			changing = true;
			event.getWorld().setStorm(false);
		}
		changing = false;
	}

}
