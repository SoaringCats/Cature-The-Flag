package tk.nekotech.war.listeners;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionEffect;

import tk.nekotech.war.War;

public class PotionSplash implements Listener {
	private War war;
	
	public PotionSplash(War war) {
		this.war = war;
	}
	
	@EventHandler
	public void onPotionSplash(PotionSplashEvent event) {
		if (event.getPotion().getShooter() instanceof Player) {
			boolean bad = false;
			for (PotionEffect effect : event.getPotion().getEffects()) {
				if (war.potions.badPotion(effect)) {
					bad = true;
				}
			}
			Player thrower = (Player) event.getPotion().getShooter();
			for (LivingEntity entity : event.getAffectedEntities()) {
				if (entity instanceof Player) {
					if (war.teamhelpers.sameTeam(thrower, (Player) entity) && bad) {
						event.getAffectedEntities().remove(entity);
					} else {
						if (!bad) {
							event.getAffectedEntities().remove(entity);
						}
					}
				}
			}
		}
	}

}
