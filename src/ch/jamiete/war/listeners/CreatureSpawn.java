package ch.jamiete.war.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;

public class CreatureSpawn extends MasterListener {

    public CreatureSpawn(final War war) {
        super(war);
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
            if (event.getSpawnReason() != SpawnReason.SPAWNER || event.getSpawnReason() != SpawnReason.CUSTOM) {
                event.setCancelled(true);
            }
        }

        if (!event.isCancelled()) {
            if (!this.war.getHelper().isMobAllowed(event.getEntity().getType())) {
                event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), this.war.getHelper().getRandomMob());
                event.setCancelled(true);
            }
            this.war.getHelper().sendSmokeScreen(event.getLocation());
        }
    }
}
