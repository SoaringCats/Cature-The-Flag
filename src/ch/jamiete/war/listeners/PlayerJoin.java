package ch.jamiete.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ch.jamiete.war.War;

public class PlayerJoin implements Listener {
    private final War war;

    public PlayerJoin(final War war) {
        this.war = war;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(final PlayerJoinEvent event) {
        this.war.afk.put(event.getPlayer(), System.currentTimeMillis());
        final Player player = event.getPlayer();
        this.war.teamhelpers.toSpawn(player, 9);
        this.war.teamhelpers.clearTeams(player);
        if (!player.hasPlayedBefore()) {
            this.war.getServer().getScheduler().scheduleSyncDelayedTask(this.war, new Runnable() {
                @Override
                public void run() {
                    player.chat("[AUTO] I am new here! If I break the rules I acknowledge that I WILL be banned!");
                }
            }, 20L);
        }
        if (this.war.getConfig().getBoolean("has-started")) {
            this.war.teamhelpers.toSpawn(player, 9);
        } else {
            if (player.hasPermission("jtwar.admin")) {
                this.war.sendMessage(player, ChatColor.RED + "Teleporting you to spawn immediately to setup new map.");
                this.war.sendMessage(player, ChatColor.RED + "Please set new spawn points with /blu and /red");
                final int y = player.getWorld().getSpawnLocation().getBlockY() - 1;
                final Location loc = player.getLocation().clone();
                loc.setY(y);
                player.getWorld().getBlockAt(loc).setType(Material.STONE);
                player.teleport(player.getWorld().getSpawnLocation());
                player.setGameMode(GameMode.CREATIVE);
            }
        }
        event.setJoinMessage(this.war.getMessage() + ChatColor.GREEN + "+ " + ChatColor.BOLD + player.getName());
        this.war.sendMessage(player, ChatColor.RED + "Welcome to the war! To join type /join");
        this.war.sendMessage(player, ChatColor.RED + "For more information say /war");
    }

}
