package ch.jamiete.war.runnables;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import ch.jamiete.war.War;

public class ArrowRemoval implements Runnable {
    private final War war;

    public ArrowRemoval(final War war) {
        this.war = war;
    }

    @Override
    public void run() {
        for (final Entity entity : this.war.mainWorld.getEntities()) {
            if (entity instanceof Arrow) {
                entity.remove();
            }
        }
    }

}
