package ch.jamiete.war.commands;

import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ch.jamiete.war.War;

public class JoinCommand extends MasterCommand {
    private final War war;

    public JoinCommand(final War war) {
        this.war = war;
    }

    @Override
    public boolean doCommand(final CommandSender sender, final String command, final String[] args, final boolean realPlayer, final Player player) {
        if (realPlayer) {
            if (!this.war.teamhelpers.onTeam(player)) {
                if (this.war.blu.size() == this.war.red.size()) {
                    final Random rand = new Random();
                    final int decider = rand.nextInt(2);
                    this.war.assignment.assignPlayer(player, decider);
                } else if (this.war.blu.size() < this.war.red.size()) {
                    this.war.assignment.assignPlayer(player, 0);
                } else if (this.war.red.size() < this.war.blu.size()) {
                    this.war.assignment.assignPlayer(player, 1);
                }
            } else {
                this.war.sendMessage(player, ChatColor.DARK_RED + "You're already on a team, dolt!");
                String team = null;
                if (this.war.teamhelpers.teamName(player) == 0) {
                    team = "blu";
                } else if (this.war.teamhelpers.teamName(player) == 1) {
                    team = "red";
                } else if (this.war.teamhelpers.teamName(player) == 2) {
                    team = "admin";
                } else {
                    player.kickPlayer(ChatColor.RED + "Uncaught exception!");
                }
                this.war.sendMessage(player, ChatColor.DARK_RED + "You're on team: " + team);
            }
        } else {
            sender.sendMessage(ChatColor.AQUA + "In-game command only.");
        }

        return true;
    }

}
