package ch.jamiete.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;
import ch.jamiete.war.MasterListener;
import ch.jamiete.war.War;
import ch.jamiete.war.helpers.WarPlayer;

public class BlockPlace extends MasterListener {

    public BlockPlace(final War war) {
        super(war);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockPlace(final BlockPlaceEvent event) {
        final WarPlayer player = this.war.getHelper().getPlayerExact(event.getPlayer().getName());

        if (this.war.getConfig().getBoolean("ready-to-go")) {
            event.setCancelled(true);

            if (event.getBlockPlaced().getType().equals(Material.TNT)) {
                if (!event.getBlockAgainst().getType().equals(Material.OBSIDIAN)) {
                    player.sendMessage(ChatColor.RED + "TNT can only be placed on OBSIDIAN!");
                } else {
                    event.getBlock().getWorld().spawn(event.getBlock().getLocation(), TNTPrimed.class);
                }
            } else {
                player.sendMessage(ChatColor.RED + "You can't place blocks!");
            }
        }
    }

}
