package ch.jamiete.war.helpers;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import ch.jamiete.war.War;

public class QuickPlayer {
    private final War war;

    public QuickPlayer(final War war) {
        this.war = war;
    }

    public void clearAttachments(final Player player) {
        this.war.admins.remove(player);
        this.war.afk.remove(player);
        this.war.blu.remove(player);
        this.war.inventory.remove(player);
        this.war.medic.remove(player);
        this.war.monster.remove(player);
        this.war.pyro.remove(player);
        this.war.red.remove(player);
    }

    public void playerLeave(final Player player) {
        if (this.war.getServer().getOnlinePlayers().length == 1) {
            this.war.getLogger().info("Performing garbage collection on all worlds.");
            for (final World world : this.war.getServer().getWorlds()) {
                this.war.getLogger().info("Clearing " + world.getEntities().size() + " entities from " + world.getName() + " for garbage collection!");
                for (final Entity entity : world.getEntities()) {
                    entity.remove();
                }
            }
            this.war.getLogger().info("Garbage collection complete.");
        }
    }

}
