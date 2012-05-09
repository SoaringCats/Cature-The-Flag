package tk.nekotech.war.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import tk.nekotech.war.War;

public class BlockPlace implements Listener {
	private War war;
	
	public BlockPlace(War war) {
		this.war = war;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockPlace(BlockPlaceEvent event) {
		if (war.getConfig().getBoolean("ready-to-go")) {
			if (event.getBlockPlaced().getType().equals(Material.TNT)) {
				if (!event.getBlockAgainst().getType().equals(Material.OBSIDIAN)) {
					event.setCancelled(true);
					event.getPlayer().sendMessage(ChatColor.RED + "You can only place TNT on OBSIDIAN!");
				}
			} else {
				event.setCancelled(true);
				event.getPlayer().sendMessage(ChatColor.RED + "Concentrate on killing the other team instead of placing " + event.getBlock().getType().toString().toLowerCase().replace("_", ""));
			}
		}
	}

}
