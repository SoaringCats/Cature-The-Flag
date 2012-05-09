package tk.nekotech.war.helpers;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import tk.nekotech.war.War;

public class Armor {
	private War war;
	
	public Armor(War war) {
		this.war = war;
	}
	
	public void armorUp(final Player player) {
		war.getServer().getScheduler().scheduleAsyncDelayedTask(war, new Runnable() {
		    public void run() {
		    	player.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
				player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
				player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
				player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
				
				Random rand = new Random();
				int lolrandom = rand.nextInt(11);
				if (lolrandom == 10) {
					player.getInventory().addItem(new ItemStack(Material.TNT, 64));
					player.sendMessage(ChatColor.GOLD + "You have special EXPLODING abilities!");
					war.getServer().broadcastMessage(ChatColor.GOLD + player.getName() + " might just explode you... stay away!");
				}
		    }
		    
		}, 10L);
	}

}
