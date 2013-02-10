package ch.jamiete.war.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBurnEvent;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;

public class BlockBurn extends MasterListener {

    public BlockBurn(final War war) {
        super(war);
    }

    @EventHandler
    public void onBlockBurn(final BlockBurnEvent event) {
        event.setCancelled(true);
    }

}
