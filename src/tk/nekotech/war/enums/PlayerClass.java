package tk.nekotech.war.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.google.common.collect.Maps;

import tk.nekotech.war.War;
import tk.nekotech.war.player.WarPlayer;

public enum PlayerClass {

	HEAVY, SNIPER, SOLDIER;
	
	private final static Map<String, PlayerClass> BY_NAME = Maps.newHashMap();
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}
	
	public static PlayerClass getClassByName(String name) {
	    return BY_NAME.get(name.toUpperCase());
	}
	
	public static List<PotionEffect> getPotionEffects(WarPlayer player) {
		List<PotionEffect> list = new ArrayList<PotionEffect>();
		switch (player.getPlayerClass()) {
		case HEAVY:
			list.add(new PotionEffect(PotionEffectType.SLOW, 200, 1));
			break;
		case SNIPER:
			list.add(new PotionEffect(PotionEffectType.HARM, 200, 1));
			break;
		case SOLDIER:
			list.add(new PotionEffect(PotionEffectType.JUMP, 200, 1));
			break;
		}
		return list;
	}
	
	public static void giveClassItems(WarPlayer player) {
		PlayerInventory inventory = player.getPlayer().getInventory();
		switch (player.getPlayerClass()) {
		case HEAVY:
			inventory.addItem(new ItemStack(Material.DIAMOND_SWORD));
			inventory.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
			inventory.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
			inventory.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
			inventory.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
			break;
		case SNIPER:
			ItemStack bow = new ItemStack(Material.BOW);
			bow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
			if (War.random.nextInt(25) == 0) {
				bow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
			}
			inventory.addItem(bow);
			inventory.setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
			inventory.setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
			inventory.setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
			inventory.setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
			break;
		case SOLDIER:
			inventory.addItem(new ItemStack(Material.GOLD_SWORD));
			inventory.setHelmet(new ItemStack(Material.GOLD_HELMET));
			inventory.setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
			inventory.setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
			inventory.setBoots(new ItemStack(Material.GOLD_BOOTS));
			break;
		}
	}
	
	static {
        for (PlayerClass pc : values()) {
            BY_NAME.put(pc.name(), pc);
        }
	}
	
}
