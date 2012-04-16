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
	public void onCreatureSpawn(CreatureSpawnEvent e) {
		
		if (!war.getConfig().getBoolean("ready-to-go")) {
			e.setCancelled(true);
			return;
		}
		
		if (war.getConfig().getBoolean("mob-spawns")) {
			if (e.getSpawnReason() != SpawnReason.SPAWNER) {
				if (e.getSpawnReason() != SpawnReason.CUSTOM) {
					if (!war.mob.isAllowed(e.getEntityType())) {
						e.getEntity().getWorld().spawnCreature(e.getEntity().getLocation(), war.mob.randoMob());
						e.setCancelled(true);
					}
				} else {
					e.setCancelled(true);
				}
			}
		}
	}
	
}
