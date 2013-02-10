package ch.jamiete.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;
import ch.jamiete.war.helpers.WarValues;

public class PlayerQuit extends MasterListener {

    public PlayerQuit(final War war) {
        super(war);
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        this.war.afk.remove(event.getPlayer());

        if (this.war.getPlayers().length == 0) {
            this.war.getHelper().cleanWorlds();
        }

        event.setQuitMessage(WarValues.MESSAGE_PREFIX + ChatColor.RED + "- " + ChatColor.BOLD + event.getPlayer().getName());
    }

}
