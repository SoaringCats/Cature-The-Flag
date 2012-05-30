package tk.nekotech.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import tk.nekotech.war.War;

public class EntityDamageByEntity implements Listener {
	private War war;
	
	public EntityDamageByEntity(War war) {
		this.war = war;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			Player damaged = (Player) event.getEntity();
			if (war.admins.contains(damaged)) {
				event.setCancelled(true);
				if (event.getDamager() instanceof Player) {
					Player damager = (Player) event.getDamager();
					war.sendMessage(damager, ChatColor.BLUE + "This administrator is in administrator mode and cannot be harmed!");
				}
				return;
			}
		}
		if (event.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) event.getDamager();
			if (arrow.getShooter() instanceof Player) {
				Player shooter = (Player) arrow.getShooter();
				if (event.getEntity() instanceof Player) {
					Player damaged = (Player) event.getEntity();
					if ((!war.blu.contains(damaged)) && (!war.red.contains(damaged))) {
						event.setCancelled(true);
						damaged.setFireTicks(0);
						war.sendMessage(shooter, ChatColor.GRAY + "You can't hurt a spectator!");
					}
					if ((war.blu.contains(shooter)) && (war.blu.contains(damaged))) {
						event.setCancelled(true);
						damaged.setFireTicks(0);
						war.sendMessage(shooter, ChatColor.BLUE + "You can't hurt your teammate " + damaged.getDisplayName() + ChatColor.BLUE + "!");
					} else if ((war.red.contains(shooter)) && (war.red.contains(damaged))) {
						event.setCancelled(true);
						shooter.sendMessage(ChatColor.RED + "You can't hurt your teammate " + damaged.getDisplayName() + ChatColor.RED + "!");
						damaged.setFireTicks(0);
					}
				}
			}
		}
		if (event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			if (war.pyro.contains(player)) {
				event.getEntity().setFireTicks(40);
			}
			if (war.monster.contains(player)) {
				player.getWorld().playEffect(player.getLocation(), Effect.GHAST_SHOOT, 100);
			}
			if (event.getEntity() instanceof Player) {
				Player damaged = (Player) event.getEntity();
				if ((!war.blu.contains(damaged)) && (!war.red.contains(damaged))) {
					event.setCancelled(true);
					damaged.setFireTicks(0);
					war.sendMessage(player, ChatColor.GRAY + "You can't hurt a spectator!");
				}
				if ((war.blu.contains(player)) && (war.blu.contains(damaged))) {
					event.setCancelled(true);
					damaged.setFireTicks(0);
					war.sendMessage(player, ChatColor.BLUE + "You can't hurt your teammate " + damaged.getDisplayName() + ChatColor.BLUE + "!");
				} else if ((war.red.contains(player)) && (war.red.contains(damaged))) {
					event.setCancelled(true);
					war.sendMessage(player, ChatColor.RED + "You can't hurt your teammate " + damaged.getDisplayName() + ChatColor.RED + "!");
					damaged.setFireTicks(0);
				}
			}
		}
	}

}
