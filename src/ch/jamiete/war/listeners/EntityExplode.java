package ch.jamiete.war.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityExplodeEvent;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;

public class EntityExplode extends MasterListener {

    public EntityExplode(final War war) {
        super(war);
    }

    @EventHandler
    public void onEntityExplode(final EntityExplodeEvent event) {
        event.blockList().clear();
    }

}
