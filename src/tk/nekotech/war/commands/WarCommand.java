package tk.nekotech.war.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarCommand extends MasterCommand {
	
	ChatColor a = ChatColor.AQUA;

	@Override
	public boolean doCommand(CommandSender sender, String command, String[] args, boolean realPlayer, Player player) {
		sender.sendMessage(a + "Welcome to the war, " + sender.getName() + "!");
		sender.sendMessage(a + "Repeatedly smack things with your sword!");
		sender.sendMessage(a + "If you receive TNT place it on obsidian to explode things!");
		sender.sendMessage(a + "To join the game say /join and you will be teleported out of spectate.");
		
		return true;
	}

}
