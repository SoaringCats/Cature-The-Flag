package tk.nekotech.war.helpers;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
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
		
		if (player.getGameMode() == GameMode.CREATIVE)
			player.setGameMode(GameMode.SURVIVAL);
		
		if (team == 0) {
			war.blu.add(player);
			war.teamhelpers.alertTeam(player, team);
			double x = war.getConfig().getDouble("blu-spawn-x");
			double y = war.getConfig().getDouble("blu-spawn-y");
			double z = war.getConfig().getDouble("blu-spawn-z");
			float yaw = war.getConfig().getInt("blu-spawn-yaw");
			float pitch = war.getConfig().getInt("blu-spawn-pitch");
			player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));
			war.color.setColor(ColorChoice.BLU, player);
			assignClass(player);
		} else if (team == 1) {
			war.red.add(player);
			war.teamhelpers.alertTeam(player, team);
			double x = war.getConfig().getDouble("red-spawn-x");
			double y = war.getConfig().getDouble("red-spawn-y");
			double z = war.getConfig().getDouble("red-spawn-z");
			float yaw = war.getConfig().getInt("red-spawn-yaw");
			float pitch = war.getConfig().getInt("red-spawn-pitch");
			player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));
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
		if (classID == 0) {
			war.getServer().getScheduler().scheduleAsyncDelayedTask(war, new Runnable() {
			    public void run() {
			    	war.potions.addEffect(player, PotionEffectType.SLOW, 999999999, 2);
			    	war.potions.addEffect(player, PotionEffectType.DAMAGE_RESISTANCE, 999999999, 1);
			    	war.sendMessage(player, ChatColor.GOLD + "You are now a heavy!");
					player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
					player.getInventory().getItem(0).addEnchantment(Enchantment.DAMAGE_ALL, 2);
					player.setNoDamageTicks(10);
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
					player.setNoDamageTicks(10);
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
					player.setNoDamageTicks(10);
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
					player.setNoDamageTicks(10);
					war.armor.armorUp(player);
			    }
			}, 10L);
		}
		if (classID == 4) {
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
		}
	}

}
