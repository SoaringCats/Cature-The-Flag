package tk.nekotech.war.commands;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.nekotech.war.War;

public class JoinCommand extends MasterCommand {
	private War war;
	
	public JoinCommand(War war) {
		this.war = war;
	}

	@Override
	public boolean doCommand(CommandSender sender, String command, String[] args, boolean realPlayer, Player player) {
		if (realPlayer) {
			if (!war.teamhelpers.onTeam(player)) {
				if (war.blu.size() == war.red.size()) {
					Random rand = new Random();
					int decider = rand.nextInt(2);
					war.assignment.assignPlayer(player, decider);
				} else if (war.blu.size() < war.red.size()) {
					war.assignment.assignPlayer(player, 0);
				} else if (war.red.size() < war.blu.size()) {
					war.assignment.assignPlayer(player, 1);
				}
			} else {
				player.sendMessage(ChatColor.DARK_RED + "You're already on a team, dolt!");
				String team = null;
				if (war.teamhelpers.teamName(player) == 0) {
					team = "blu";
				} else if (war.teamhelpers.teamName(player) == 1) {
					team = "red";
				} else {
					player.kickPlayer(ChatColor.RED + "Uncaught exception!");
				}
				player.sendMessage(ChatColor.DARK_RED + "You're on team: " + team);
			}
		} else {
			sender.sendMessage(ChatColor.AQUA + "In-game command only.");
		}
		
		return true;
	}

}
