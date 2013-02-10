package ch.jamiete.war.listeners;

import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import ch.jamiete.war.War;

public class EntityDamage implements Listener {
    private final War war;

    public EntityDamage(final War war) {
        this.war = war;
    }

    @EventHandler
    public void onEntityDamage(final EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player) event.getEntity();
            if (this.war.admins.contains(player)) {
                event.setCancelled(true);
                return;
            }
            if (this.war.monster.contains(player)) {
                player.getWorld().playEffect(player.getLocation(), Effect.GHAST_SHRIEK, 100);
            }
        }
    }

}
