package ch.jamiete.war.listeners;

import java.util.Date;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import ch.jamiete.war.War;


public class PlayerChat implements Listener {
	private War war;
	
	public PlayerChat(War war) {
		this.war = war;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat(PlayerChatEvent event) {
		war.afk.put(event.getPlayer(), (new Date()).getTime());
		event.setFormat(war.getMessage() + "%s > %s");
		if (event.getMessage().startsWith(".")) {
			war.teamhelpers.teamMessage(war.teamhelpers.teamName(event.getPlayer()), "(TEAM) " + ChatColor.WHITE + "<" + event.getPlayer().getDisplayName() + "> " + event.getMessage());
			event.setCancelled(true);
		}
	}

}
