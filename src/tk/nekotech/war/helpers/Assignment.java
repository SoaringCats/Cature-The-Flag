package tk.nekotech.war.helpers;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import tk.nekotech.war.War;

public class Assignment {
	private War war;
	
	public Assignment(War war) {
		this.war = war;
	}
	
	public void assignPlayer(Player player, int team) {
		if (war.blu.contains(player))
			war.blu.remove(player);
		if (war.red.contains(player))
			war.red.remove(player);
		if (war.pyro.contains(player))
			war.pyro.remove(player);
		if (war.monster.contains(player))
			war.monster.remove(player);
		if (war.medic.contains(player))
			war.medic.remove(player);
		
		if (player.getGameMode() == GameMode.CREATIVE)
			player.setGameMode(GameMode.SURVIVAL);
		
		if (team == 0) {
			war.blu.add(player);
			war.teamhelpers.alertTeam(player, team);
			war.teamhelpers.toSpawn(player, team);
			war.color.setColor(ColorChoice.BLU, player);
			assignClass(player);
		} else if (team == 1) {
			war.red.add(player);
			war.teamhelpers.alertTeam(player, team);
			war.teamhelpers.toSpawn(player, team);
			war.color.setColor(ColorChoice.RED, player);
			assignClass(player);
		} else {
			player.kickPlayer(ChatColor.AQUA + "Rejoin in 5 seconds :)");
		}
	}
	
	public void assignClass(final Player player) {
		Random rand = new Random();
		int classID = rand.nextInt(5);
		player.getInventory().clear();
		player.setNoDamageTicks(10);
		if (classID == 0) {
			war.getServer().getScheduler().scheduleAsyncDelayedTask(war, new Runnable() {
			    public void run() {
			    	war.potions.addEffect(player, PotionEffectType.SLOW, 999999999, 2);
			    	war.potions.addEffect(player, PotionEffectType.DAMAGE_RESISTANCE, 999999999, 1);
			    	war.sendMessage(player, ChatColor.GOLD + "You are now a heavy!");
					player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
					player.getInventory().getItem(0).addEnchantment(Enchantment.DAMAGE_ALL, 2);
					war.armor.armorUp(player);
			    }
			}, 10L);
		}
		if (classID == 1) {
			war.getServer().getScheduler().scheduleAsyncDelayedTask(war, new Runnable() {
			    public void run() {
			    	war.potions.addEffect(player, PotionEffectType.SPEED, 999999999, 1);
			    	war.sendMessage(player, ChatColor.GOLD + "You are now a scout!");
					player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
					player.getInventory().getItem(0).addEnchantment(Enchantment.KNOCKBACK, 2);
					war.armor.armorUp(player);
			    }
			}, 10L);
		}
		if (classID == 2) {
			war.getServer().getScheduler().scheduleAsyncDelayedTask(war, new Runnable() {
			    public void run() {
			    	war.potions.addEffect(player, PotionEffectType.DAMAGE_RESISTANCE, 999999999, 2);
			    	war.sendMessage(player, ChatColor.GOLD + "You are now a sniper!");
					player.getInventory().addItem(new ItemStack(Material.BOW, 1));
					player.getInventory().addItem(new ItemStack(Material.ARROW, 1));
					player.getInventory().getItem(0).addEnchantment(Enchantment.ARROW_INFINITE, 1);
					player.getInventory().getItem(0).addEnchantment(Enchantment.ARROW_DAMAGE, 2);
					war.armor.armorUp(player);
			    }
			}, 10L);
		}
		if (classID == 3) {
			war.getServer().getScheduler().scheduleAsyncDelayedTask(war, new Runnable() {
			    public void run() {
			    	war.potions.addEffect(player, PotionEffectType.FIRE_RESISTANCE, 999999999, 3);
					war.pyro.add(player);
					war.sendMessage(player, ChatColor.GOLD + "You are now a pyro!");
					player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
					war.armor.armorUp(player);
			    }
			}, 10L);
		}
		if (classID == 4) {
			war.getServer().getScheduler().scheduleAsyncDelayedTask(war, new Runnable() {
			    public void run() {
			    	war.potions.addEffect(player, PotionEffectType.SPEED, 999999999, 2);
					war.medic.add(player);
					war.sendMessage(player, ChatColor.GOLD + "You are now a medic!");
					war.teamhelpers.teamMessage(war.teamhelpers.teamName(player), "[AUTO] I am now a medic!");
					player.getInventory().addItem(new ItemStack(Material.WOOD_SWORD, 1));
					player.getInventory().addItem(new ItemStack(Material.EGG, 64));
					war.armor.armorUp(player);
			    }
			}, 10L);
		}
		/*if (classID == 4) {
			war.getServer().getScheduler().scheduleAsyncDelayedTask(war, new Runnable() {
			    public void run() {
			    	war.potions.addEffect(player, PotionEffectType.FIRE_RESISTANCE, 999999999, 3);
			    	war.potions.addEffect(player, PotionEffectType.DAMAGE_RESISTANCE, 999999999, 2);
			    	war.potions.addEffect(player, PotionEffectType.SPEED, 999999999, 1);
					war.pyro.add(player);
					war.monster.add(player);
					war.sendMessage(player, ChatColor.GOLD + "You are now a monster!");
					war.getServer().broadcastMessage(war.getMessage() + ChatColor.GOLD + player.getName() + " is a monster, beware!");
					player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
					player.getInventory().addItem(new ItemStack(Material.BOW, 1));
					player.getInventory().addItem(new ItemStack(Material.ARROW, 1));
					player.getInventory().getItem(0).addEnchantment(Enchantment.KNOCKBACK, 2);
					player.getInventory().getItem(0).addEnchantment(Enchantment.DAMAGE_ALL, 2);
					player.getInventory().getItem(1).addEnchantment(Enchantment.ARROW_INFINITE, 1);
					player.getInventory().getItem(1).addEnchantment(Enchantment.ARROW_DAMAGE, 2);
					player.setNoDamageTicks(10);
					war.armor.armorUp(player);
			    }
			}, 10L);
		}*/
	}

}
