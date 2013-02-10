package ch.jamiete.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import ch.jamiete.war.War;

public class EntityDamageByEntity implements Listener {
    private final War war;

    public EntityDamageByEntity(final War war) {
        this.war = war;
    }

    private boolean handleDamage(final Player damager, final Player damaged) {
        if (!this.war.teamhelpers.onTeam(damaged)) {
            this.war.sendMessage(damager, ChatColor.AQUA + "You cannot hurt a spectator!");
            return false;
        }
        if (this.war.teamhelpers.sameTeam(damager, damaged)) {
            this.war.sendMessage(damager, ChatColor.AQUA + "You cannot hurt players on your team!");
            return false;
        }
        return true;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && this.war.admins.contains(event.getEntity())) {
            event.setCancelled(true);
            if (event.getDamager() instanceof Player) {
                this.war.sendMessage((Player) event.getDamager(), ChatColor.AQUA + "You cannot harm this administrator!");
            }
            return;
        }
        if (event.getDamager() instanceof Player && this.war.medic.contains(event.getDamager()) && event.getEntity() instanceof Player) {
            final Player damager = (Player) event.getDamager();
            final Player damaged = (Player) event.getEntity();
            if (damager.getItemInHand().getType() == Material.WOOD_SWORD) {
                if (this.war.teamhelpers.sameTeam(damager, damaged)) {
                    this.war.sendMessage(damaged, ChatColor.AQUA + "You were healed by " + damager.getName());
                    this.war.sendMessage(damager, ChatColor.AQUA + "Healed " + damaged.getName() + "!");
                    damaged.setHealth(20);
                } else {
                    this.war.sendMessage(damager, ChatColor.AQUA + "You can't heal someone on the opposite team!");
                }
            }
            event.setCancelled(true);
            return;
        }
        if (event.getEntity() instanceof Player && this.war.medic.contains(event.getEntity())) {
            event.setDamage(event.getDamage() / 2);
        }
        if (event.getDamager() instanceof Arrow && (Arrow) event.getDamager() instanceof Player) {
            final Player shooter = (Player) ((Arrow) event.getDamager()).getShooter();
            if (shooter instanceof Player && event.getEntity() instanceof Player) {
                if (!this.handleDamage(shooter, (Player) event.getEntity())) {
                    event.setCancelled(true);
                }
            }
        }
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            final Player damager = (Player) event.getDamager();
            final Player damaged = (Player) event.getEntity();
            if (this.handleDamage(damager, damaged)) {
                if (this.war.pyro.contains(damager)) {
                    damaged.setFireTicks(40);
                }
            } else {
                event.setCancelled(true);
            }
        }
    }

}
