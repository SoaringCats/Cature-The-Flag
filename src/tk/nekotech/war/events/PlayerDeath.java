package tk.nekotech.war.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import tk.nekotech.war.War;

public class PlayerDeath implements Listener {
	private War war;
	
	public PlayerDeath(War war) {
		this.war = war;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		war.getLogger().info(event.getEntity().getName() + " died: " + event.getDeathMessage());
		war.smitePlayer(event.getEntity());
		event.setDeathMessage(war.getMessage() + ChatColor.AQUA + " " + event.getDeathMessage());
	}

}
