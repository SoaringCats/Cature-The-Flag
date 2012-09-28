package tk.nekotech.war.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.nekotech.war.War;
import tk.nekotech.war.enums.PlayerClass;
import tk.nekotech.war.player.WarPlayer;

public class ClassCommand implements CommandExecutor {
	private War war;
	
	public ClassCommand(War war) {
		this.war = war;
		war.getCommand("heavy").setExecutor(this);
		war.getCommand("sniper").setExecutor(this);
		war.getCommand("soldier").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			WarPlayer player = war.getPlayer(((Player) sender).getName());
			PlayerClass clazz = PlayerClass.getClassByName(label);
			player.setClass(clazz);
		} else {
			sender.sendMessage("Can only change class in-game.");
		}
		return true;
	}

}
