package ch.jamiete.war.helpers;

import java.util.ArrayList;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.kitteh.tag.TagAPI;
import ch.jamiete.war.War;

public class WarHelper {
    private final War war;

    public WarHelper(final War war) {
        this.war = war;
    }

    public String arrayToString(final Object[] array) {
        final StringBuilder sb = new StringBuilder();
        for (final Object o : array) {
            sb.append(o.toString());
            sb.append(", ");
        }
        if (sb.length() != 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    public void assignPlayer(final WarPlayer player, final Team team) {
        final Player bplayer = player.getPlayer();

        if (bplayer.getGameMode() != GameMode.SURVIVAL) {
            bplayer.setGameMode(GameMode.SURVIVAL);
        }

        this.war.getHelper().broadcastMessage(ChatColor.GRAY + player.getName() + " is now on " + team.getColored());
        bplayer.setDisplayName(team.getColor() + player.getName());
        bplayer.setPlayerListName(this.war.getHelper().getShortenedUsername(team.getColor() + player.getName()));
        bplayer.teleport(this.getTeamSpawn(team));
        TagAPI.refreshPlayer(bplayer);

        if (team == Team.SPECTATOR) {
            return;
        }

        // TODO: Assign class
        //this.assignClass(bplayer);
    }

    public void broadcastMessage(final String message) {
        this.broadcastMessage(message, null);
    }

    public void broadcastMessage(final String message, final String permission) {
        for (final WarPlayer player : this.war.getPlayers()) {
            if (permission == null || player.hasPermission(permission)) {
                player.sendMessage(WarValues.MESSAGE_PREFIX + message);
            }
        }
    }

    public void broadcastTeam(final String message, final Team team) {
        for (final WarPlayer player : this.war.getPlayers()) {
            if (player.getTeam() == team) {
                player.sendMessage(WarValues.MESSAGE_PREFIX + message);
            }
        }
    }

    public void cleanWorlds() {
        this.war.getLogger().info("Performing garbage collection on all worlds.");
        for (final World world : this.war.getServer().getWorlds()) {
            this.war.getLogger().info("Clearing " + world.getEntities().size() + " entities from " + world.getName() + " for garbage collection!");
            for (final Entity entity : world.getEntities()) {
                entity.remove();
            }
        }
        this.war.getLogger().info("Garbage collection complete.");
    }

    public Team getAvailableTeam() {
        if (this.war.getHelper().getCountBlu() == this.war.getHelper().getCountRed()) {
            final Random random = new Random();
            return random.nextBoolean() ? Team.BLU : Team.RED;
        } else {
            return this.war.getHelper().getCountBlu() > this.war.getHelper().getCountRed() ? Team.RED : Team.BLU;
        }
    }

    public int getCountBlu() {
        int players = 0;
        for (final WarPlayer player : this.war.getPlayers()) {
            if (player.getTeam() == Team.BLU) {
                players++;
            }
        }
        return players;
    }

    public int getCountRed() {
        int players = 0;
        for (final WarPlayer player : this.war.getPlayers()) {
            if (player.getTeam() == Team.RED) {
                players++;
            }
        }
        return players;
    }

    public int getCountSpectator() {
        int players = 0;
        for (final WarPlayer player : this.war.getPlayers()) {
            if (player.getTeam() == Team.SPECTATOR) {
                players++;
            }
        }
        return players;
    }

    public WarPlayer getPlayer(final String match) {
        final ArrayList<WarPlayer> players = new ArrayList<WarPlayer>();
        for (final WarPlayer player : this.war.getPlayers()) {
            if (player.getName().contains(match)) {
                players.add(player);
            }
        }
        return players.size() != 1 ? null : players.get(0);
    }

    public WarPlayer getPlayerExact(final String match) {
        for (final WarPlayer player : this.war.getPlayers()) {
            if (player.getName().equals(match)) {
                return player;
            }
        }
        return null;
    }

    public EntityType getRandomMob() {
        final Random rand = new Random();
        switch (rand.nextInt(9)) {
            case 0:
                return EntityType.SPIDER;
            case 1:
                return EntityType.CAVE_SPIDER;
            case 2:
                return EntityType.SKELETON;
            case 3:
                return EntityType.CREEPER;
            case 4:
                return EntityType.PIG_ZOMBIE;
            case 5:
                return EntityType.ZOMBIE;
            case 6:
                return EntityType.ZOMBIE;
            case 7:
                return EntityType.ZOMBIE;
            case 8:
                return EntityType.ZOMBIE;
            default:
                return null;
        }
    }

    public String getShortenedUsername(final String username) {
        if (username.length() <= 16) {
            return username;
        }
        return username.substring(0, 16);
    }

    public Location getTeamSpawn(final Team team) {
        final String confSec = team.name().toLowerCase() + ".spawn.";
        return new Location(this.war.mainWorld, this.war.getConfig().getDouble(confSec + "x"), this.war.getConfig().getDouble(confSec + "y"), this.war.getConfig().getDouble(confSec + "x"), this.war.getConfig().getInt(confSec + "yaw"), this.war.getConfig().getInt(confSec + "pitch"));
    }

    public boolean isBadEffect(final PotionEffect effect) {
        final PotionEffectType type = effect.getType();
        return type == PotionEffectType.BLINDNESS || type == PotionEffectType.CONFUSION || type == PotionEffectType.DAMAGE_RESISTANCE || type == PotionEffectType.HARM || type == PotionEffectType.HUNGER || type == PotionEffectType.POISON || type == PotionEffectType.SLOW || type == PotionEffectType.SLOW_DIGGING || type == PotionEffectType.WEAKNESS;
    }

    public boolean isMobAllowed(final EntityType type) {
        return type == EntityType.SPIDER || type == EntityType.CAVE_SPIDER || type == EntityType.SKELETON || type == EntityType.CREEPER || type == EntityType.PIG_ZOMBIE || type == EntityType.ZOMBIE;
    }

    public void sendSmokeScreen(final Location location) {
        final Random random = new Random();
        for (int i = 0; i < 10; i++) {
            location.getWorld().playEffect(location, Effect.SMOKE, random.nextInt(9));
        }
    }

}
