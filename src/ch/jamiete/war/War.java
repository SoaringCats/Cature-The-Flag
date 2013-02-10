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

    public void adminMode(final String message) {
        for (int i = 0; i < this.admins.size(); i++) {
            this.sendMessage(this.admins.get(i), message);
        }
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(final String worldname, final String uid) {
        return new Gen(this);
    }

    public String getMessage() {
        return String.valueOf('|') + String.valueOf(ChatColor.STRIKETHROUGH) + String.valueOf('|') + String.valueOf(ChatColor.RESET) + " ";
    }

    public void messageAdmins(final String message) {
        for (final Player player : this.getServer().getOnlinePlayers()) {
            player.sendMessage(this.getMessage() + message);
        }
        this.getLogger().info(message);
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Disabled!");
    }

    @Override
    public void onEnable() {
        this.getCommand("admin").setExecutor(new AdminCommand(this));
        this.getCommand("join").setExecutor(new JoinCommand(this));
        this.getCommand("blu").setExecutor(new SetupCommands(this));
        this.getCommand("red").setExecutor(new SetupCommands(this));
        this.getCommand("ready").setExecutor(new SetupCommands(this));
        this.getCommand("spectate").setExecutor(new SetupCommands(this));
        this.getCommand("reset").setExecutor(new SetupCommands(this));
        this.getCommand("war").setExecutor(new WarCommand(this));

        final PluginManager p = this.getServer().getPluginManager();
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

        this.pyro = new ArrayList<Player>();
        this.monster = new ArrayList<Player>();
        this.blu = new ArrayList<Player>();
        this.red = new ArrayList<Player>();
        this.inventory = new ArrayList<Player>();
        this.admins = new ArrayList<Player>();
        this.medic = new ArrayList<Player>();
        this.afk = new HashMap<Player, Long>();
        this.arrows = new ArrayList<Arrow>();

        if (!this.getConfig().getBoolean("has-started")) {
            this.getLogger().warning("This is the first logged startup event (did you clear your config?)");
            this.getLogger().warning("Please make sure you set the coordinates for the new spawn areas.");
        }
        this.getLogger().info("****************************************");
        this.getLogger().info("DO NOT MODIFY THE CONFIG DURING GAMEPLAY");
        this.getLogger().info("DOING SO WILL ROYALLY FUCK THINGS UP! D:");
        this.getLogger().info("****************************************");

        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new AFKCheck(this), 1200L, 1200L);
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new ArrowRemoval(this), 40L, 40L);
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Time(this), 4800L, 4800L);

        for (final World world : this.getServer().getWorlds()) {
            world.setTime(14000);
            world.setStorm(false);
        }

        for (final Player player : this.getServer().getOnlinePlayers()) {
            player.kickPlayer("Please rejoin in 5 seconds :)");
        }
    }

    public void sendMessage(final CommandSender sender, final String message) {
        sender.sendMessage(ChatColor.GRAY + this.getMessage() + message);
    }

    public void sendMessage(final Player player, final String message) {
        player.sendMessage(ChatColor.GRAY + this.getMessage() + message);
    }

    public void smitePlayer(final Player player) {
        final Location origLoc = player.getLocation();
        final Random random = new Random();
        for (int i = 0; i < 5; i++) {
            this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                @Override
                public void run() {
                    final Location loc = origLoc.clone();
                    loc.setX(loc.getX() + random.nextDouble() * 20 - 10);
                    loc.setZ(loc.getZ() + random.nextDouble() * 20 - 10);
                    player.getWorld().strikeLightning(loc);
                    player.getWorld().createExplosion(loc, 0);
                }
            }, Math.max(0, i * 3 + random.nextInt(10) - 5));
        }
    }

}
