package ch.jamiete.war.listeners;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import ch.jamiete.war.War;

public class PlayerRespawn implements Listener {
    private final War war;

    public PlayerRespawn(final War war) {
        this.war = war;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRespawn(final PlayerRespawnEvent event) {
        if (this.war.blu.size() == this.war.red.size()) {
            final Random rand = new Random();
            final int decider = rand.nextInt(2);
            this.war.assignment.assignPlayer(event.getPlayer(), decider);
        } else if (this.war.blu.size() < this.war.red.size()) {
            this.war.assignment.assignPlayer(event.getPlayer(), 0);
        } else if (this.war.red.size() < this.war.blu.size()) {
            this.war.assignment.assignPlayer(event.getPlayer(), 1);
        }
        if (this.war.teamhelpers.teamName(event.getPlayer()) == 0) {
            final double x = this.war.getConfig().getDouble("blu-spawn-x");
            final double y = this.war.getConfig().getDouble("blu-spawn-y");
            final double z = this.war.getConfig().getDouble("blu-spawn-z");
            final float yaw = this.war.getConfig().getInt("blu-spawn-yaw");
            final float pitch = this.war.getConfig().getInt("blu-spawn-pitch");
            event.setRespawnLocation(new Location(event.getPlayer().getWorld(), x, y, z, yaw, pitch));
        } else {
            final double x = this.war.getConfig().getDouble("red-spawn-x");
            final double y = this.war.getConfig().getDouble("red-spawn-y");
            final double z = this.war.getConfig().getDouble("red-spawn-z");
            final float yaw = this.war.getConfig().getInt("red-spawn-yaw");
            final float pitch = this.war.getConfig().getInt("red-spawn-pitch");
            event.setRespawnLocation(new Location(event.getPlayer().getWorld(), x, y, z, yaw, pitch));
        }
    }

}
