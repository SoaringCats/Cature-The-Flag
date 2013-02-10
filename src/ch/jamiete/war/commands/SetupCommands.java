package ch.jamiete.war.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import ch.jamiete.war.MasterCommand;
import ch.jamiete.war.War;
import ch.jamiete.war.helpers.WarPlayer;
import ch.jamiete.war.helpers.WarValues;

public class SetupCommands extends MasterCommand {
    public SetupCommands(final War war) {
        super(war);
    }

    @Override
    public void execute(final CommandSender sender, final String command, final String[] args, final boolean realPlayer, final Player player) {
        if (!realPlayer) {
            sender.sendMessage(WarValues.MESSAGE_CONSOLE);
            return;
        }

        final Location loc = player.getLocation();
        final WarPlayer wplayer = this.war.getHelper().getPlayerExact(player.getName());

        if (command.equalsIgnoreCase("ready")) {
            this.war.getConfig().set("has-started", true);
            this.war.saveConfig();

            if (this.war.getConfig().getBoolean("ready-to-go")) {
                this.war.getConfig().set("ready-to-go", false);
                this.war.saveConfig();

                wplayer.sendMessage(ChatColor.GREEN + "Server set to unready mode, block changes now allowed!");
                wplayer.sendMessage(ChatColor.GREEN + "Mob spawns are now disabled. When ready type /ready again!");

                for (final Entity e : player.getWorld().getEntities()) {
                    if (!(e instanceof Player)) {
                        e.remove();
                    }
                }
            } else {
                this.war.getConfig().set("ready-to-go", true);
                this.war.saveConfig();

                wplayer.sendMessage(ChatColor.GREEN + "Server set to ready mode, block changes now disabled!");
                wplayer.sendMessage(ChatColor.GREEN + "Mob spawns enabled. To disable ready mode type /ready again!");
            }
            return;
        }

        if (command.equalsIgnoreCase("reset")) {
            this.war.getConfig().set("has-started", false);
            this.war.getConfig().set("ready-to-go", false);
            this.war.saveConfig();
            wplayer.sendMessage(ChatColor.GREEN + "Reset configuration but kept spawns!");
            return;
        }

        final String team = command.toLowerCase();

        this.war.getConfig().set(team + ".spawn.x", loc.getBlockX());
        this.war.getConfig().set(team + ".spawn.y", loc.getBlockY());
        this.war.getConfig().set(team + ".spawn.z", loc.getBlockZ());
        this.war.getConfig().set(team + ".spawn.yaw", loc.getYaw());
        this.war.getConfig().set(team + ".spawn.pitch", loc.getPitch());
        wplayer.sendMessage(ChatColor.GREEN + team + " spawn set.");
    }
}
