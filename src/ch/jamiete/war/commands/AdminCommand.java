package ch.jamiete.war.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ch.jamiete.war.War;
import ch.jamiete.war.helpers.ColorChoice;

public class AdminCommand extends MasterCommand {
    private final War war;

    public AdminCommand(final War war) {
        this.war = war;
    }

    @Override
    public boolean doCommand(final CommandSender sender, final String command, final String[] args, final boolean realPlayer, final Player player) {
        if (!sender.hasPermission("jtwar.admin")) {
            this.war.sendMessage(sender, ChatColor.RED + "You do not have access to that command.");
            return true;
        }
        if (!realPlayer) {
            sender.sendMessage(ChatColor.RED + "[ADMIN] In-game command only.");
            return true;
        } else {
            if (this.war.admins.contains(player)) {
                this.war.admins.remove(player);
                player.setGameMode(GameMode.SURVIVAL);
                this.war.messageAdmins(ChatColor.RED + player.getName() + " disables ADMIN MODE.");
                final int teamID = this.war.teamhelpers.teamName(player);
                switch (teamID) {
                    case 0:
                        this.war.color.setColor(ColorChoice.BLU, player);
                        break;
                    case 1:
                        this.war.color.setColor(ColorChoice.RED, player);
                        break;
                    default:
                        this.war.color.setColor(ColorChoice.GRAY, player);
                        break;
                }
            } else {
                if (args.length == 1 && (args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("announce"))) {
                    this.war.getServer().broadcastMessage(ChatColor.RED + this.war.getMessage() + ChatColor.LIGHT_PURPLE + player.getName() + " is an administrator, listen to them!");
                }
                this.war.admins.add(player);
                player.setGameMode(GameMode.CREATIVE);
                this.war.messageAdmins(ChatColor.RED + player.getName() + " enables ADMIN MODE.");
                this.war.color.setColor(ColorChoice.PURPLE, player);
                player.setDisplayName(ChatColor.LIGHT_PURPLE + player.getName());
            }
        }
        return true;
    }

}
