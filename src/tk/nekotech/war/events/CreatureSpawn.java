package tk.nekotech.war.events;

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
			if (event.getSpawnReason() != SpawnReason.SPAWNER) {
				if (event.getSpawnReason() != SpawnReason.CUSTOM) {
					if (!war.mob.isAllowed(event.getEntityType())) {
						event.getEntity().getWorld().spawnCreature(event.getEntity().getLocation(), war.mob.randoMob());
						event.setCancelled(true);
					}
				} else {
					event.setCancelled(true);
				}
			}
		}
	}
	
}
