package tk.nekotech.war.helpers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
	
	public ItemStack getWool(Player player) {
		int team = teamName(player);
		if (team == 0)
			return new ItemStack(Material.WOOL, 1, (byte) 11);
		if (team == 1)
			return new ItemStack(Material.WOOL, 1, (byte) 15);
		return new ItemStack(Material.WOOL, 1, (byte) 7);
	}
	
	public ChatColor getColor(Player player) {
		if (!onTeam(player))
			return ChatColor.GRAY;
		if (teamName(player) == 0)
			return ChatColor.BLUE;
		if (teamName(player) == 1)
			return ChatColor.RED;
		return ChatColor.BLACK;
	}
	
	public void alertTeam(Player player, int team) {
		if (team == 0) {
			war.getServer().broadcastMessage(war.getMessage() + ChatColor.BLUE + player.getName() + " is now on blu team!");
		}
		if (team == 1) {
			war.getServer().broadcastMessage(war.getMessage() + ChatColor.RED + player.getName() + " is now on red team!");
		}
		war.getServer().broadcastMessage(war.getMessage() + ChatColor.GREEN + "There are now " + ChatColor.BLUE + blu() + " on blu" + ChatColor.GREEN + " and " + ChatColor.RED + red() + " on red");
	}
	
	public boolean onTeam(Player player) {
		if ((war.blu.contains(player)) || (war.red.contains(player)))
			return true;
		return false;		
	}
	
	public void clearTeams(Player player) {
		if (war.blu.contains(player))
			war.blu.remove(player);
		if (war.red.contains(player))
			war.red.remove(player);
		if (war.pyro.contains(player))
			war.pyro.remove(player);
		if (war.monster.contains(player))
			war.monster.remove(player);
		war.color.setColor(ColorChoice.GRAY, player);
		player.getInventory().clear();
		war.potions.clearPotions(player);
	}
	
	public void teamMessage(int team, String message) {
		if (team == 0) {
			for (Player p : war.getServer().getOnlinePlayers()) {
				if (war.blu.contains(p)) {
					war.sendMessage(p, ChatColor.BLUE + message);
				}
			}
			war.getLogger().info("[BLU] " + message);
			return;
		}
		if (team == 1) {
			for (Player p : war.getServer().getOnlinePlayers()) {
				if (war.red.contains(p)) {
					war.sendMessage(p, ChatColor.RED + message);
				}
			}
			war.getLogger().info("[RED] " + message);
			return;
		}
		if (team == 9) {
			for (Player p : war.getServer().getOnlinePlayers()) {
				if ((!war.red.contains(p)) && (!war.blu.contains(p))) {
					war.sendMessage(p, ChatColor.GRAY + message);
				}
			}
			war.getLogger().info("[SPECTATE] " + message);
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
