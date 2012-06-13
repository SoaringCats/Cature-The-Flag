package tk.nekotech.war.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.nekotech.war.War;
import tk.nekotech.war.helpers.ColorChoice;

public class AdminCommand extends MasterCommand {
	private War war;
	
	public AdminCommand(War war) {
		this.war = war;
	}

	@Override
	public boolean doCommand(CommandSender sender, String command, String[] args, boolean realPlayer, Player player) {
		if (!sender.hasPermission("jtwar.admin")) {
			war.sendMessage(sender, ChatColor.RED + "You do not have access to that command.");
			return true;
		}
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
		return true;
	}

}
