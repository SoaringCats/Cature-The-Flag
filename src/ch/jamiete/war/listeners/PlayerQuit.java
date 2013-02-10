package ch.jamiete.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import ch.jamiete.war.War;


public class PlayerQuit implements Listener {
	private War war;
	
	public PlayerQuit(War war) {
		this.war = war;
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		war.quickplayer.clearAttachments(event.getPlayer());
		war.quickplayer.playerLeave(event.getPlayer());
		event.setQuitMessage(war.getMessage() + ChatColor.RED + "- " + ChatColor.BOLD + event.getPlayer().getName());
	}

}
