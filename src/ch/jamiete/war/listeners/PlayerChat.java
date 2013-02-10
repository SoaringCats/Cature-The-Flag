package ch.jamiete.war.listeners;

import java.util.Date;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;
import ch.jamiete.war.helpers.WarPlayer;
import ch.jamiete.war.helpers.WarValues;

public class PlayerChat extends MasterListener {

    public PlayerChat(final War war) {
        super(war);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(final AsyncPlayerChatEvent event) {
        final WarPlayer wplayer = this.war.getHelper().getPlayerExact(event.getPlayer().getName());
        this.war.afk.put(event.getPlayer(), new Date().getTime());

        event.setFormat(WarValues.MESSAGE_PREFIX + wplayer.getTeam().getColor() + "[TEAM]" + ChatColor.WHITE + " %s > %s");
    }
}
