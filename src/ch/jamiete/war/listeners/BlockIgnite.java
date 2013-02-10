package ch.jamiete.war.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

public class BlockIgnite implements Listener {
	
	@EventHandler
	public void onBlockIgnite(BlockIgniteEvent event) {
		event.setCancelled(true);
	}

}
