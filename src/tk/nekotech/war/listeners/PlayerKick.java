package tk.nekotech.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import tk.nekotech.war.War;

public class PlayerKick implements Listener {
	private War war;
	
	public PlayerKick(War war) {
		this.war = war;
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
		war.afk.remove(event.getPlayer());
		event.setLeaveMessage(null);
		if (war.red.contains(event.getPlayer().getName())) war.red.remove(event.getPlayer().getName());
		if (war.blu.contains(event.getPlayer().getName())) war.blu.remove(event.getPlayer().getName());
		if (war.pyro.contains(event.getPlayer().getName())) war.pyro.remove(event.getPlayer().getName());
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
	}

}
