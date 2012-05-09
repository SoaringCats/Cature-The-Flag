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
		if (command.equals("blu")) {
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
					player.sendMessage(ChatColor.GREEN + "Blu team spawn set!");
					player.sendMessage(ChatColor.GREEN + "When ready type /ready");
				} else {
					sender.sendMessage(a + "In-game command only.");
				}
			}
		}
		
		if (command.equals("red")) {
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
					player.sendMessage(ChatColor.GREEN + "Red team spawn set!");
					player.sendMessage(ChatColor.GREEN + "When ready type /ready");
				} else {
					sender.sendMessage(a + "In-game command only.");
				}
			}
		}
		
		if (command.equals("spectate")) {
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
					player.sendMessage(ChatColor.GREEN + "Spectator spawn set!");
					player.sendMessage(ChatColor.GREEN + "When ready type /ready");
				}
			} else {
				sender.sendMessage(a + "In-game command only.");
			}
		}
		
		if (command.equals("ready")) {
			if (sender.hasPermission("jtwar.admin")) {
				if (realPlayer) {
					war.getConfig().set("has-started", true);
					war.saveConfig();
					if (war.getConfig().getBoolean("ready-to-go")) {
						war.getConfig().set("ready-to-go", false);
						war.saveConfig();
						player.sendMessage(ChatColor.GREEN + "Server set to unready mode, block changes now allowed!");
						player.sendMessage(ChatColor.GREEN + "Mob spawns are now disabled. When ready type /ready again!");
						int count = 0;
						for (Entity e : player.getWorld().getEntities()) {
							if (!(e instanceof Player)) {
								e.remove();
								count++;
							}
						}
						player.sendMessage("Removed " + count + " entities!");
					} else {
						war.getConfig().set("ready-to-go", true);
						war.saveConfig();
						player.sendMessage(ChatColor.GREEN + "Server set to ready mode, block changes now disabled!");
						player.sendMessage(ChatColor.GREEN + "Enabling mob spawns... To disable ready mode type /ready again!");
					}
				} else {
					sender.sendMessage(a + "In-game command only.");
				}
			}
		}
		
		if (command.equals("reset")) {
			if (sender.hasPermission("jtwar.admin")) {
				war.getConfig().set("has-started", false);
				war.getConfig().set("ready-to-go", false);
				war.saveConfig();
				sender.sendMessage(ChatColor.GREEN + "Reset configuration but kept spawns!");
			}
		}
		
		return true;
	}

}
