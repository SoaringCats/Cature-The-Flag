package ch.jamiete.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;
import ch.jamiete.war.helpers.PlayerClass;
import ch.jamiete.war.helpers.WarPlayer;

public class EntityDamageByEntity extends MasterListener {

    public EntityDamageByEntity(final War war) {
        super(war);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            final WarPlayer damager = this.war.getHelper().getPlayerExact(((Player) event.getDamager()).getName());

            if (!damager.isOnTeam()) {
                event.setCancelled(true);
                damager.sendMessage(ChatColor.RED + "Join a team first!");
                return;
            }

            if (event.getEntity() instanceof Player) {
                final WarPlayer damaged = this.war.getHelper().getPlayerExact(((Player) event.getEntity()).getName());

                if (!damaged.isOnTeam()) {
                    event.setCancelled(true);
                    damager.getPlayer().damage(1);
                    return;
                }

                if (damaged.getTeam() == damager.getTeam()) {
                    event.setCancelled(true);
                    return;
                }
            }

            if (PlayerClass.PYRO == damager.getPlayerClass()) {
                event.getEntity().setFireTicks(100); // 5s
            }
        }
    }
}
