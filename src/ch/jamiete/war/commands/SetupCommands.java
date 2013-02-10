package ch.jamiete.war.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import ch.jamiete.war.War;

public class SetupCommands extends MasterCommand {
    private final War war;

    ChatColor a = ChatColor.AQUA;

    public SetupCommands(final War war) {
        this.war = war;
    }

    @Override
    public boolean doCommand(final CommandSender sender, final String command, final String[] args, final boolean realPlayer, final Player player) {
        if (command.equalsIgnoreCase("blu")) {
            if (sender.hasPermission("jtwar.admin")) {
                if (realPlayer) {
                    final int x = (int) player.getLocation().getX();
                    final int y = (int) player.getLocation().getY();
                    final int z = (int) player.getLocation().getZ();
                    final int yaw = (int) player.getLocation().getYaw();
                    final int pitch = (int) player.getLocation().getPitch();
                    this.war.getConfig().set("blu-spawn-x", x);
                    this.war.getConfig().set("blu-spawn-y", y);
                    this.war.getConfig().set("blu-spawn-z", z);
                    this.war.getConfig().set("blu-spawn-yaw", yaw);
                    this.war.getConfig().set("blu-spawn-pitch", pitch);
                    this.war.saveConfig();
                    this.war.sendMessage(player, ChatColor.GREEN + "Blu team spawn set!");
                    this.war.sendMessage(player, ChatColor.GREEN + "When ready type /ready");
                } else {
                    sender.sendMessage(this.a + "In-game command only.");
                }
            }
        }

        if (command.equalsIgnoreCase("red")) {
            if (sender.hasPermission("jtwar.admin")) {
                if (realPlayer) {
                    final int x = (int) player.getLocation().getX();
                    final int y = (int) player.getLocation().getY();
                    final int z = (int) player.getLocation().getZ();
                    final int yaw = (int) player.getLocation().getYaw();
                    final int pitch = (int) player.getLocation().getPitch();
                    this.war.getConfig().set("red-spawn-x", x);
                    this.war.getConfig().set("red-spawn-y", y);
                    this.war.getConfig().set("red-spawn-z", z);
                    this.war.getConfig().set("red-spawn-yaw", yaw);
                    this.war.getConfig().set("red-spawn-pitch", pitch);
                    this.war.saveConfig();
                    this.war.sendMessage(player, ChatColor.GREEN + "Red team spawn set!");
                    this.war.sendMessage(player, ChatColor.GREEN + "When ready type /ready");
                } else {
                    sender.sendMessage(this.a + "In-game command only.");
                }
            }
        }

        if (command.equalsIgnoreCase("spectate")) {
            if (realPlayer) {
                if (sender.hasPermission("jtwar.admin")) {
                    final int x = (int) player.getLocation().getX();
                    final int y = (int) player.getLocation().getY();
                    final int z = (int) player.getLocation().getZ();
                    final int yaw = (int) player.getLocation().getYaw();
                    final int pitch = (int) player.getLocation().getPitch();
                    this.war.getConfig().set("spec-spawn-x", x);
                    this.war.getConfig().set("spec-spawn-y", y);
                    this.war.getConfig().set("spec-spawn-z", z);
                    this.war.getConfig().set("spec-spawn-yaw", yaw);
                    this.war.getConfig().set("spec-spawn-pitch", pitch);
                    this.war.saveConfig();
                    this.war.sendMessage(player, ChatColor.GREEN + "Spectator spawn set!");
                    this.war.sendMessage(player, ChatColor.GREEN + "When ready type /ready");
                }
            } else {
                sender.sendMessage(this.a + "In-game command only.");
            }
        }

        if (command.equalsIgnoreCase("ready")) {
            if (sender.hasPermission("jtwar.admin")) {
                if (realPlayer) {
                    this.war.getConfig().set("has-started", true);
                    this.war.saveConfig();
                    if (this.war.getConfig().getBoolean("ready-to-go")) {
                        this.war.getConfig().set("ready-to-go", false);
                        this.war.saveConfig();
                        this.war.sendMessage(player, ChatColor.GREEN + "Server set to unready mode, block changes now allowed!");
                        this.war.sendMessage(player, ChatColor.GREEN + "Mob spawns are now disabled. When ready type /ready again!");
                        int count = 0;
                        for (final Entity e : player.getWorld().getEntities()) {
                            if (!(e instanceof Player)) {
                                e.remove();
                                count++;
                            }
                        }
                        this.war.sendMessage(player, ChatColor.GREEN + "Removed " + count + " entities!");
                    } else {
                        this.war.getConfig().set("ready-to-go", true);
                        this.war.saveConfig();
                        this.war.sendMessage(player, ChatColor.GREEN + "Server set to ready mode, block changes now disabled!");
                        this.war.sendMessage(player, ChatColor.GREEN + "Enabling mob spawns... To disable ready mode type /ready again!");
                    }
                } else {
                    sender.sendMessage(this.a + "In-game command only.");
                }
            }
        }

        if (command.equalsIgnoreCase("reset")) {
            if (sender.hasPermission("jtwar.admin")) {
                this.war.getConfig().set("has-started", false);
                this.war.getConfig().set("ready-to-go", false);
                this.war.saveConfig();
                this.war.sendMessage(sender, ChatColor.GREEN + "Reset configuration but kept spawns!");
            }
        }

        return true;
    }

}
