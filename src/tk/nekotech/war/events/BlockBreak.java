package tk.nekotech.war.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import tk.nekotech.war.War;

public class BlockBreak implements Listener {
	private War war;
	
	public BlockBreak(War war) {
		this.war = war;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent event) {
		if (war.getConfig().getBoolean("ready-to-go")) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED + "Concentrate on killing the other team instead of breaking " + event.getBlock().getType().toString().toLowerCase().replace("_", ""));
		}
	}

}
