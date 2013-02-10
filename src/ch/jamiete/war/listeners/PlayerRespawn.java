package ch.jamiete.war.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerRespawnEvent;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;

public class PlayerRespawn extends MasterListener {

    public PlayerRespawn(final War war) {
        super(war);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRespawn(final PlayerRespawnEvent event) {
        this.war.getHelper().assignPlayer(this.war.getHelper().getPlayerExact(event.getPlayer().getName()), this.war.getHelper().getAvailableTeam());
    }

}
