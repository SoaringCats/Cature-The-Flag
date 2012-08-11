package tk.nekotech.war.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import tk.nekotech.war.War;

public class Cancellables extends MasterClass implements Listener {
	
	public Cancellables(War war) {
		super(war);
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		event.setCancelled(true);
	}

}
