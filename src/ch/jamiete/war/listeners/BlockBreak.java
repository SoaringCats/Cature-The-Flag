package ch.jamiete.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;
import ch.jamiete.war.helpers.WarPlayer;

public class BlockBreak extends MasterListener {

    public BlockBreak(final War war) {
        super(war);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(final BlockBreakEvent event) {
        final WarPlayer player = this.war.getHelper().getPlayerExact(event.getPlayer().getName());

        if (this.war.getConfig().getBoolean("ready-to-go")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You can't break blocks!");
        }
    }

}
