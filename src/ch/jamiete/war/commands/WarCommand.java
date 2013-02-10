package ch.jamiete.war.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ch.jamiete.war.War;

public class WarCommand extends MasterCommand {
    private final War war;
    private final ChatColor a = ChatColor.AQUA;

    public WarCommand(final War war) {
        this.war = war;
    }

    @Override
    public boolean doCommand(final CommandSender sender, final String command, final String[] args, final boolean realPlayer, final Player player) {
        this.war.sendMessage(player, this.a + "Welcome to the war, " + sender.getName() + "!");
        this.war.sendMessage(player, this.a + "Repeatedly smack things with your sword!");
        this.war.sendMessage(player, this.a + "If you receive TNT, place it on obsidian to explode things!");
        this.war.sendMessage(player, this.a + "To join the game say /join and you will be teleported out of spectate.");

        return true;
    }

}
