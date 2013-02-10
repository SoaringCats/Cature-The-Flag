package ch.jamiete.war.helpers;

import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import ch.jamiete.war.War;

public class Assignment {
    private final War war;
    Random rand;

    public Assignment(final War war) {
        this.war = war;
        this.rand = new Random();
    }

    public void assignClass(final Player player) {
        final int classID = this.rand.nextInt(5);
        player.getInventory().clear();
        player.setNoDamageTicks(10);
        this.assignPotion(player);
        switch (classID) {
            case 0:
                this.war.getServer().getScheduler().scheduleAsyncDelayedTask(this.war, new Runnable() {
                    @Override
                    public void run() {
                        Assignment.this.war.potions.addEffect(player, PotionEffectType.SLOW, 999999999, 2);
                        Assignment.this.war.potions.addEffect(player, PotionEffectType.DAMAGE_RESISTANCE, 999999999, 1);
                        Assignment.this.war.sendMessage(player, ChatColor.GOLD + "You are now a heavy!");
                        player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
                        player.getInventory().getItem(0).addEnchantment(Enchantment.DAMAGE_ALL, 2);
                        Assignment.this.war.armor.armorUp(player);
                    }
                }, 10L);
                break;
            case 1:
                this.war.getServer().getScheduler().scheduleAsyncDelayedTask(this.war, new Runnable() {
                    @Override
                    public void run() {
                        Assignment.this.war.potions.addEffect(player, PotionEffectType.SPEED, 999999999, 1);
                        Assignment.this.war.sendMessage(player, ChatColor.GOLD + "You are now a scout!");
                        player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
                        player.getInventory().getItem(0).addEnchantment(Enchantment.KNOCKBACK, 2);
                        Assignment.this.war.armor.armorUp(player);
                    }
                }, 10L);
                break;
            case 2:
                this.war.getServer().getScheduler().scheduleAsyncDelayedTask(this.war, new Runnable() {
                    @Override
                    public void run() {
                        Assignment.this.war.potions.addEffect(player, PotionEffectType.DAMAGE_RESISTANCE, 999999999, 2);
                        Assignment.this.war.sendMessage(player, ChatColor.GOLD + "You are now a sniper!");
                        player.getInventory().addItem(new ItemStack(Material.BOW, 1));
                        player.getInventory().addItem(new ItemStack(Material.ARROW, 1));
                        player.getInventory().getItem(0).addEnchantment(Enchantment.ARROW_INFINITE, 1);
                        player.getInventory().getItem(0).addEnchantment(Enchantment.ARROW_DAMAGE, 2);
                        Assignment.this.war.armor.armorUp(player);
                    }
                }, 10L);
                break;
            case 3:
                this.war.getServer().getScheduler().scheduleAsyncDelayedTask(this.war, new Runnable() {
                    @Override
                    public void run() {
                        Assignment.this.war.potions.addEffect(player, PotionEffectType.FIRE_RESISTANCE, 999999999, 3);
                        Assignment.this.war.pyro.add(player);
                        Assignment.this.war.sendMessage(player, ChatColor.GOLD + "You are now a pyro!");
                        player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
                        Assignment.this.war.armor.armorUp(player);
                    }
                }, 10L);
                break;
            case 4:
                this.war.getServer().getScheduler().scheduleAsyncDelayedTask(this.war, new Runnable() {
                    @Override
                    public void run() {
                        Assignment.this.war.potions.addEffect(player, PotionEffectType.SPEED, 999999999, 2);
                        Assignment.this.war.medic.add(player);
                        Assignment.this.war.sendMessage(player, ChatColor.GOLD + "You are now a medic!");
                        Assignment.this.war.teamhelpers.teamMessage(Assignment.this.war.teamhelpers.teamName(player), ChatColor.WHITE + "<" + player.getDisplayName() + "> [AUTO] I am now a medic!");
                        player.getInventory().addItem(new ItemStack(Material.WOOD_SWORD, 1));
                        player.getInventory().addItem(new ItemStack(Material.EGG, 64));
                        Assignment.this.war.armor.armorUp(player);
                    }
                }, 10L);
                break;
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

    public void assignPlayer(final Player player, final int team) {
        this.war.quickplayer.clearAttachments(player);

        if (player.getGameMode() == GameMode.CREATIVE) {
            player.setGameMode(GameMode.SURVIVAL);
        }

        if (team == 0) {
            this.war.blu.add(player);
            this.war.teamhelpers.alertTeam(player, team);
            this.war.teamhelpers.toSpawn(player, team);
            this.war.color.setColor(ColorChoice.BLU, player);
            this.assignClass(player);
        } else if (team == 1) {
            this.war.red.add(player);
            this.war.teamhelpers.alertTeam(player, team);
            this.war.teamhelpers.toSpawn(player, team);
            this.war.color.setColor(ColorChoice.RED, player);
            this.assignClass(player);
        } else {
            player.kickPlayer(ChatColor.AQUA + "Rejoin in 5 seconds :)");
        }
    }

    private void assignPotion(final Player player) {
        /*if (rand.nextBoolean()) {
        	int random = rand.nextInt(10);
        	switch (random) {
        		case 0:
        			player.getInventory().getItem(8).setType(Material.POTION);
        			player.getInventory().getItem(8).setAmount(1);
        			player.getInventory().getItem(8).setDurability((short) 24580);
        			break;
        		case 1:
        			player.getInventory().getItem(8).setType(Material.POTION);
        			player.getInventory().getItem(8).setAmount(1);
        			player.getInventory().getItem(8).setDurability((short) 24585);
        			break;
        	}
        }*/
    }

}
