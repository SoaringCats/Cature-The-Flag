package ch.jamiete.war.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ch.jamiete.war.MasterCommand;
import ch.jamiete.war.War;
import ch.jamiete.war.helpers.WarPlayer;
import ch.jamiete.war.helpers.WarValues;

public class JoinCommand extends MasterCommand {

    public JoinCommand(final War war) {
        super(war);
    }

    @Override
    public void execute(final CommandSender sender, final String command, final String[] args, final boolean realPlayer, final Player player) {
        if (!realPlayer) {
            sender.sendMessage(WarValues.MESSAGE_CONSOLE);
            return;
        }

        final WarPlayer wplayer = this.war.getHelper().getPlayerExact(player.getName());

        if (wplayer.isOnTeam()) {
            wplayer.sendMessage(ChatColor.GRAY + "You're already on " + wplayer.getTeam().getColored());
        } else {
            this.war.getHelper().assignPlayer(wplayer, this.war.getHelper().getAvailableTeam());
        }
    }
}
