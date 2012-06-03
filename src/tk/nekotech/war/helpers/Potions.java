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
	
	public boolean badPotion(PotionEffect effect) {
		PotionEffectType type = effect.getType();
		if ((type == PotionEffectType.BLINDNESS)
				|| (type == PotionEffectType.CONFUSION)
				|| (type == PotionEffectType.DAMAGE_RESISTANCE)
				|| (type == PotionEffectType.HARM)
				|| (type == PotionEffectType.HUNGER)
				|| (type == PotionEffectType.POISON)
				|| (type == PotionEffectType.SLOW)
				|| (type == PotionEffectType.SLOW_DIGGING)
				|| (type == PotionEffectType.WEAKNESS)) {
			return true;
		}
		return false;
	}

}
