package tk.nekotech.war.helpers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import tk.nekotech.war.War;

public class Color {
	private War war;
	
	public Color(War war) {
		this.war = war;
	}
	
	public void setHelmet(Player player) {
		int teamID = war.teamhelpers.teamName(player);
		switch (teamID) {
			case 0:
				player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) 11));
				break;
			case 1:
				player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) 14));
				break;
			case 9:
				player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) 8));
				break;
		}
	}
	
	public void setHelmet(Player player, int id) {
		switch (id) {
			case 0:
				player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) 11));
				break;
			case 1:
				player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) 14));
				break;
			case 9:
				player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) 8));
				break;
			case 2:
				player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) 2));
		}
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
		ChatColor c = null;
		switch (color) {
			case BLU:
				c = ChatColor.BLUE;
				setHelmet(player, 0);
				break;
			case RED:
				c = ChatColor.RED;
				setHelmet(player, 1);
				break;
			case PURPLE:
				c = ChatColor.LIGHT_PURPLE;
				setName = false;
				setHelmet(player, 2);
				break;
			case GRAY:
				c = ChatColor.GRAY;
				setHelmet(player, 9);
				break;
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
