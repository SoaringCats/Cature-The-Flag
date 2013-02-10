package ch.jamiete.war.helpers;

import java.util.Random;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import ch.jamiete.war.War;

public class Mob {
    @SuppressWarnings("unused")
    private final War war;

    public Mob(final War war) {
        this.war = war;
    }

    public boolean isAllowed(final EntityType type) {
        switch (type) {
            case SPIDER:
                return true;
            case CAVE_SPIDER:
                return true;
            case SKELETON:
                return true;
            case CREEPER:
                return true;
            case PIG_ZOMBIE:
                return true;
            case ZOMBIE:
                return true;
            default:
                return false;

        }
    }

    public void killMob(final Entity entity) {
        if (entity != null) {
            entity.getWorld().strikeLightningEffect(entity.getLocation());
            entity.remove();
        }
    }

    public EntityType randoMob() {
        final Random rand = new Random();
        final int mobID = rand.nextInt(9);
        switch (mobID) {
            case 0:
                return EntityType.SPIDER;
            case 1:
                return EntityType.CAVE_SPIDER;
            case 2:
                return EntityType.SKELETON;
            case 3:
                return EntityType.CREEPER;
            case 4:
                return EntityType.PIG_ZOMBIE;
            case 5:
                return EntityType.ZOMBIE;
            case 6:
                return EntityType.ZOMBIE;
            case 7:
                return EntityType.ZOMBIE;
            case 8:
                return EntityType.ZOMBIE;
            default:
                return null;
        }
    }

}
