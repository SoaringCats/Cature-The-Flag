package tk.nekotech.war.player;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import tk.nekotech.war.War;
import tk.nekotech.war.enums.PlayerClass;
import tk.nekotech.war.enums.Team;
import tk.nekotech.war.runnables.PlayerMessage;

public class WarPlayer {
	private Player player;
	private War war;
	private Team team;
	private PlayerClass playerclass;
	private int cancellable;
	private List<PotionEffect> potions;
	private int kills = 0;
	private int deaths = 0;
	
	public WarPlayer(Player player, Team team, War war) {
		this.player = player;
		this.team = team;
		this.war = war;
		this.playerclass = PlayerClass.HEAVY;
		PlayerClass.giveClassItems(this);
		player.teleport(Team.getSpawn(war.getConfig().getString("location." + team.toString()), war));
		this.cancellable = war.getServer().getScheduler().scheduleSyncRepeatingTask(war, new PlayerMessage(this, war), 20L, 20L);
		this.potions = PlayerClass.getPotionEffects(this);
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
		// TODO: Detect if player is away from spawn and don't do this if they are.
		this.potions = PlayerClass.getPotionEffects(this);
		PlayerClass.giveClassItems(this);
	}
	
	public void addPotion(PotionEffect effect) {
		this.potions.add(effect);
	}
	
	public void removePotion(PotionEffect effect) {
		this.potions.remove(effect);
	}
	
	public List<PotionEffect> getPotions() {
		return this.potions;
	}
	
	public void cancel() {
		war.getServer().getScheduler().cancelTask(cancellable);
	}
	
	public void addKill() {
		kills++;
	}
	
	public int getKills() {
		return kills;
	}
	
	public void addDeath() {
		deaths++;
	}
	
	public int getDeaths() {
		return deaths;
	}

	public void smitePlayer() {
		final Location origLoc = player.getLocation();
		for (int i = 0; i < 5; i++) {
			war.getServer().getScheduler().scheduleSyncDelayedTask(war, new Runnable() {
				public void run() {
					Location loc = origLoc.clone();
					loc.setX(loc.getX() + War.random.nextDouble() * 20 - 10);
					loc.setZ(loc.getZ() + War.random.nextDouble() * 20 - 10);
					player.getWorld().strikeLightning(loc);
				}
			}, Math.max(0, i * 3 + War.random.nextInt(10) - 5));
		}
	}

}
