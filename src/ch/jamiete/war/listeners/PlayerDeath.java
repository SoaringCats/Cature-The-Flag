package ch.jamiete.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;
import ch.jamiete.war.helpers.WarValues;

public class PlayerDeath extends MasterListener {

    public PlayerDeath(final War war) {
        super(war);
    }

    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent event) {
        this.war.getLogger().info(event.getEntity().getName() + " died: " + event.getDeathMessage());
        event.setDeathMessage(WarValues.MESSAGE_PREFIX + ChatColor.AQUA + event.getDeathMessage());
    }

}
