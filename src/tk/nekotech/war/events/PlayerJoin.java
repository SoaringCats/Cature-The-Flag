package tk.nekotech.war.events;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
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
		double x = war.getConfig().getDouble("spec-spawn-x");
		double y = war.getConfig().getDouble("spec-spawn-y");
		double z = war.getConfig().getDouble("spec-spawn-z");
		float yaw = war.getConfig().getInt("spec-spawn-yaw");
		float pitch = war.getConfig().getInt("spec-spawn-pitch");
		event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), x, y, z, yaw, pitch));
		war.teamhelpers.clearTeams(event.getPlayer());
		war.on++;
		war.online.add(event.getPlayer());
		if (!event.getPlayer().hasPlayedBefore()) {
			war.getServer().getScheduler().scheduleSyncDelayedTask(war, new Runnable() {
				@Override
				public void run() {
					event.getPlayer().chat("[AUTO] I am new here! If I break the rules I acknowledge that I WILL be banned!");
				}
			}, 20L);
		}
		if (war.getConfig().getBoolean("has-started")) {
			double sx = war.getConfig().getDouble("spec-spawn-x");
			double sy = war.getConfig().getDouble("spec-spawn-y");
			double sz = war.getConfig().getDouble("spec-spawn-z");
			float syaw = war.getConfig().getInt("spec-spawn-yaw");
			float spitch = war.getConfig().getInt("spec-spawn-pitch");
			event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), sx, sy, sz, syaw, spitch));
		} else {
			if (event.getPlayer().hasPermission("jtwar.admin")) {
				event.setJoinMessage(ChatColor.YELLOW + event.getPlayer().getName() + " is joining as an admin on the new map!");
				event.getPlayer().sendMessage(ChatColor.RED + "[jtWar] Teleporting you to spawn immediately to setup new map.");
				event.getPlayer().sendMessage(ChatColor.RED + "[jtWar] Please set new spawn points with /blu and /red");
				event.getPlayer().teleport(event.getPlayer().getWorld().getSpawnLocation());
				event.getPlayer().setGameMode(GameMode.CREATIVE);
			}
		}
		event.setJoinMessage(ChatColor.YELLOW + event.getPlayer().getName() + " joins the war!");
		event.getPlayer().sendMessage(ChatColor.RED + "Welcome to the war! To join type /join");
		event.getPlayer().sendMessage(ChatColor.RED + "For more information say /war");
	}

}
