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
	public void onPlayerJoin(final PlayerJoinEvent e) {
		
		// have this at top so it can get overriden if required
		double x = war.getConfig().getDouble("spec-spawn-x");
		double y = war.getConfig().getDouble("spec-spawn-y");
		double z = war.getConfig().getDouble("spec-spawn-z");
		float yaw = war.getConfig().getInt("spec-spawn-yaw");
		float pitch = war.getConfig().getInt("spec-spawn-pitch");
		e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), x, y, z, yaw, pitch));
		war.teamhelpers.clearTeams(e.getPlayer());
		war.on++;
		war.online.add(e.getPlayer());
		if (!e.getPlayer().hasPlayedBefore()) {
			war.getServer().getScheduler().scheduleSyncDelayedTask(war, new Runnable() {
				@Override
				public void run() {
					e.getPlayer().chat("[AUTO] I am new here! If I break the rules I acknowledge that I WILL be banned!");
				}
			}, 20L);
		}
		
		if (war.getConfig().getBoolean("has-started")) {
			double sx = war.getConfig().getDouble("spec-spawn-x");
			double sy = war.getConfig().getDouble("spec-spawn-y");
			double sz = war.getConfig().getDouble("spec-spawn-z");
			float syaw = war.getConfig().getInt("spec-spawn-yaw");
			float spitch = war.getConfig().getInt("spec-spawn-pitch");
			e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), sx, sy, sz, syaw, spitch));
		} else {
			if (e.getPlayer().hasPermission("jtwar.admin")) {
				e.setJoinMessage(ChatColor.YELLOW + e.getPlayer().getName() + " is joining as an admin on the new map!");
				e.getPlayer().sendMessage(ChatColor.RED + "[jtWar] Teleporting you to spawn immediately to setup new map.");
				e.getPlayer().sendMessage(ChatColor.RED + "[jtWar] Please set new spawn points with /blu and /red");
				e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
				e.getPlayer().setGameMode(GameMode.CREATIVE);
			}
		}
		
		e.setJoinMessage(ChatColor.YELLOW + e.getPlayer().getName() + " joins the war!");
		e.getPlayer().sendMessage(ChatColor.RED + "Welcome to the war! To join type /join");
		e.getPlayer().sendMessage(ChatColor.RED + "For more information say /war");
		
	}

}
