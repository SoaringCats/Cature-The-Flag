package ch.jamiete.war.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockIgniteEvent;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;

public class BlockIgnite extends MasterListener {

    public BlockIgnite(final War war) {
        super(war);
    }

    @EventHandler
    public void onBlockIgnite(final BlockIgniteEvent event) {
        event.setCancelled(true);
    }

}
