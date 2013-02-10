package ch.jamiete.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import ch.jamiete.war.War;


public class PlayerKick implements Listener {
	private War war;
	
	public PlayerKick(War war) {
		this.war = war;
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
		event.setLeaveMessage(null);
		war.quickplayer.clearAttachments(event.getPlayer());
		war.quickplayer.playerLeave(event.getPlayer());
		if (event.getReason().equals("Kicked by administrator.")) {
			event.setReason(ChatColor.AQUA + "Kicked!");
		} else {
			event.setReason(ChatColor.AQUA + event.getReason());
		}
		for (Player player : war.getServer().getOnlinePlayers()) {
			if (player.hasPermission("jtwar.admin")) {
				war.sendMessage(player, ChatColor.RED + "- " + ChatColor.BOLD + event.getPlayer().getName() + ChatColor.RESET + ChatColor.RED + ": " + event.getReason());
			} else {
				war.sendMessage(player, ChatColor.RED + "- " + ChatColor.BOLD + event.getPlayer().getName());
			}
		}
		war.getLogger().info(event.getPlayer().getName() + " kicked from server for " + event.getReason());
	}

}
