package tk.nekotech.war.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class ExplosionPrime implements Listener {
	
	@EventHandler
	public void onExplosionPrime(ExplosionPrimeEvent event) {
		event.setRadius(0F);
	}

}
