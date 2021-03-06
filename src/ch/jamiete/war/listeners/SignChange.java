package ch.jamiete.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;

public class SignChange extends MasterListener {

    public SignChange(final War war) {
        super(war);
    }

    @EventHandler
    public void onSignChange(final SignChangeEvent event) {
        event.setLine(0, ChatColor.translateAlternateColorCodes('&', event.getLine(0)));
        event.setLine(1, ChatColor.translateAlternateColorCodes('&', event.getLine(1)));
        event.setLine(2, ChatColor.translateAlternateColorCodes('&', event.getLine(2)));
        event.setLine(3, ChatColor.translateAlternateColorCodes('&', event.getLine(3)));
    }

}
