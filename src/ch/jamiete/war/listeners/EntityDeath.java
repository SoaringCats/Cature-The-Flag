package ch.jamiete.war.listeners;

import java.util.Random;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;
import ch.jamiete.war.helpers.Team;
import ch.jamiete.war.helpers.WarPlayer;

public class EntityDeath extends MasterListener {

    public EntityDeath(final War war) {
        super(war);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDeath(final EntityDeathEvent event) {
        event.setDroppedExp(0);
        event.getDrops().clear();

        if (event.getEntity().getLastDamageCause().getCause() == DamageCause.BLOCK_EXPLOSION) {
            for (int a = 0; a < 10; a++) {
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.TNT));
            }
        }

        final Random random = new Random();
        Material food = null;
        switch (random.nextInt(22)) {
            case 0:
                food = Material.APPLE;
                break;
            case 1:
                food = Material.MUSHROOM_SOUP;
                break;
            case 2:
                food = Material.BREAD;
                break;
            case 3:
                food = Material.PORK;
                break;
            case 4:
                food = Material.GRILLED_PORK;
                break;
            case 5:
                food = Material.GOLDEN_APPLE;
                break;
            case 6:
                food = Material.RAW_FISH;
                break;
            case 7:
                food = Material.COOKED_FISH;
                break;
            case 8:
                food = Material.COOKIE;
                break;
            case 9:
                food = Material.MELON;
                break;
            case 10:
                food = Material.RAW_BEEF;
                break;
            case 11:
                food = Material.COOKED_BEEF;
                break;
            case 12:
                food = Material.RAW_CHICKEN;
                break;
            case 13:
                food = Material.COOKED_CHICKEN;
                break;
            case 14:
                food = Material.ROTTEN_FLESH;
                break;
            case 15:
                food = Material.SPIDER_EYE;
                break;
            case 16:
                food = Material.CARROT_ITEM;
                break;
            case 17:
                food = Material.POTATO_ITEM;
                break;
            case 18:
                food = Material.BAKED_POTATO;
                break;
            case 19:
                food = Material.POISONOUS_POTATO;
                break;
            case 20:
                food = Material.GOLDEN_CARROT;
                break;
            case 21:
                food = Material.PUMPKIN_PIE;
                break;
        }
        event.getDrops().add(new ItemStack(food, random.nextInt(5) + 1));

        if (event.getEntity() instanceof Player) {
            final WarPlayer player = this.war.getHelper().getPlayerExact(((Player) event.getEntity()).getName());
            player.setTeam(Team.SPECTATOR);
        }
    }
}
