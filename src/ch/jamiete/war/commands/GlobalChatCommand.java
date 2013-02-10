package ch.jamiete.war.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ch.jamiete.war.MasterCommand;
import ch.jamiete.war.War;
import ch.jamiete.war.helpers.WarValues;

public class GlobalChatCommand extends MasterCommand {

    public GlobalChatCommand(final War war) {
        super(war);
    }

    @Override
    public void execute(final CommandSender sender, final String command, final String[] args, final boolean realPlayer, final Player player) {
        if (!realPlayer) {
            sender.sendMessage(WarValues.MESSAGE_CONSOLE);
            return;
        }

        if (args.length == 0) {
            sender.sendMessage(WarValues.MESSAGE_PREFIX + ChatColor.GRAY + "You need to specify a message. /" + command + " <message>");
            return;
        }

        this.war.getServer().broadcastMessage(String.format(WarValues.MESSAGE_PREFIX + "%s > %s", player.getDisplayName(), this.war.getHelper().arrayToString(args)));
    }
}
