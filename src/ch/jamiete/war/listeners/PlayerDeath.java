package ch.jamiete.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import ch.jamiete.war.War;

public class PlayerDeath implements Listener {
    private final War war;

    public PlayerDeath(final War war) {
        this.war = war;
    }

    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent event) {
        this.war.getLogger().info(event.getEntity().getName() + " died: " + event.getDeathMessage());
        this.war.smitePlayer(event.getEntity());
        event.setDeathMessage(this.war.getMessage() + ChatColor.AQUA + event.getDeathMessage());
    }

}
