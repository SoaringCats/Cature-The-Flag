package ch.jamiete.war.listeners;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import ch.jamiete.war.War;


public class PlayerRespawn implements Listener {
	private War war;
	
	public PlayerRespawn(War war) {
		this.war = war;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		if (war.blu.size() == war.red.size()) {
			Random rand = new Random();
			int decider = rand.nextInt(2);
			war.assignment.assignPlayer(event.getPlayer(), decider);
		} else if (war.blu.size() < war.red.size()) {
			war.assignment.assignPlayer(event.getPlayer(), 0);
		} else if (war.red.size() < war.blu.size()) {
			war.assignment.assignPlayer(event.getPlayer(), 1);
		}
		if (war.teamhelpers.teamName(event.getPlayer()) == 0) {
			double x = war.getConfig().getDouble("blu-spawn-x");
			double y = war.getConfig().getDouble("blu-spawn-y");
			double z = war.getConfig().getDouble("blu-spawn-z");
			float yaw = war.getConfig().getInt("blu-spawn-yaw");
			float pitch = war.getConfig().getInt("blu-spawn-pitch");
			event.setRespawnLocation(new Location(event.getPlayer().getWorld(), x, y, z, yaw, pitch));
		} else {
			double x = war.getConfig().getDouble("red-spawn-x");
			double y = war.getConfig().getDouble("red-spawn-y");
			double z = war.getConfig().getDouble("red-spawn-z");
			float yaw = war.getConfig().getInt("red-spawn-yaw");
			float pitch = war.getConfig().getInt("red-spawn-pitch");
			event.setRespawnLocation(new Location(event.getPlayer().getWorld(), x, y, z, yaw, pitch));
		}
	}

}
