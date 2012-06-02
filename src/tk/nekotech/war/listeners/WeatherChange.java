package tk.nekotech.war.listeners;

import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import tk.nekotech.war.War;
import tk.nekotech.war.runnables.Weather;

public class WeatherChange implements Listener {
	private War war;
	
	public WeatherChange(War war) {
		this.war = war;
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		Random random = new Random();
		int randINT = random.nextInt(25);
		if (randINT == 0) {
			event.getWorld().setStorm(true);
			event.getWorld().setThundering(true);
			war.getServer().getScheduler().scheduleSyncDelayedTask(war, new Weather(event.getWorld()), 2400L);
		} else {
			event.getWorld().setStorm(false);
		}
	}

}
