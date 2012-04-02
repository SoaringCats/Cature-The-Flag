package tk.nekotech.war;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import tk.nekotech.war.events.BlockBreak;
import tk.nekotech.war.events.BlockPlace;
import tk.nekotech.war.events.CreatureSpawn;
import tk.nekotech.war.events.EntityDamage;
import tk.nekotech.war.events.EntityDamageByEntity;
import tk.nekotech.war.events.EntityDeath;
import tk.nekotech.war.events.EntityExplode;
import tk.nekotech.war.events.PlayerChat;
import tk.nekotech.war.events.PlayerJoin;
import tk.nekotech.war.events.PlayerMove;
import tk.nekotech.war.events.PlayerQuit;
import tk.nekotech.war.events.PlayerRespawn;
import tk.nekotech.war.helpers.Armor;
import tk.nekotech.war.helpers.Assignment;
import tk.nekotech.war.helpers.Color;
import tk.nekotech.war.helpers.Mob;
import tk.nekotech.war.helpers.Potions;
import tk.nekotech.war.helpers.TeamHelpers;

public class War extends JavaPlugin {
	
	public Armor armor = new Armor(this);
	public Assignment assignment = new Assignment(this);
	public Color color = new Color(this);
	public Mob mob = new Mob(this);
	public Potions potions = new Potions(this);
	public TeamHelpers teamhelpers = new TeamHelpers(this);
	
	public BlockBreak blockbreak = new BlockBreak(this);
	public BlockPlace blockplace = new BlockPlace(this);
	public CreatureSpawn creaturespawn = new CreatureSpawn(this);
	public EntityDamage entitydamage = new EntityDamage(this);
	public EntityDamageByEntity entitydamagebyentity = new EntityDamageByEntity(this);
	public EntityDeath entitydeath = new EntityDeath(this);
	public EntityExplode entityexplode = new EntityExplode();
	public PlayerChat playerchat = new PlayerChat(this);
	public PlayerJoin playerjoin = new PlayerJoin(this);
	public PlayerMove playermove = new PlayerMove(this);
	public PlayerQuit playerquit = new PlayerQuit(this);
	public PlayerRespawn playerrespawn = new PlayerRespawn(this);
	
	public ArrayList<Player> online;
	public ArrayList<Player> pyro;
	public ArrayList<Player> monster;
	public ArrayList<Player> blu;
	public ArrayList<Player> red;
	
	public int on = 0;
	public int max = 0;
	public int dead = 0;
	
	public void onEnable() {
		
		getLogger().info("Enabled!");
		
		getCommand("war").setExecutor(new Commands(this));
		getCommand("join").setExecutor(new Commands(this));
		getCommand("blu").setExecutor(new Commands(this));
		getCommand("red").setExecutor(new Commands(this));
		getCommand("ready").setExecutor(new Commands(this));
		getCommand("spectate").setExecutor(new Commands(this));
		getCommand("reset").setExecutor(new Commands(this));
		
		PluginManager p = getServer().getPluginManager();
		p.registerEvents(blockbreak, this);
		p.registerEvents(blockplace, this);
		p.registerEvents(creaturespawn, this);
		p.registerEvents(entitydamage, this);
		p.registerEvents(entitydamagebyentity, this);
		p.registerEvents(entitydeath, this);
		p.registerEvents(entityexplode, this);
		p.registerEvents(playerchat, this);
		p.registerEvents(playerjoin, this);
		p.registerEvents(playermove, this);
		p.registerEvents(playerquit, this);
		p.registerEvents(playerrespawn, this);
		
		max = getServer().getMaxPlayers();
		
		online = new ArrayList<Player>();
		pyro = new ArrayList<Player>();
		monster = new ArrayList<Player>();
		blu = new ArrayList<Player>();
		red = new ArrayList<Player>();
		
		if (!getConfig().getBoolean("has-started")) {
			getLogger().warning("This is the first logged startup event (did you clear your config?)");
			getLogger().warning("Please make sure you set the coordinates for the new spawn areas.");
		}
		getLogger().info("Please do not modify the config as it changes often during gameplay.");
		
		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				int worlds = 0;
				int arrows = 0;
				for (World w : getServer().getWorlds()) {
					w.setStorm(false);
					w.setTime(14000);
					worlds++;
					for (Entity e : w.getEntities()) {
						if (e instanceof Arrow) {
							e.remove();
							arrows++;
						}
					}
				}
				if (getServer().getOnlinePlayers().length < 0) getLogger().info("Set time to night in " + worlds + " worlds and cleaned " + arrows + " arrows up!");
			}
		}, 40L, 4800L);
		
		for (Player player : getServer().getOnlinePlayers()) {
			player.kickPlayer(ChatColor.AQUA + "Please rejoin in 5 seconds :)");
		}
		
	}
	
	public void onDisable() {
		
		getLogger().info("Disabled!");
		
	}
	
	public ChunkGenerator getDefaultWorldGenerator(String worldname, String uid) {
		
		return new Gen(this);
		
	}

}
