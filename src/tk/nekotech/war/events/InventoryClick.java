package tk.nekotech.war.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if ((player.getInventory().getHelmet() == null) || (player.getInventory().getHelmet().getType() != Material.WOOL)) {
			event.setCancelled(true);
		}
	}

}
