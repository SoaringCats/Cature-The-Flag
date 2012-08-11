package tk.nekotech.war.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import tk.nekotech.war.War;
import tk.nekotech.war.enums.PlayerClass;
import tk.nekotech.war.enums.Team;

public class WarPlayer {
	private Player player;
	private War war;
	private Team team;
	private PlayerClass playerclass;
	
	public WarPlayer(Player player, War war) {
		this.player = player;
		this.war = war;
		this.team = war.getNewTeam();
		this.playerclass = PlayerClass.HEAVY;
		player.teleport(Team.getSpawn(war.getConfig().getString("location." + team.toString()), war));
	}
	
	public WarPlayer(Player player, Team team, War war) {
		this.player = player;
		this.team = team;
		this.war = war;
		this.playerclass = PlayerClass.HEAVY;
		player.teleport(Team.getSpawn(war.getConfig().getString("location." + team.toString()), war));
	}

	public Player getPlayer() {
		return player;
	}

	public String getUsername() {
		return player.getName();
	}

	public Location getLocation() {
		return player.getLocation();
	}
	
	public Team getTeam() {
		return team;
	}
	
	public PlayerClass getPlayerClass() {
		return playerclass;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public void setClass(PlayerClass playerclass) {
		this.playerclass = playerclass;
	}

	public void smitePlayer() {
		final Location origLoc = player.getLocation();
		for (int i = 0; i < 5; i++) {
			war.getServer().getScheduler().scheduleSyncDelayedTask(war, new Runnable() {
				public void run() {
					Location loc = origLoc.clone();
					loc.setX(loc.getX() + war.random.nextDouble() * 20 - 10);
					loc.setZ(loc.getZ() + war.random.nextDouble() * 20 - 10);
					player.getWorld().strikeLightning(loc);
				}
			}, Math.max(0, i * 3 + war.random.nextInt(10) - 5));
		}
	}

}
