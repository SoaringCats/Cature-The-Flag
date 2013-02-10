package ch.jamiete.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;
import ch.jamiete.war.helpers.Team;
import ch.jamiete.war.helpers.WarPlayer;
import ch.jamiete.war.helpers.WarValues;

public class PlayerJoin extends MasterListener {

    public PlayerJoin(final War war) {
        super(war);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player bplayer = event.getPlayer();
        final WarPlayer player = new WarPlayer(bplayer);

        this.war.afk.put(bplayer, System.currentTimeMillis());

        if (this.war.getConfig().getBoolean("has-started")) {
            bplayer.teleport(this.war.getHelper().getTeamSpawn(Team.SPECTATOR));
        } else {
            if (player.hasPermission("jtwar.admin")) {
                player.sendMessage(ChatColor.RED + "Teleporting you to spawn so you can setup a new map.");
                player.sendMessage(ChatColor.RED + "Please set the spawn points with /blu and /red");

                bplayer.teleport(this.war.mainWorld.getSpawnLocation().add(0, 10, 0));
                this.war.mainWorld.getSpawnLocation().getBlock().setType(Material.BRICK);

                bplayer.setGameMode(GameMode.CREATIVE);
            }
        }

        event.setJoinMessage(WarValues.MESSAGE_PREFIX + ChatColor.GREEN + "+ " + ChatColor.BOLD + player.getName());
        player.sendMessage(ChatColor.RED + "Welcome to the war! To join type /join");
        player.sendMessage(ChatColor.RED + "For more information say /war");
    }

}
