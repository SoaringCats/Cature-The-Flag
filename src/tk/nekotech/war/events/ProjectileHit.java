package tk.nekotech.war.events;

import org.bukkit.EntityEffect;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import tk.nekotech.war.War;

public class ProjectileHit implements Listener {
	private War war;
	
	public ProjectileHit(War war) {
		this.war = war;
	}
	
	@EventHandler
	public void onProjectileHit(final ProjectileHitEvent event) {
		if (event.getEntity() instanceof Arrow) {
			event.getEntity().playEffect(EntityEffect.WOLF_HEARTS);
			war.getServer().getScheduler().scheduleSyncDelayedTask(war, new Runnable() {
				@Override
				public void run() {
					event.getEntity().remove();
				}
			}, 40L);
		}
	}

}
