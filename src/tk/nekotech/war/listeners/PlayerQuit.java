package tk.nekotech.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import tk.nekotech.war.War;

public class PlayerQuit implements Listener {
	private War war;
	
	public PlayerQuit(War war) {
		this.war = war;
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		war.afk.remove(event.getPlayer());
		if (war.red.contains(event.getPlayer().getName())) war.red.remove(event.getPlayer().getName());
		if (war.blu.contains(event.getPlayer().getName())) war.blu.remove(event.getPlayer().getName());
		if (war.pyro.contains(event.getPlayer().getName())) war.pyro.remove(event.getPlayer().getName());
		event.setQuitMessage(war.getMessage() + ChatColor.RED + "- " + ChatColor.BOLD + event.getPlayer().getName());
	}

}
