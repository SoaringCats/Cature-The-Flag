package ch.jamiete.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import ch.jamiete.war.War;

public class PlayerQuit implements Listener {
    private final War war;

    public PlayerQuit(final War war) {
        this.war = war;
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        this.war.quickplayer.clearAttachments(event.getPlayer());
        this.war.quickplayer.playerLeave(event.getPlayer());
        event.setQuitMessage(this.war.getMessage() + ChatColor.RED + "- " + ChatColor.BOLD + event.getPlayer().getName());
    }

}
