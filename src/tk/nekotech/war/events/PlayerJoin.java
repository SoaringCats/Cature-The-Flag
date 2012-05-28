package tk.nekotech.war.events;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tk.nekotech.war.War;

public class PlayerJoin implements Listener {
	private War war;
	
	public PlayerJoin(War war) {
		this.war = war;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerJoin(final PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		war.teamhelpers.toSpawn(player, 9);
		war.teamhelpers.clearTeams(player);
		war.on++;
		war.online.add(player);
		if (!player.hasPlayedBefore()) {
			war.getServer().getScheduler().scheduleSyncDelayedTask(war, new Runnable() {
				@Override
				public void run() {
					player.chat("[AUTO] I am new here! If I break the rules I acknowledge that I WILL be banned!");
				}
			}, 20L);
		}
		if (war.getConfig().getBoolean("has-started")) {
			war.teamhelpers.toSpawn(player, 9);
		} else {
			if (player.hasPermission("jtwar.admin")) {
				war.sendMessage(player, ChatColor.RED + "[jtWar] Teleporting you to spawn immediately to setup new map.");
				war.sendMessage(player, ChatColor.RED + "[jtWar] Please set new spawn points with /blu and /red");
				int y = player.getWorld().getSpawnLocation().getBlockY() - 1;
				Location loc = player.getLocation().clone();
				loc.setY(y);
				player.getWorld().getBlockAt(loc).setType(Material.STONE);
				player.teleport(player.getWorld().getSpawnLocation());
				player.setGameMode(GameMode.CREATIVE);
			}
		}
		event.setJoinMessage(war.getMessage() + ChatColor.GREEN + " + " + ChatColor.BOLD + player.getName());
		war.sendMessage(player, ChatColor.RED + "Welcome to the war! To join type /join");
		war.sendMessage(player, ChatColor.RED + "For more information say /war");
	}

}
