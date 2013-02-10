package ch.jamiete.war.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;

public class BlockBurn implements Listener {

    @EventHandler
    public void onBlockBurn(final BlockBurnEvent event) {
        event.setCancelled(true);
    }

}
