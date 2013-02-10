package ch.jamiete.war.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;

public class ProjectileHit extends MasterListener {

    public ProjectileHit(final War war) {
        super(war);
    }

    @EventHandler
    public void onProjectileHit(final ProjectileHitEvent event) {
        // TODO
    }

}
