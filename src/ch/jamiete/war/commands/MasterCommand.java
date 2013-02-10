package ch.jamiete.war.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class MasterCommand implements CommandExecutor {
	
	public abstract boolean doCommand(CommandSender sender, String command, String[] args, boolean realPlayer, Player player);

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		boolean real = sender instanceof Player;
		Player player = null;
		if (real)
			player = (Player) sender;
		return doCommand(sender, label, args, real, player);
	}

}
