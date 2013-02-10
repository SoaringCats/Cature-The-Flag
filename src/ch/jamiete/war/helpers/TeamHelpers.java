package ch.jamiete.war.helpers;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ch.jamiete.war.War;


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
		return war.blu.contains(player) || war.red.contains(player);
	}
	
	public void clearTeams(Player player) {
		war.quickplayer.clearAttachments(player);
		war.color.setColor(ColorChoice.GRAY, player);
		player.getInventory().clear();
		war.potions.clearPotions(player);
	}
	
	public void teamMessage(int team, String message) {
		if (team == 2) {
			war.adminMode(ChatColor.LIGHT_PURPLE + "Discarded team message `" + message + "` sent by an admin");
		}
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
		if (war.admins.contains(player)) {
			return 2;
		}
		if (war.blu.contains(player)) {
			return 0;
		}
		if (war.red.contains(player)) {
			return 1;
		}
		return 9;
	}
	
	public void toSpawn(Player player, int teamID) {
		if (teamID == 0) {
			double x = war.getConfig().getDouble("blu-spawn-x");
			double y = war.getConfig().getDouble("blu-spawn-y");
			double z = war.getConfig().getDouble("blu-spawn-z");
			float yaw = war.getConfig().getInt("blu-spawn-yaw");
			float pitch = war.getConfig().getInt("blu-spawn-pitch");
			player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));
		} else if (teamID == 1) {
			double x = war.getConfig().getDouble("red-spawn-x");
			double y = war.getConfig().getDouble("red-spawn-y");
			double z = war.getConfig().getDouble("red-spawn-z");
			float yaw = war.getConfig().getInt("red-spawn-yaw");
			float pitch = war.getConfig().getInt("red-spawn-pitch");
			player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));
		} else {
			double sx = war.getConfig().getDouble("spec-spawn-x");
			double sy = war.getConfig().getDouble("spec-spawn-y");
			double sz = war.getConfig().getDouble("spec-spawn-z");
			float syaw = war.getConfig().getInt("spec-spawn-yaw");
			float spitch = war.getConfig().getInt("spec-spawn-pitch");
			player.teleport(new Location(player.getWorld(), sx, sy, sz, syaw, spitch));
		}
	}
	
	public boolean sameTeam(Player one, Player two) {
		return ((war.blu.contains(one) && war.blu.contains(two)) || (war.red.contains(one) && war.red.contains(two)));
	}

}
