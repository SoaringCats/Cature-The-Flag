package tk.nekotech.war;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import tk.nekotech.war.commands.AdminCommands;
import tk.nekotech.war.commands.JoinCommand;
import tk.nekotech.war.commands.SetupCommands;
import tk.nekotech.war.commands.WarCommand;
import tk.nekotech.war.events.BlockBreak;
import tk.nekotech.war.events.BlockBurn;
import tk.nekotech.war.events.BlockIgnite;
import tk.nekotech.war.events.BlockPlace;
import tk.nekotech.war.events.CreatureSpawn;
import tk.nekotech.war.events.EntityDamage;
import tk.nekotech.war.events.EntityDamageByEntity;
import tk.nekotech.war.events.EntityDeath;
import tk.nekotech.war.events.EntityExplode;
import tk.nekotech.war.events.InventoryClick;
import tk.nekotech.war.events.InventoryClose;
import tk.nekotech.war.events.PlayerChat;
import tk.nekotech.war.events.PlayerDeath;
import tk.nekotech.war.events.PlayerDropItem;
import tk.nekotech.war.events.PlayerInteract;
import tk.nekotech.war.events.PlayerJoin;
import tk.nekotech.war.events.PlayerKick;
import tk.nekotech.war.events.PlayerMove;
import tk.nekotech.war.events.PlayerQuit;
import tk.nekotech.war.events.PlayerRespawn;
import tk.nekotech.war.events.ProjectileHit;
import tk.nekotech.war.events.SignChange;
import tk.nekotech.war.events.WeatherChange;
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
	
	public ArrayList<Player> online;
	public ArrayList<Player> pyro;
	public ArrayList<Player> monster;
	public ArrayList<Player> blu;
	public ArrayList<Player> red;
	public ArrayList<Player> inventory;
	public ArrayList<Player> admins;
	
	public int on = 0;
	public int max = 0;
	public int dead = 0;
	
	public void onEnable() {
		getCommand("kick").setExecutor(new AdminCommands(this));
		getCommand("ban").setExecutor(new AdminCommands(this));
		getCommand("unban").setExecutor(new AdminCommands(this));
		getCommand("admin").setExecutor(new AdminCommands(this));
		getCommand("smite").setExecutor(new AdminCommands(this));
		getCommand("join").setExecutor(new JoinCommand(this));
		getCommand("blu").setExecutor(new SetupCommands(this));
		getCommand("red").setExecutor(new SetupCommands(this));
		getCommand("ready").setExecutor(new SetupCommands(this));
		getCommand("spectate").setExecutor(new SetupCommands(this));
		getCommand("reset").setExecutor(new SetupCommands(this));
		getCommand("war").setExecutor(new WarCommand(this));
		
		PluginManager p = getServer().getPluginManager();
		p.registerEvents(new BlockBreak(this), this);
		p.registerEvents(new BlockBurn(), this);
		p.registerEvents(new BlockIgnite(), this);
		p.registerEvents(new BlockPlace(this), this);
		p.registerEvents(new CreatureSpawn(this), this);
		p.registerEvents(new EntityDamage(this), this);
		p.registerEvents(new EntityDamageByEntity(this), this);
		p.registerEvents(new EntityDeath(this), this);
		p.registerEvents(new EntityExplode(), this);
		p.registerEvents(new InventoryClick(), this);
		p.registerEvents(new InventoryClose(this), this);
		p.registerEvents(new PlayerChat(this), this);
		p.registerEvents(new PlayerDeath(this), this);
		p.registerEvents(new PlayerDropItem(), this);
		p.registerEvents(new PlayerInteract(this), this);
		p.registerEvents(new PlayerJoin(this), this);
		p.registerEvents(new PlayerKick(this), this);
		p.registerEvents(new PlayerMove(this), this);
		p.registerEvents(new PlayerQuit(this), this);
		p.registerEvents(new PlayerRespawn(this), this);
		p.registerEvents(new ProjectileHit(this), this);
		p.registerEvents(new SignChange(), this);
		p.registerEvents(new WeatherChange(), this);
		
		max = getServer().getMaxPlayers();
		
		online = new ArrayList<Player>();
		pyro = new ArrayList<Player>();
		monster = new ArrayList<Player>();
		blu = new ArrayList<Player>();
		red = new ArrayList<Player>();
		inventory = new ArrayList<Player>();
		admins = new ArrayList<Player>();
		
		if (!getConfig().getBoolean("has-started")) {
			getLogger().warning("This is the first logged startup event (did you clear your config?)");
			getLogger().warning("Please make sure you set the coordinates for the new spawn areas.");
		}
		getLogger().info("Please do not modify the config as it changes often during gameplay.");
		
		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for (World world : getServer().getWorlds()) {
					world.setTime(14000);
				}
			}
		}, 4800L, 4800L);
		
		for (World world : getServer().getWorlds()) {
			world.setTime(14000);
			world.setStorm(false);
		}
		
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
	
	public String getMessage() {
		return String.valueOf('|') + String.valueOf(ChatColor.STRIKETHROUGH) + String.valueOf('|') + String.valueOf(ChatColor.RESET) + " ";
	}
	
	public void sendMessage(Player player, String message) {
		player.sendMessage(ChatColor.GRAY + getMessage() + message);
	}
	
	public void sendMessage(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.GRAY + getMessage() + message);
	}
	
	public void messageAdmins(String message) {
		for (Player player : getServer().getOnlinePlayers()) {
			player.sendMessage(getMessage() + message);
		}
		getLogger().info(message);
	}
	
	public void adminMode(String message) {
		for (int i = 0; i < admins.size(); i++) {
			sendMessage(admins.get(i), message);
		}
	}
	
	public void smitePlayer(final Player player) {
		final Location origLoc = player.getLocation();
		final Random random = new Random();
        for (int i = 0; i < 15; i++) {
            this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                public void run() {
                    Location loc = origLoc.clone();
                    loc.setX(loc.getX() + random.nextDouble() * 20 - 10);
                    loc.setZ(loc.getZ() + random.nextDouble() * 20 - 10);
                    player.getWorld().strikeLightning(loc);
                    player.getWorld().createExplosion(loc, 0);
                }
            }, Math.max(0, i * 3 + random.nextInt(10) - 5));
        }
	}

}
