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
	public void omgTagging(PlayerQuitEvent e) {
		war.on--;
		war.online.remove(e.getPlayer().getName());
		if (war.red.contains(e.getPlayer().getName())) war.red.remove(e.getPlayer().getName());
		if (war.blu.contains(e.getPlayer().getName())) war.blu.remove(e.getPlayer().getName());
		if (war.pyro.contains(e.getPlayer().getName())) war.pyro.remove(e.getPlayer().getName());
		e.setQuitMessage(ChatColor.YELLOW + e.getPlayer().getName() + " chickened out.");
	}

}
