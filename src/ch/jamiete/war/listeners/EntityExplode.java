package ch.jamiete.war.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplode implements Listener {

    @EventHandler
    public void onEntityExplode(final EntityExplodeEvent event) {
        event.blockList().clear();
    }

}
