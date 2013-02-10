package ch.jamiete.war.helpers;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ch.jamiete.war.War;


public class Armor {
	private War war;
	
	public Armor(War war) {
		this.war = war;
	}
	
	public void armorUp(final Player player) {
		war.getServer().getScheduler().scheduleAsyncDelayedTask(war, new Runnable() {
		    public void run() {
		    	war.color.setHelmet(player, war.teamhelpers.teamName(player));
				player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
				player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
				player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
				Random random = new Random();
				int rand = random.nextInt(11);
				if (rand == 10) {
					player.getInventory().addItem(new ItemStack(Material.TNT, 64));
					war.sendMessage(player, ChatColor.GOLD + "You have special EXPLODING abilities!");
					war.getServer().broadcastMessage(war.getMessage() + ChatColor.GOLD + player.getName() + " might just explode you... stay away!");
				}
		    }
		    
		}, 10L);
	}

}
