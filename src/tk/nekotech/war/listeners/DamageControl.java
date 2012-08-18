package tk.nekotech.war.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import tk.nekotech.war.War;

public class DamageControl extends MasterClass implements Listener {

	public DamageControl(War war) {
		super(war);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		war.getPlayer(event.getEntity().getName()).addDeath();
		if (event.getEntity().getLastDamageCause() instanceof Player) {
			Player killer = (Player) event.getEntity().getLastDamageCause();
			war.getPlayer(killer.getName()).addKill();
		}
	}

}
