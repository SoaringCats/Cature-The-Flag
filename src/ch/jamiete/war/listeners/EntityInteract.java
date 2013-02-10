package ch.jamiete.war.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityInteractEvent;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;

public class EntityInteract extends MasterListener {

    public EntityInteract(final War war) {
        super(war);
    }

    @EventHandler
    public void onEntityInteract(final EntityInteractEvent event) {
        if (event.getEntityType() == EntityType.ENDERMAN) {
            event.setCancelled(true);
        }
    }

}
