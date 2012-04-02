package tk.nekotech.war.events;

import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import tk.nekotech.war.War;

public class EntityDamage implements Listener {
	private War war;
	
	public EntityDamage(War war) {
		this.war = war;
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (war.monster.contains(player))
				player.getWorld().playEffect(player.getLocation(), Effect.GHAST_SHRIEK, 100);
		}
	}

}
