package ch.jamiete.war.listeners;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionEffect;
import ch.jamiete.war.War;

public class PotionSplash implements Listener {
    private final War war;

    public PotionSplash(final War war) {
        this.war = war;
    }

    @EventHandler
    public void onPotionSplash(final PotionSplashEvent event) {
        if (event.getPotion().getShooter() instanceof Player) {
            boolean bad = false;
            for (final PotionEffect effect : event.getPotion().getEffects()) {
                if (this.war.potions.badPotion(effect)) {
                    bad = true;
                }
            }
            final Player thrower = (Player) event.getPotion().getShooter();
            for (final LivingEntity entity : event.getAffectedEntities()) {
                if (entity instanceof Player) {
                    if (this.war.teamhelpers.sameTeam(thrower, (Player) entity) && bad) {
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
