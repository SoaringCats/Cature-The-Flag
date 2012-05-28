package tk.nekotech.war.helpers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import tk.nekotech.war.War;

public class Color {
	@SuppressWarnings("unused")
	private War war;
	
	public Color(War war) {
		this.war = war;
	}
	
	public void setColor(ColorChoice color, Player player) {
		int len = player.getName().length();
		String name = player.getName();
		if (len == 16) {
			name = player.getName().substring(0, 13);
		} else if (len == 15) {
			name = player.getName().substring(0, 14);
		}
		boolean setName = true;
		ChatColor c = ChatColor.GRAY;
		if (color == ColorChoice.BLU) {
			player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) 11));
			c = ChatColor.BLUE;
		}
		if (color == ColorChoice.RED) {
			player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) 14));
			c = ChatColor.RED;
		}
		if (color == ColorChoice.PURPLE) {
			player.getInventory().setHelmet(new ItemStack(Material.WOOD, 1, (byte) 10));
			c = ChatColor.LIGHT_PURPLE;
			setName = false;
		}
		if (color == ColorChoice.GRAY) {
			player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) 8));
		}
		if (setName) {
			player.setDisplayName(c + player.getName() + ChatColor.WHITE);
		}
		player.setPlayerListName(c + name);
		//setHeadName(player, c, name);
	}
	
	/**
	 * Head name <b>requires</b> jamietech's custom Bukkit version
	 * @param player
	 */
	public void setHeadName(Player player, ChatColor color, String name) {
		player.getMetadata("headname").clear();
		//player.getMetadata("headname").add(color + name);
	}

}
