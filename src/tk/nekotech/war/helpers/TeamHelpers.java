package tk.nekotech.war.helpers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import tk.nekotech.war.War;

public class TeamHelpers {
	private War war;
	
	public TeamHelpers(War war) {
		this.war = war;
	}
	
	public int blu() {
		return war.blu.size();
	}
	
	public int red() {
		return war.red.size();
	}
	
	public void alertTeam(Player player, int team) {
		
		if (team == 0) {
			war.getServer().broadcastMessage(ChatColor.BLUE + player.getName() + " is now on blu team!");
		}
		
		if (team == 1) {
			war.getServer().broadcastMessage(ChatColor.RED + player.getName() + " is now on red team!");
		}
		
		war.getServer().broadcastMessage(ChatColor.GREEN + "There are now " + ChatColor.BLUE + blu() + " on blu" + ChatColor.GREEN + " and " + ChatColor.RED + red() + " on red");
		
	}
	
	public boolean onTeam(Player player) {
		
		if (war.blu.contains(player)) {
			
			return true;
			
		} else if (war.red.contains(player)) {
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
	public void clearTeams(Player player) {
		
		if (war.blu.contains(player)) war.blu.remove(player);
		if (war.red.contains(player)) war.red.remove(player);
		if (war.pyro.contains(player)) war.pyro.remove(player);
		if (war.monster.contains(player)) war.monster.remove(player);
		
		war.color.setColor(ColorChoice.GRAY, player);
		player.getInventory().clear();
		war.potions.clearPotions(player);
			
	}
	
	public void teamMessage(int team, String message) {
		
		if (team == 0) {
			for (Player p : war.getServer().getOnlinePlayers()) {
				if (war.blu.contains(p)) {
					p.sendMessage(message);
				}
			}
			war.getLogger().info("[BLU] " + message);
			return;
		}
		
		if (team == 1) {
			for (Player p : war.getServer().getOnlinePlayers()) {
				if (war.red.contains(p)) {
					p.sendMessage(message);
				}
			}
			war.getLogger().info("[RED] " + message);
			return;
		}
		
		if (team == 2) {
			for (Player p : war.getServer().getOnlinePlayers()) {
				if ((!war.red.contains(p)) && (!war.blu.contains(p))) {
					p.sendMessage(message);
				}
			}
			war.getLogger().info("[RED] " + message);
			return;
		}
		
		war.getLogger().severe("Unable to find team for team message!");
		
	}
	
	public int teamName(Player player) {
		
		if (war.blu.contains(player)) {
			return 0;
		}
		
		if (war.red.contains(player)) {
			return 1;
		}
		
		return 9;
		
	}

}
