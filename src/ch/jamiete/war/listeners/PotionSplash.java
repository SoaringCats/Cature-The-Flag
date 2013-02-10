package ch.jamiete.war.listeners;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionEffect;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;
import ch.jamiete.war.helpers.WarPlayer;

public class PotionSplash extends MasterListener {

    public PotionSplash(final War war) {
        super(war);
    }

    @EventHandler
    public void onPotionSplash(final PotionSplashEvent event) {
        if (event.getPotion().getShooter() instanceof Player) {
            final WarPlayer thrower = this.war.getHelper().getPlayerExact(((Player) event.getPotion().getShooter()).getName());

            for (final LivingEntity entity : event.getAffectedEntities()) {
                if (!(entity instanceof Player)) {
                    event.getAffectedEntities().remove(entity);
                    continue;
                }

                final WarPlayer hit = this.war.getHelper().getPlayerExact(((Player) entity).getName());

                for (final PotionEffect effect : event.getPotion().getEffects()) {
                    if (hit.getTeam() == thrower.getTeam()) {
                        if (!this.war.getHelper().isBadEffect(effect)) {
                            hit.getPlayer().addPotionEffect(effect, true);
                        }
                    } else {
                        if (this.war.getHelper().isBadEffect(effect)) {
                            hit.getPlayer().addPotionEffect(effect, true);
                        }
                    }
                }
            }
        }
    }
}
