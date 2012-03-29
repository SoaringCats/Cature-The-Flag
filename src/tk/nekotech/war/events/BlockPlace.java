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
	public void onBlockPlace(BlockPlaceEvent e) {
		if (war.getConfig().getBoolean("ready-to-go")) {
			if (e.getBlockPlaced().getType().equals(Material.TNT)) {
				if (!e.getBlockAgainst().getType().equals(Material.OBSIDIAN)) {
					e.setCancelled(true);
					e.getPlayer().sendMessage(ChatColor.RED + "You can only place TNT on OBSIDIAN!");
				}
			} else {
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChatColor.RED + "Concentrate on killing the other team instead of placing " + e.getBlock().getType().toString().toLowerCase().replace("_", ""));
			}
		}
	}

}
