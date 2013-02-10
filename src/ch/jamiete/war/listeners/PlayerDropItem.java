package ch.jamiete.war.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;

public class PlayerDropItem extends MasterListener {

    public PlayerDropItem(final War war) {
        super(war);
    }

    @EventHandler
    public void onPlayerDropItem(final PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

}
