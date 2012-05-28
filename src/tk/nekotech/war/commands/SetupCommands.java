package tk.nekotech.war.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import tk.nekotech.war.War;

public class SetupCommands extends MasterCommand {
	private War war;
	
	public SetupCommands(War war) {
		this.war = war;
	}
	
	ChatColor a = ChatColor.AQUA;

	@Override
	public boolean doCommand(CommandSender sender, String command, String[] args, boolean realPlayer, Player player) {
		if (command.equalsIgnoreCase("blu")) {
			if (sender.hasPermission("jtwar.admin")) {
				if (realPlayer) {
					int x = (int) player.getLocation().getX();
					int y = (int) player.getLocation().getY();
					int z = (int) player.getLocation().getZ();
					int yaw = (int) player.getLocation().getYaw();
					int pitch = (int) player.getLocation().getPitch();
					war.getConfig().set("blu-spawn-x", x);
					war.getConfig().set("blu-spawn-y", y);
					war.getConfig().set("blu-spawn-z", z);
					war.getConfig().set("blu-spawn-yaw", yaw);
					war.getConfig().set("blu-spawn-pitch", pitch);
					war.saveConfig();
					war.sendMessage(player, ChatColor.GREEN + "Blu team spawn set!");
					war.sendMessage(player, ChatColor.GREEN + "When ready type /ready");
				} else {
					sender.sendMessage(a + "In-game command only.");
				}
			}
		}
		
		if (command.equalsIgnoreCase("red")) {
			if (sender.hasPermission("jtwar.admin")) {
				if (realPlayer) {
					int x = (int) player.getLocation().getX();
					int y = (int) player.getLocation().getY();
					int z = (int) player.getLocation().getZ();
					int yaw = (int) player.getLocation().getYaw();
					int pitch = (int) player.getLocation().getPitch();
					war.getConfig().set("red-spawn-x", x);
					war.getConfig().set("red-spawn-y", y);
					war.getConfig().set("red-spawn-z", z);
					war.getConfig().set("red-spawn-yaw", yaw);
					war.getConfig().set("red-spawn-pitch", pitch);
					war.saveConfig();
					war.sendMessage(player, ChatColor.GREEN + "Red team spawn set!");
					war.sendMessage(player, ChatColor.GREEN + "When ready type /ready");
				} else {
					sender.sendMessage(a + "In-game command only.");
				}
			}
		}
		
		if (command.equalsIgnoreCase("spectate")) {
			if (realPlayer) {
				if (sender.hasPermission("jtwar.admin")) {
					int x = (int) player.getLocation().getX();
					int y = (int) player.getLocation().getY();
					int z = (int) player.getLocation().getZ();
					int yaw = (int) player.getLocation().getYaw();
					int pitch = (int) player.getLocation().getPitch();
					war.getConfig().set("spec-spawn-x", x);
					war.getConfig().set("spec-spawn-y", y);
					war.getConfig().set("spec-spawn-z", z);
					war.getConfig().set("spec-spawn-yaw", yaw);
					war.getConfig().set("spec-spawn-pitch", pitch);
					war.saveConfig();
					war.sendMessage(player, ChatColor.GREEN + "Spectator spawn set!");
					war.sendMessage(player, ChatColor.GREEN + "When ready type /ready");
				}
			} else {
				sender.sendMessage(a + "In-game command only.");
			}
		}
		
		if (command.equalsIgnoreCase("ready")) {
			if (sender.hasPermission("jtwar.admin")) {
				if (realPlayer) {
					war.getConfig().set("has-started", true);
					war.saveConfig();
					if (war.getConfig().getBoolean("ready-to-go")) {
						war.getConfig().set("ready-to-go", false);
						war.saveConfig();
						war.sendMessage(player, ChatColor.GREEN + "Server set to unready mode, block changes now allowed!");
						war.sendMessage(player, ChatColor.GREEN + "Mob spawns are now disabled. When ready type /ready again!");
						int count = 0;
						for (Entity e : player.getWorld().getEntities()) {
							if (!(e instanceof Player)) {
								e.remove();
								count++;
							}
						}
						war.sendMessage(player, ChatColor.GREEN + "Removed " + count + " entities!");
					} else {
						war.getConfig().set("ready-to-go", true);
						war.saveConfig();
						war.sendMessage(player, ChatColor.GREEN + "Server set to ready mode, block changes now disabled!");
						war.sendMessage(player, ChatColor.GREEN + "Enabling mob spawns... To disable ready mode type /ready again!");
					}
				} else {
					sender.sendMessage(a + "In-game command only.");
				}
			}
		}
		
		if (command.equalsIgnoreCase("reset")) {
			if (sender.hasPermission("jtwar.admin")) {
				war.getConfig().set("has-started", false);
				war.getConfig().set("ready-to-go", false);
				war.saveConfig();
				war.sendMessage(sender, ChatColor.GREEN + "Reset configuration but kept spawns!");
			}
		}
		
		return true;
	}

}
