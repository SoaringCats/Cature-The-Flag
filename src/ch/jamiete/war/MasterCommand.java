package ch.jamiete.war;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class MasterCommand implements CommandExecutor {
    protected War war;

    public MasterCommand(final War war) {
        this.war = war;
    }

    public abstract void execute(CommandSender sender, String command, String[] args, boolean realPlayer, Player player);

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        this.execute(sender, label, args, sender instanceof Player, sender instanceof Player ? (Player) sender : null);
        return true;
    }

}
