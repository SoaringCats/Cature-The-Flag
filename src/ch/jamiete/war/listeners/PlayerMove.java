package ch.jamiete.war.listeners;

import java.util.Date;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;

public class PlayerMove extends MasterListener {

    public PlayerMove(final War war) {
        super(war);
    }

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {
        this.war.afk.put(event.getPlayer(), new Date().getTime());
    }

}
