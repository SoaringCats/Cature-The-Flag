package ch.jamiete.war.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.WeatherChangeEvent;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;

public class WeatherChange extends MasterListener {

    public WeatherChange(final War war) {
        super(war);
    }

    @EventHandler
    public void onWeatherChange(final WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            event.getWorld().setWeatherDuration(0);
        }
    }

}
