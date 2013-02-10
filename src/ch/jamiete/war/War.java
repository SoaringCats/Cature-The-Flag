package ch.jamiete.war;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import ch.jamiete.war.commands.GlobalChatCommand;
import ch.jamiete.war.commands.JoinCommand;
import ch.jamiete.war.commands.SetupCommands;
import ch.jamiete.war.commands.WarCommand;
import ch.jamiete.war.helpers.Team;
import ch.jamiete.war.helpers.WarHelper;
import ch.jamiete.war.helpers.WarPlayer;
import ch.jamiete.war.listeners.BlockBreak;
import ch.jamiete.war.listeners.BlockBurn;
import ch.jamiete.war.listeners.BlockIgnite;
import ch.jamiete.war.listeners.BlockPlace;
import ch.jamiete.war.listeners.CreatureSpawn;
import ch.jamiete.war.listeners.EntityDamageByEntity;
import ch.jamiete.war.listeners.EntityDeath;
import ch.jamiete.war.listeners.EntityExplode;
import ch.jamiete.war.listeners.EntityInteract;
import ch.jamiete.war.listeners.PlayerChat;
import ch.jamiete.war.listeners.PlayerDeath;
import ch.jamiete.war.listeners.PlayerDropItem;
import ch.jamiete.war.listeners.PlayerJoin;
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

    public World mainWorld;
    private ArrayList<WarPlayer> players;
    private WarHelper helper;
    public ConcurrentHashMap<Player, Long> afk;

    @Override
    public ChunkGenerator getDefaultWorldGenerator(final String worldname, final String uid) {
        return new WorldGenerator(this);
    }

    public WarHelper getHelper() {
        return this.helper;
    }

    public WarPlayer[] getPlayers() {
        synchronized (this.players) {
            return this.players.toArray(new WarPlayer[this.players.size()]);
        }
    }

    @Override
    public void onEnable() {
        this.mainWorld = this.getServer().getWorlds().get(0);
        this.players = new ArrayList<WarPlayer>();
        this.helper = new WarHelper(this);
        this.afk = new ConcurrentHashMap<Player, Long>();

        if (this.getServer().getPluginManager().getPlugin("TagAPI") == null) {
            this.getLogger().severe("Failed to find TagAPI. Please download it from http://dev.bukkit.org/server-mods/tag");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        final SetupCommands setup = new SetupCommands(this);

        this.getCommand("join").setExecutor(new JoinCommand(this));
        this.getCommand("blu").setExecutor(setup);
        this.getCommand("red").setExecutor(setup);
        this.getCommand("ready").setExecutor(setup);
        this.getCommand("spectator").setExecutor(setup);
        this.getCommand("reset").setExecutor(setup);
        this.getCommand("war").setExecutor(new WarCommand(this));
        this.getCommand("global").setExecutor(new GlobalChatCommand(this));

        new BlockBreak(this);
        new BlockBurn(this);
        new BlockIgnite(this);
        new BlockPlace(this);
        new CreatureSpawn(this);
        new EntityDamageByEntity(this);
        new EntityDeath(this);
        new EntityExplode(this);
        new EntityInteract(this);
        new PlayerChat(this);
        new PlayerDeath(this);
        new PlayerDropItem(this);
        new PlayerJoin(this);
        new PlayerMove(this);
        new PlayerQuit(this);
        new PlayerRespawn(this);
        new PotionSplash(this);
        new ProjectileHit(this);
        new SignChange(this);
        new WeatherChange(this);

        if (!this.getConfig().getBoolean("has-started")) {
            this.getLogger().warning("This is the first logged startup event (did you clear your config?)");
            this.getLogger().warning("Please make sure you set the coordinates for the new spawn areas.");
        }
        this.getLogger().info("****************************************");
        this.getLogger().info("DO NOT MODIFY THE CONFIG DURING GAMEPLAY");
        this.getLogger().info("****************************************");

        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new AFKCheck(this), 1200L, 1200L);
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new ArrowRemoval(this), 40L, 40L);
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Time(this), 4800L, 4800L);

        for (final World world : this.getServer().getWorlds()) {
            world.setTime(14000);
            world.setStorm(false);
        }

        for (final Player player : this.getServer().getOnlinePlayers()) {
            final WarPlayer wplayer = new WarPlayer(player);
            this.players.add(wplayer);
            this.helper.assignPlayer(wplayer, Team.SPECTATOR);
        }
    }

}
