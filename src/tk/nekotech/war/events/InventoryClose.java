package tk.nekotech.war.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import tk.nekotech.war.War;
import tk.nekotech.war.helpers.ColorChoice;

public class InventoryClose implements Listener {
	private War war;
	
	public InventoryClose(War war) {
		this.war = war;
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		Player player = (Player) event.getPlayer();
		if ( player.getGameMode() == GameMode.CREATIVE ){
			return;
		}
		if ((player.getInventory().getHelmet() == null) || (player.getInventory().getHelmet().getType() != Material.WOOL)) {
			if (war.teamhelpers.teamName(player) == 0) {
				war.color.setColor(ColorChoice.BLU, player);
			} else if (war.teamhelpers.teamName(player) == 1) {
				war.color.setColor(ColorChoice.RED, player);
			} else {
				war.color.setColor(ColorChoice.GRAY, player);
			}
		}
	}

}
