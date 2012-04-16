package tk.nekotech.war.helpers;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import tk.nekotech.war.War;

public class Potions {
	@SuppressWarnings("unused")
	private War war;
	
	public Potions(War war) {
		this.war = war;
	}
	
	public void addEffect(Player player, PotionEffectType effect, int time, int level) {
		player.addPotionEffect(new PotionEffect(effect, time, level));
	}
	
	public void clearPotions(Player player) {
		for (PotionEffect potion : player.getActivePotionEffects()) {
			player.removePotionEffect(potion.getType());
		}
	}

}
