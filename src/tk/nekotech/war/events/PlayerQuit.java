package tk.nekotech.war.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import tk.nekotech.war.War;

public class PlayerQuit implements Listener {
	private War war;
	
	public PlayerQuit(War war) {
		this.war = war;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void omgTagging(PlayerQuitEvent event) {
		war.on--;
		war.online.remove(event.getPlayer().getName());
		if (war.red.contains(event.getPlayer().getName())) war.red.remove(event.getPlayer().getName());
		if (war.blu.contains(event.getPlayer().getName())) war.blu.remove(event.getPlayer().getName());
		if (war.pyro.contains(event.getPlayer().getName())) war.pyro.remove(event.getPlayer().getName());
		event.setQuitMessage(ChatColor.YELLOW + event.getPlayer().getName() + " chickened out.");
	}

}
