package ch.jamiete.war.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ch.jamiete.war.MasterCommand;
import ch.jamiete.war.War;
import ch.jamiete.war.helpers.WarPlayer;
import ch.jamiete.war.helpers.WarValues;

public class WarCommand extends MasterCommand {

    public WarCommand(final War war) {
        super(war);
    }

    @Override
    public void execute(final CommandSender sender, final String command, final String[] args, final boolean realPlayer, final Player player) {
        if (!realPlayer) {
            sender.sendMessage(WarValues.MESSAGE_CONSOLE);
        }

        final WarPlayer wplayer = this.war.getHelper().getPlayerExact(player.getName());

        wplayer.sendMessage(ChatColor.GRAY + "Welcome to the war, " + sender.getName() + "!");
        wplayer.sendMessage(ChatColor.GRAY + "Repeatedly smack things with your sword!");
        wplayer.sendMessage(ChatColor.GRAY + "If you receive TNT, place it on obsidian to explode things!");
        wplayer.sendMessage(ChatColor.GRAY + "To join the game say /join and you will be teleported out of spectate.");
    }

}
