package tk.nekotech.war.listeners;

import org.bukkit.Effect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import tk.nekotech.war.War;

public class CreatureSpawn implements Listener {
	private War war;
	
	public CreatureSpawn(War war) {
		this.war = war;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if (!war.getConfig().getBoolean("ready-to-go")) {
			event.setCancelled(true);
			return;
		}
		if (war.getConfig().getBoolean("mob-spawns")) {
			event.setCancelled(true);
			if ((event.getSpawnReason() == SpawnReason.SPAWNER) || (event.getSpawnReason() == SpawnReason.CUSTOM)) {
				if (!war.mob.isAllowed(event.getEntityType())) {
					event.getEntity().getWorld().spawnCreature(event.getEntity().getLocation(), war.mob.randoMob());
					event.setCancelled(true);
				}
			}
			if (event.getSpawnReason() == SpawnReason.SPAWNER_EGG) {
				event.setCancelled(false);
			}
		}
		if (!event.isCancelled()) {
			event.getEntity().getLocation().getWorld().playEffect(event.getEntity().getLocation(), Effect.SMOKE, 50);
		}
	}
	
}
