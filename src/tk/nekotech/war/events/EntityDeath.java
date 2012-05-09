package tk.nekotech.war.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import tk.nekotech.war.War;

public class EntityDeath implements Listener {
	private War war;
	
	public EntityDeath(War war) {
		this.war = war;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntity().getLastDamageCause().getCause() == DamageCause.BLOCK_EXPLOSION) {
			event.setDroppedExp(0);
			event.getDrops().clear();
			for (int a = 0; a < 10; a++) {
				event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.TNT));
			}
		}
		if (event.getEntity().getLastDamageCause().getCause() == DamageCause.CONTACT) {
			event.setDroppedExp(0);
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(Material.COOKIE, 1));
		}
		if (event.getEntity().getLastDamageCause().getCause() == DamageCause.DROWNING) {
			event.setDroppedExp(0);
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(Material.BREAD, 1));
		}
		if (event.getEntity().getLastDamageCause().getCause() == DamageCause.ENTITY_ATTACK) {
			event.setDroppedExp(10);
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(Material.APPLE, 1));
		}
		if (event.getEntity().getLastDamageCause().getCause() == DamageCause.ENTITY_EXPLOSION) {
			event.setDroppedExp(0);
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(Material.PORK, 1));
		}
		if (event.getEntity().getLastDamageCause().getCause() == DamageCause.FALL) {
			event.setDroppedExp(10);
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(Material.COOKED_BEEF, 1));
		}
		if (event.getEntity().getLastDamageCause().getCause() == DamageCause.FIRE) {
			event.setDroppedExp(10);
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(Material.COOKED_CHICKEN, 1));
		}
		if (event.getEntity().getLastDamageCause().getCause() == DamageCause.FIRE_TICK) {
			event.setDroppedExp(10);
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(Material.COOKED_FISH, 2));
		}
		if (event.getEntity().getLastDamageCause().getCause() == DamageCause.LAVA) {
			event.setDroppedExp(10);
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(Material.COOKED_FISH, 64));
		}
		if (event.getEntity().getLastDamageCause().getCause() == DamageCause.LIGHTNING) {
			event.setDroppedExp(10);
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(Material.TNT, 1));
		}
		if (event.getEntity().getLastDamageCause().getCause() == DamageCause.MAGIC) {
			event.setDroppedExp(10);
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(Material.TNT, 1));
		}
		if (event.getEntity().getLastDamageCause().getCause() == DamageCause.POISON) {
			event.setDroppedExp(10);
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(Material.TNT, 1));
		}
		if (event.getEntity().getLastDamageCause().getCause() == DamageCause.PROJECTILE) {
			 event.setDroppedExp(10);
			 event.getDrops().clear();
			 event.getDrops().add(new ItemStack(Material.MELON, 3));
		}
		if (event.getEntity().getLastDamageCause().getCause() == DamageCause.STARVATION) {
			event.setDroppedExp(10);
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(Material.TNT, 1));
		}
		if (event.getEntity().getLastDamageCause().getCause() == DamageCause.SUFFOCATION) {
			event.setDroppedExp(10);
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(Material.TNT, 1));
		}
		if (event.getEntity().getLastDamageCause().getCause() == DamageCause.SUICIDE) {
			event.setDroppedExp(0);
			event.getDrops().clear();
			event.getDrops().add(new ItemStack(Material.TNT, 1));
		}
		if (event.getEntity().getLastDamageCause().getCause() == DamageCause.VOID) {
			event.setDroppedExp(0);
			event.getDrops().clear();
		}
		if (event.getEntity() instanceof Player) {
			 Player player = (Player) event.getEntity();
			 war.teamhelpers.clearTeams(player);
		 }
	}

}
