package ch.jamiete.war.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ch.jamiete.war.War;

public class ProjectileHit implements Listener {
    private final War war;

    public ProjectileHit(final War war) {
        this.war = war;
    }

    @EventHandler
    public void onProjectileHit(final ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            this.war.arrows.add((Arrow) event.getEntity());
        }
        if (event.getEntity() instanceof Egg) {
            final Egg egg = (Egg) event.getEntity();
            if (egg.getShooter() instanceof Player && this.war.medic.contains(egg.getShooter())) {
                egg.getLocation().getWorld().createExplosion(egg.getLocation(), 0);
                for (final Entity entity : egg.getNearbyEntities(10D, 10D, 10D)) {
                    if (entity instanceof Player) {
                        final Player player = (Player) entity;
                        if (!this.war.teamhelpers.sameTeam((Player) egg.getShooter(), player)) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 300, 1));
                        }
                    }
                }
            }
        }
    }

}
