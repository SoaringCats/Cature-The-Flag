package tk.nekotech.war.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.nekotech.war.War;

public class WarCommand extends MasterCommand {
	private War war;
	private ChatColor a = ChatColor.AQUA;
	
	public WarCommand(War war) {
		this.war = war;
	}
	
	@Override
	public boolean doCommand(CommandSender sender, String command, String[] args, boolean realPlayer, Player player) {
		war.sendMessage(player, a + "Welcome to the war, " + sender.getName() + "!");
		war.sendMessage(player, a + "Repeatedly smack things with your sword!");
		war.sendMessage(player, a + "If you receive TNT, place it on obsidian to explode things!");
		war.sendMessage(player, a + "To join the game say /join and you will be teleported out of spectate.");
		
		return true;
	}

}
