package ch.jamiete.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import ch.jamiete.war.War;

public class PlayerKick implements Listener {
    private final War war;

    public PlayerKick(final War war) {
        this.war = war;
    }

    @EventHandler
    public void onPlayerKick(final PlayerKickEvent event) {
        event.setLeaveMessage(null);
        this.war.quickplayer.clearAttachments(event.getPlayer());
        this.war.quickplayer.playerLeave(event.getPlayer());
        if (event.getReason().equals("Kicked by administrator.")) {
            event.setReason(ChatColor.AQUA + "Kicked!");
        } else {
            event.setReason(ChatColor.AQUA + event.getReason());
        }
        for (final Player player : this.war.getServer().getOnlinePlayers()) {
            if (player.hasPermission("jtwar.admin")) {
                this.war.sendMessage(player, ChatColor.RED + "- " + ChatColor.BOLD + event.getPlayer().getName() + ChatColor.RESET + ChatColor.RED + ": " + event.getReason());
            } else {
                this.war.sendMessage(player, ChatColor.RED + "- " + ChatColor.BOLD + event.getPlayer().getName());
            }
        }
        this.war.getLogger().info(event.getPlayer().getName() + " kicked from server for " + event.getReason());
    }

}
