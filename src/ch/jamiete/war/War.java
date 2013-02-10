package ch.jamiete.war;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ch.jamiete.war.commands.AdminCommand;
import ch.jamiete.war.commands.JoinCommand;
import ch.jamiete.war.commands.SetupCommands;
import ch.jamiete.war.commands.WarCommand;
import ch.jamiete.war.helpers.Armor;
import ch.jamiete.war.helpers.Assignment;
import ch.jamiete.war.helpers.Color;
import ch.jamiete.war.helpers.Mob;
import ch.jamiete.war.helpers.Potions;
import ch.jamiete.war.helpers.QuickPlayer;
import ch.jamiete.war.helpers.TeamHelpers;
import ch.jamiete.war.listeners.BlockBreak;
import ch.jamiete.war.listeners.BlockBurn;
import ch.jamiete.war.listeners.BlockIgnite;
import ch.jamiete.war.listeners.BlockPlace;
import ch.jamiete.war.listeners.CreatureSpawn;
import ch.jamiete.war.listeners.EntityDamage;
import ch.jamiete.war.listeners.EntityDamageByEntity;
import ch.jamiete.war.listeners.EntityDeath;
import ch.jamiete.war.listeners.EntityExplode;
import ch.jamiete.war.listeners.EntityInteract;
import ch.jamiete.war.listeners.InventoryClick;
import ch.jamiete.war.listeners.InventoryClose;
import ch.jamiete.war.listeners.PlayerChat;
import ch.jamiete.war.listeners.PlayerDeath;
import ch.jamiete.war.listeners.PlayerDropItem;
import ch.jamiete.war.listeners.PlayerInteract;
import ch.jamiete.war.listeners.PlayerJoin;
import ch.jamiete.war.listeners.PlayerKick;
import ch.jamiete.war.listeners.PlayerMove;
import ch.jamiete.war.listeners.PlayerQuit;
import ch.jamiete.war.listeners.PlayerRespawn;
import ch.jamiete.war.listeners.PotionSplash;
import ch.jamiete.war.listeners.ProjectileHit;
import ch.jamiete.war.listeners.SignChange;
import ch.jamiete.war.listeners.WeatherChange;
import ch.jamiete.war.runnables.AFKCheck;
import ch.jamiete.war.runnables.ArrowRemoval;
import ch.jamiete.war.runnables.Time;


public class War extends JavaPlugin {
	
	public Armor armor = new Armor(this);
	public Assignment assignment = new Assignment(this);
	public Color color = new Color(this);
	public Mob mob = new Mob(this);
	public Potions potions = new Potions(this);
	public QuickPlayer quickplayer = new QuickPlayer(this);
	public TeamHelpers teamhelpers = new TeamHelpers(this);
	
	public ArrayList<Player> pyro;
	public ArrayList<Player> monster;
	public ArrayList<Player> blu;
	public ArrayList<Player> red;
	public ArrayList<Player> inventory;
	public ArrayList<Player> admins;
	public ArrayList<Player> medic;
	public HashMap<Player, Long> afk;
	public ArrayList<Arrow> arrows;
	
	public void onEnable() {
		getCommand("admin").setExecutor(new AdminCommand(this));
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
		p.registerEvents(new EntityInteract(), this);
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
		p.registerEvents(new PotionSplash(this), this);
		p.registerEvents(new ProjectileHit(this), this);
		p.registerEvents(new SignChange(), this);
		p.registerEvents(new WeatherChange(this), this);
		
		pyro = new ArrayList<Player>();
		monster = new ArrayList<Player>();
		blu = new ArrayList<Player>();
		red = new ArrayList<Player>();
		inventory = new ArrayList<Player>();
		admins = new ArrayList<Player>();
		medic = new ArrayList<Player>();
		afk = new HashMap<Player, Long>();
		arrows = new ArrayList<Arrow>();
		
		if (!getConfig().getBoolean("has-started")) {
			getLogger().warning("This is the first logged startup event (did you clear your config?)");
			getLogger().warning("Please make sure you set the coordinates for the new spawn areas.");
		}
		getLogger().info("****************************************");
		getLogger().info("DO NOT MODIFY THE CONFIG DURING GAMEPLAY");
		getLogger().info("DOING SO WILL ROYALLY FUCK THINGS UP! D:");
		getLogger().info("****************************************");
		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new AFKCheck(this), 1200L, 1200L);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new ArrowRemoval(this), 40L, 40L);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Time(this), 4800L, 4800L);
		
		for (World world : getServer().getWorlds()) {
			world.setTime(14000);
			world.setStorm(false);
		}
		
		for (Player player : getServer().getOnlinePlayers()) {
			player.kickPlayer("Please rejoin in 5 seconds :)");
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
        for (int i = 0; i < 5; i++) {
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
