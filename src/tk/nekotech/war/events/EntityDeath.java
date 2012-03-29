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
	public void onEntityDeath(EntityDeathEvent e) {
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.BLOCK_EXPLOSION) {
			 
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.CONTACT) {
			 e.setDroppedExp(0);
			 e.getDrops().clear();
			 e.getDrops().add(new ItemStack(Material.COOKIE, 1));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.DROWNING) {
			 e.setDroppedExp(0);
			 e.getDrops().clear();
			 e.getDrops().add(new ItemStack(Material.BREAD, 1));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.ENTITY_ATTACK) {
			 e.setDroppedExp(10);
			 e.getDrops().clear();
			 e.getDrops().add(new ItemStack(Material.APPLE, 1));
		 }
		 
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.ENTITY_EXPLOSION) {
			 e.setDroppedExp(0);
			 e.getDrops().clear();
			 e.getDrops().add(new ItemStack(Material.PORK, 1));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.FALL) {
			e.setDroppedExp(10);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.COOKED_BEEF, 1));
		}
		 
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.FIRE) {
			e.setDroppedExp(10);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.COOKED_CHICKEN, 1));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.FIRE_TICK) {
			e.setDroppedExp(10);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.COOKED_FISH, 2));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.LAVA) {
			e.setDroppedExp(10);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.COOKED_FISH, 64));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.LIGHTNING) {
			e.setDroppedExp(10);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.TNT, 1));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.MAGIC) {
			e.setDroppedExp(10);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.TNT, 1));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.POISON) {
			e.setDroppedExp(10);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.TNT, 1));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.PROJECTILE) {
			 e.setDroppedExp(10);
			 e.getDrops().clear();
			 e.getDrops().add(new ItemStack(Material.MELON, 3));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.STARVATION) {
			e.setDroppedExp(10);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.TNT, 1));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.SUFFOCATION) {
			e.setDroppedExp(10);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.TNT, 1));
		}
		 
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.SUICIDE) {
			e.setDroppedExp(0);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.TNT, 1));
		}
		 
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.VOID) {
			e.setDroppedExp(0);
			e.getDrops().clear();
		}
		 
		if (e.getEntity() instanceof Player) {
			 Player player = (Player) e.getEntity();
			 war.teamhelpers.clearTeams(player);
		 }
	}

}
