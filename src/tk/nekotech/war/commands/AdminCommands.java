package tk.nekotech.war.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.nekotech.war.War;
import tk.nekotech.war.helpers.ColorChoice;

public class AdminCommands extends MasterCommand {
	private War war;
	
	public AdminCommands(War war) {
		this.war = war;
	}

	@Override
	public boolean doCommand(CommandSender sender, String command, String[] args, boolean realPlayer, Player player) {
		if (!sender.hasPermission("jtwar.admin")) {
			war.sendMessage(sender, ChatColor.RED + "You do not have access to that command.");
			return true;
		}
		if (command.equalsIgnoreCase("admin")) {
			if (!realPlayer) {
				sender.sendMessage(ChatColor.RED + "[ADMIN] In-game command only.");
				return true;
			} else {
				if (war.admins.contains(player)) {
					war.admins.remove(player);
					player.setGameMode(GameMode.SURVIVAL);
					war.messageAdmins(ChatColor.RED + player.getName() + " disables ADMIN MODE.");
					int teamID = war.teamhelpers.teamName(player);
					switch (teamID) {
						case 0:
							war.color.setColor(ColorChoice.BLU, player);
							break;
						case 1:
							war.color.setColor(ColorChoice.RED, player);
							break;
						default:
							war.color.setColor(ColorChoice.GRAY, player);
							break;
					}
				} else {
					if ((args.length == 1) && ((args[0].equalsIgnoreCase("a") || (args[0].equalsIgnoreCase("announce"))))) {
						war.getServer().broadcastMessage(ChatColor.RED + war.getMessage() + ChatColor.LIGHT_PURPLE + player.getName() + " is an administrator, listen to them!");
					}
					war.admins.add(player);
					player.setGameMode(GameMode.CREATIVE);
					war.messageAdmins(ChatColor.RED + player.getName() + " enables ADMIN MODE.");
					war.color.setColor(ColorChoice.PURPLE, player);
					player.setDisplayName(ChatColor.LIGHT_PURPLE + player.getName());
				}
			}
		}
		if ((command.equalsIgnoreCase("kick")) || (command.equalsIgnoreCase("k"))) {
			if (args.length < 2) {
				war.sendMessage(sender, ChatColor.RED + "/kick <player> <reason>");
			} else {
				Player target = war.getServer().getPlayer(args[0]);
				if (target == null) {
					war.sendMessage(sender, ChatColor.RED + "Could not find player " + args[0]);
				} else {
					String split = combineSplit(1, args, " ");
					target.kickPlayer("Kicked: " + split);
					war.messageAdmins(ChatColor.RED + sender.getName() + " kicked " + target.getName() + " for: " + split);
				}
			}
		}
		if ((command.equalsIgnoreCase("ban")) || (command.equalsIgnoreCase("b"))) {
			if (args.length < 2) {
				war.sendMessage(sender, ChatColor.RED + "/ban <player> <reason>");
			} else {
				Player target = war.getServer().getPlayer(args[0]);
				if (target == null) {
					war.sendMessage(sender, ChatColor.RED + "Could not find player " + args[0]);
				} else {
					String split = combineSplit(1, args, " ");
					target.setBanned(true);
					target.kickPlayer("Banned: " + split);
					war.messageAdmins(ChatColor.RED + sender.getName() + " banned " + target.getName() + " for: " + split);
				}
			}
		}
		if ((command.equalsIgnoreCase("unban")) || (command.equalsIgnoreCase("pardon"))) {
			if (args.length != 1) {
				war.sendMessage(sender, ChatColor.RED + "/unban <player>");
			} else {
				OfflinePlayer banned = war.getServer().getOfflinePlayer(args[0]);
				if (!banned.hasPlayedBefore()) {
					war.sendMessage(sender, ChatColor.RED + args[0] + " has not played on this server before!");
					return true;
				}
				if (banned.isBanned()) {
					banned.setBanned(false);
					war.messageAdmins(ChatColor.RED + sender.getName() + " unbanned " + banned.getName());
				} else {
					war.sendMessage(sender, ChatColor.RED + args[0] + " is not currently banned!");
				}
			}
		}
		if ((command.equalsIgnoreCase("smite")) || (command.equalsIgnoreCase("s"))) {
			if (args.length != 1) {
				war.sendMessage(sender, ChatColor.RED + "/smite <player>");
			} else {
				Player target = war.getServer().getPlayer(args[0]);
				if (target == null ) {
					war.sendMessage(sender, ChatColor.RED + "Could not find player " + args[0]);
				} else {
					war.smitePlayer(target);
					war.messageAdmins(ChatColor.RED + sender.getName() + " smites " + target.getName());
					war.smitePlayer(target);
				}
			}
		}
		return true;
	}
	
	private String combineSplit(int startIndex, String[] string, String seperator) {
        final StringBuilder builder = new StringBuilder();
        for (int i = startIndex; i < string.length; i++) {
            builder.append(string[i]);
            builder.append(seperator);
        }
        builder.deleteCharAt(builder.length() - seperator.length());
        return builder.toString();
    }

}
