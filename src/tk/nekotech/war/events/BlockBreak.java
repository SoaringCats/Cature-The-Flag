package tk.nekotech.war.events;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
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
			war.sendMessage(event.getPlayer(), ChatColor.RED + "You can't break blocks!");
		}
		if (event.isCancelled()) {
			if (event.getBlock().getState() instanceof Sign) {
				Sign sign = (Sign) event.getBlock();
				sign.update();
			}
		}
	}

}
