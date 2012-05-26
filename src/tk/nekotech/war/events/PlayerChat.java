package tk.nekotech.war.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import tk.nekotech.war.War;

public class PlayerChat implements Listener {
	private War war;
	
	public PlayerChat(War war) {
		this.war = war;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat(PlayerChatEvent event) {
		event.setFormat(war.getMessage() + " %s > %s");
		if (event.getMessage().startsWith(".")) {
			war.teamhelpers.teamMessage(war.teamhelpers.teamName(event.getPlayer()), "(TEAM) <" + event.getPlayer().getDisplayName() + "> " + event.getMessage());
			event.setCancelled(true);
		}
	}

}
