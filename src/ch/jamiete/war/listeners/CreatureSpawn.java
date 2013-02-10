package ch.jamiete.war.listeners;

import org.bukkit.Effect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import ch.jamiete.war.War;

public class CreatureSpawn implements Listener {
    private final War war;

    public CreatureSpawn(final War war) {
        this.war = war;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCreatureSpawn(final CreatureSpawnEvent event) {
        if (!this.war.getConfig().getBoolean("ready-to-go")) {
            event.setCancelled(true);
            return;
        }
        if (this.war.getConfig().getBoolean("mob-spawns")) {
            if (event.getSpawnReason() == SpawnReason.SPAWNER_EGG) {
                return;
            }
            if (!(event.getSpawnReason() == SpawnReason.SPAWNER) || !(event.getSpawnReason() == SpawnReason.CUSTOM)) {
                event.setCancelled(true);
            }
        }
        if (!event.isCancelled()) {
            if (!this.war.mob.isAllowed(event.getEntity().getType())) {
                event.getEntity().getWorld().spawnCreature(event.getEntity().getLocation(), this.war.mob.randoMob());
                event.setCancelled(true);
            }
            event.getEntity().getLocation().getWorld().playEffect(event.getEntity().getLocation(), Effect.SMOKE, 50);
        }
    }

}
