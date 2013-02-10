package ch.jamiete.war.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class MasterCommand implements CommandExecutor {

    public abstract boolean doCommand(CommandSender sender, String command, String[] args, boolean realPlayer, Player player);

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final boolean real = sender instanceof Player;
        Player player = null;
        if (real) {
            player = (Player) sender;
        }
        return this.doCommand(sender, label, args, real, player);
    }

}
