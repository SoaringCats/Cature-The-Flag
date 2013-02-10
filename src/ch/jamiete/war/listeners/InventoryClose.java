package ch.jamiete.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import ch.jamiete.war.War;
import ch.jamiete.war.helpers.ColorChoice;

public class InventoryClose implements Listener {
    private final War war;

    public InventoryClose(final War war) {
        this.war = war;
    }

    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent event) {
        final Player player = (Player) event.getPlayer();
        if (this.war.inventory.contains(player)) {
            this.war.inventory.remove(player);
            this.war.teamhelpers.toSpawn(player, this.war.teamhelpers.teamName(player));
            this.war.sendMessage(player, ChatColor.AQUA + "Whoosh!");
        }
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }
        if (player.getInventory().getHelmet() == null || player.getInventory().getHelmet().getType() != Material.WOOL) {
            if (this.war.teamhelpers.teamName(player) == 0) {
                this.war.color.setColor(ColorChoice.BLU, player);
            } else if (this.war.teamhelpers.teamName(player) == 1) {
                this.war.color.setColor(ColorChoice.RED, player);
            } else {
                this.war.color.setColor(ColorChoice.GRAY, player);
            }
        }
    }

}
