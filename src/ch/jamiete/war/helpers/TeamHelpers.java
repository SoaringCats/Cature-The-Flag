package ch.jamiete.war.helpers;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ch.jamiete.war.War;

public class TeamHelpers {
    private final War war;

    public TeamHelpers(final War war) {
        this.war = war;
    }

    public void alertTeam(final Player player, final int team) {
        if (team == 0) {
            this.war.getServer().broadcastMessage(this.war.getMessage() + ChatColor.BLUE + player.getName() + " is now on blu team!");
        }
        if (team == 1) {
            this.war.getServer().broadcastMessage(this.war.getMessage() + ChatColor.RED + player.getName() + " is now on red team!");
        }
        this.war.getServer().broadcastMessage(this.war.getMessage() + ChatColor.GREEN + "There are now " + ChatColor.BLUE + this.blu() + " on blu" + ChatColor.GREEN + " and " + ChatColor.RED + this.red() + " on red");
    }

    public int blu() {
        return this.war.blu.size();
    }

    public void clearTeams(final Player player) {
        this.war.quickplayer.clearAttachments(player);
        this.war.color.setColor(ColorChoice.GRAY, player);
        player.getInventory().clear();
        this.war.potions.clearPotions(player);
    }

    public ChatColor getColor(final Player player) {
        if (!this.onTeam(player)) {
            return ChatColor.GRAY;
        }
        if (this.teamName(player) == 0) {
            return ChatColor.BLUE;
        }
        if (this.teamName(player) == 1) {
            return ChatColor.RED;
        }
        return ChatColor.BLACK;
    }

    public ItemStack getWool(final Player player) {
        final int team = this.teamName(player);
        if (team == 0) {
            return new ItemStack(Material.WOOL, 1, (byte) 11);
        }
        if (team == 1) {
            return new ItemStack(Material.WOOL, 1, (byte) 15);
        }
        return new ItemStack(Material.WOOL, 1, (byte) 7);
    }

    public boolean onTeam(final Player player) {
        return this.war.blu.contains(player) || this.war.red.contains(player);
    }

    public int red() {
        return this.war.red.size();
    }

    public boolean sameTeam(final Player one, final Player two) {
        return this.war.blu.contains(one) && this.war.blu.contains(two) || this.war.red.contains(one) && this.war.red.contains(two);
    }

    public void teamMessage(final int team, final String message) {
        if (team == 2) {
            this.war.adminMode(ChatColor.LIGHT_PURPLE + "Discarded team message `" + message + "` sent by an admin");
        }
        if (team == 0) {
            for (final Player p : this.war.getServer().getOnlinePlayers()) {
                if (this.war.blu.contains(p)) {
                    this.war.sendMessage(p, ChatColor.BLUE + message);
                }
            }
            this.war.getLogger().info("[BLU] " + message);
            return;
        }
        if (team == 1) {
            for (final Player p : this.war.getServer().getOnlinePlayers()) {
                if (this.war.red.contains(p)) {
                    this.war.sendMessage(p, ChatColor.RED + message);
                }
            }
            this.war.getLogger().info("[RED] " + message);
            return;
        }
        if (team == 9) {
            for (final Player p : this.war.getServer().getOnlinePlayers()) {
                if (!this.war.red.contains(p) && !this.war.blu.contains(p)) {
                    this.war.sendMessage(p, ChatColor.GRAY + message);
                }
            }
            this.war.getLogger().info("[SPECTATE] " + message);
            return;
        }
        this.war.getLogger().severe("Unable to find team for team message!");
    }

    public int teamName(final Player player) {
        if (this.war.admins.contains(player)) {
            return 2;
        }
        if (this.war.blu.contains(player)) {
            return 0;
        }
        if (this.war.red.contains(player)) {
            return 1;
        }
        return 9;
    }

    public void toSpawn(final Player player, final int teamID) {
        if (teamID == 0) {
            final double x = this.war.getConfig().getDouble("blu-spawn-x");
            final double y = this.war.getConfig().getDouble("blu-spawn-y");
            final double z = this.war.getConfig().getDouble("blu-spawn-z");
            final float yaw = this.war.getConfig().getInt("blu-spawn-yaw");
            final float pitch = this.war.getConfig().getInt("blu-spawn-pitch");
            player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));
        } else if (teamID == 1) {
            final double x = this.war.getConfig().getDouble("red-spawn-x");
            final double y = this.war.getConfig().getDouble("red-spawn-y");
            final double z = this.war.getConfig().getDouble("red-spawn-z");
            final float yaw = this.war.getConfig().getInt("red-spawn-yaw");
            final float pitch = this.war.getConfig().getInt("red-spawn-pitch");
            player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));
        } else {
            final double sx = this.war.getConfig().getDouble("spec-spawn-x");
            final double sy = this.war.getConfig().getDouble("spec-spawn-y");
            final double sz = this.war.getConfig().getDouble("spec-spawn-z");
            final float syaw = this.war.getConfig().getInt("spec-spawn-yaw");
            final float spitch = this.war.getConfig().getInt("spec-spawn-pitch");
            player.teleport(new Location(player.getWorld(), sx, sy, sz, syaw, spitch));
        }
    }

}
