package tk.nekotech.war.events;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import tk.nekotech.war.War;

public class PlayerRespawn implements Listener {
	private War war;
	
	public PlayerRespawn(War war) {
		this.war = war;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		if (war.blu.size() == war.red.size()) {
			Random rand = new Random();
			int decider = rand.nextInt(2);
			war.assignment.assignPlayer(e.getPlayer(), decider);
		} else if (war.blu.size() < war.red.size()) {
			war.assignment.assignPlayer(e.getPlayer(), 0);
		} else if (war.red.size() < war.blu.size()) {
			war.assignment.assignPlayer(e.getPlayer(), 1);
		}
		if (war.teamhelpers.teamName(e.getPlayer()) == 0) {
			double x = war.getConfig().getDouble("blu-spawn-x");
			double y = war.getConfig().getDouble("blu-spawn-y");
			double z = war.getConfig().getDouble("blu-spawn-z");
			float yaw = war.getConfig().getInt("blu-spawn-yaw");
			float pitch = war.getConfig().getInt("blu-spawn-pitch");
			e.setRespawnLocation(new Location(e.getPlayer().getWorld(), x, y, z, yaw, pitch));
		} else {
			double x = war.getConfig().getDouble("red-spawn-x");
			double y = war.getConfig().getDouble("red-spawn-y");
			double z = war.getConfig().getDouble("red-spawn-z");
			float yaw = war.getConfig().getInt("red-spawn-yaw");
			float pitch = war.getConfig().getInt("red-spawn-pitch");
			e.setRespawnLocation(new Location(e.getPlayer().getWorld(), x, y, z, yaw, pitch));
		}
	}

}
