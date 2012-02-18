package tk.nekotech.war;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class War extends JavaPlugin implements Listener {
	
	public void onEnable() {
		getLogger().info("Enabled!");
		if (!getConfig().getBoolean("has_started")) {
			getLogger().warning("This is the first logged startup event (did you clear your config?");
			getLogger().warning("Please make sure you set the coordinates for the new spawn areas.");
			getLogger().warning("You will be teleported instantly to 0,2,0 and will fall to the ground on join.");
		}
		getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	public void onDisable() {
		getLogger().info("Disabled!");
	}
	
	public ChunkGenerator getDefaultWorldGenerator(String worldname, String uid) {
		return new Gen(this);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void holyShitNewMap(PlayerJoinEvent e) {
		if (!getConfig().getBoolean("has_started")) {
			if (e.getPlayer().hasPermission("jtwar.admin")) {
				e.setJoinMessage(ChatColor.YELLOW + e.getPlayer().getName() + " is joining as an admin on the new map!");
				e.getPlayer().sendMessage(ChatColor.RED + "[jtWAR] Teleporting you to spawn immediately to setup new map.");
				e.getPlayer().sendMessage(ChatColor.RED + "[jtWAR] Please set new spawn points with /blu and /red");
				e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
				e.getPlayer().setGameMode(GameMode.CREATIVE);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void motd(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		for (Player p : this.getServer().getOnlinePlayers()) {
			if (p.hasPermission("jtwar.admin")) {
				StringBuilder mrow = new StringBuilder();
				if (e.getPlayer().hasPlayedBefore()) {
					mrow.append(", HasPlayed");
				} else {
					mrow.append(", New");
				}
				if (e.getPlayer().hasPermission("jtwar.admin")) {
					mrow.append(", Admin");
				}
				p.sendMessage(ChatColor.YELLOW + "J: " + e.getPlayer().getName() + mrow);
			} else {
				p.sendMessage(ChatColor.YELLOW + e.getPlayer().getName() + " has joined the War!");
			}
		}
		e.getPlayer().sendMessage(ChatColor.RED + "Welcome to the war! To join type /join");
		e.getPlayer().sendMessage(ChatColor.RED + "For more information say /war");
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void dontTouchThat(BlockBreakEvent e) {
		if (!e.getPlayer().hasPermission("jtwar.admin")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "The objective of the game is to kill all of the other team");
			e.getPlayer().sendMessage(ChatColor.RED + "To do this you don't need to break blocks!");
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void dontPlaceThat(BlockPlaceEvent e) {
		if (!e.getPlayer().hasPermission("jtwar.admin")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "The objective of the game is to kill all of the other team");
			e.getPlayer().sendMessage(ChatColor.RED + "To do this you don't need to place blocks!");
		}
	}

}
