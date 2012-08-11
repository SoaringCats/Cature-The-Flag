package tk.nekotech.war.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import tk.nekotech.war.War;
import tk.nekotech.war.player.WarPlayer;

public class PlayerFlow extends MasterClass implements Listener {
	
	public PlayerFlow(War war) {
		super(war);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		war.addPlayer(new WarPlayer(event.getPlayer(), war.getNewTeam(), war));
		event.setJoinMessage(null);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		war.removePlayer(war.getPlayer(event.getPlayer().getName()));
		event.setQuitMessage(null);
	}

}
