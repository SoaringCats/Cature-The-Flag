package ch.jamiete.war.listeners;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import ch.jamiete.war.War;


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
				final Sign sign = (Sign) event.getBlock();
				war.getServer().getScheduler().scheduleSyncDelayedTask(war, new Runnable() {
					@Override
					public void run() {
						// Attempt to fix signs not displaying any text to any players when broken and replaced by plugins.
						sign.setLine(0, sign.getLine(0));
						sign.setLine(1, sign.getLine(1));
						sign.setLine(2, sign.getLine(2));
						sign.setLine(3, sign.getLine(3));
						sign.update();
					}
				}, 10L);
			}
		}
	}

}
