package tk.nekotech.war.listeners;

import org.bukkit.ChatColor;
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
		if (event.getEntity() instanceof Player && war.admins.contains((Player) event.getEntity())) {
			event.setCancelled(true);
			if (event.getDamager() instanceof Player) {
				war.sendMessage((Player) event.getDamager(), ChatColor.AQUA + "You cannot harm this administrator!");
			}
			return;
		}
		if (event.getDamager() instanceof Player && war.medic.contains((Player) event.getDamager()) && event.getEntity() instanceof Player) {
			Player damager = (Player) event.getDamager();
			Player damaged = (Player) event.getEntity();
			if (war.teamhelpers.sameTeam(damager, damaged)) {
				war.sendMessage(damaged, ChatColor.AQUA + "You were healed by " + damager.getName());
				war.sendMessage(damager, ChatColor.AQUA + "Healed " + damaged.getName() + "!");
				damaged.setHealth(20);
			} else {
				war.sendMessage(damager, ChatColor.AQUA + "You can't heal someone on the opposite team!");
			}
			event.setCancelled(true);
			return;
		}
		if (event.getEntity() instanceof Player && war.medic.contains((Player) event.getEntity())) {
			event.setDamage(event.getDamage() / 2);
		}
		if (event.getDamager() instanceof Arrow) {
			Player shooter = (Player) ((Arrow) event.getDamager()).getShooter();
			if (shooter instanceof Player && event.getEntity() instanceof Player) {
				if (!handleDamage(shooter, (Player) event.getEntity())) {
					event.setCancelled(true);
				}
			}
		}
		if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
			Player damager = (Player) event.getDamager();
			Player damaged = (Player) event.getEntity();
			if (handleDamage(damager, damaged)) {
				if (war.pyro.contains(damager)) {
					damaged.setFireTicks(40);
				}
			} else {
				event.setCancelled(true);
			}
		}
	}
	
	private boolean handleDamage(Player damager, Player damaged) {
		if (!war.teamhelpers.onTeam(damaged)) {
			war.sendMessage(damager, ChatColor.AQUA + "You cannot hurt a spectator!");
			return false;
		}
		if (war.teamhelpers.sameTeam(damager, damaged)) {
			war.sendMessage(damager, ChatColor.AQUA + "You cannot hurt players on your team!");
			return false;
		}
		return true;
	}

}
