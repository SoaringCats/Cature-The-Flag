package tk.nekotech.war;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import tk.nekotech.war.enums.Team;
import tk.nekotech.war.listeners.Cancellables;
import tk.nekotech.war.listeners.DamageControl;
import tk.nekotech.war.listeners.PlayerFlow;
import tk.nekotech.war.player.WarPlayer;
import tk.nekotech.war.runnables.PotionUpdate;

public class War extends JavaPlugin {
	public static Random random;
	private ArrayList<WarPlayer> players;
	
	public void onLoad() {
		if (getConfig().getString("version") == null) {
			this.saveDefaultConfig();
		} else {
			(new Updater(this)).updateConfig();
		}
	}
	
	public void onEnable() {
		// Initialize variables
		random = new Random();
		players = new ArrayList<WarPlayer>();
		
		// Register events
		new Cancellables(this);
		new DamageControl(this);
		new PlayerFlow(this);
		
		// Schedule runners
		new PotionUpdate(this);
	}
	
	public ChunkGenerator getDefaultWorldGenerator(String worldname, String uid) {
		return new WorldGenerator(this);
	}
	
	public WarPlayer getPlayer(String username) {
		for (WarPlayer player : players) {
			if (player.getUsername().equals(username)) {
				return player;
			}
		}
		return null;
	}
	
	public ArrayList<WarPlayer> getPlayers() {
		return players;
	}
	
	public void addPlayer(WarPlayer player) {
		players.add(player);
	}
	
	public void removePlayer(WarPlayer player) {
		player.cancel();
		players.remove(player);
	}
	
	public int getAmount(Team team) {
		int amount = 0;
		for (WarPlayer player : players) {
			if (player.getTeam() == team) {
				amount++;
			}
		}
		return amount;
	}
	
	public int getAmount() {
		return getServer().getOnlinePlayers().length;
	}
	
	public Team getNewTeam() {
		int blue = 0;
		int red = 0;
		for (WarPlayer player : players) {
			if (player.getTeam() == Team.BLUE) {
				blue++;
			} else {
				red++;
			}
		}
		if (blue > red) {
			return Team.RED;
		} else if (red > blue) {
			return Team.BLUE;
		} else {
			return random.nextBoolean() ? Team.RED : Team.BLUE;
		}
	}

}
