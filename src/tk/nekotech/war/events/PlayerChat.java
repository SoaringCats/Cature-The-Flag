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
	public void lolTeamChat(PlayerChatEvent e) {
		if (e.getMessage().startsWith(".")) {
			war.teamhelpers.teamMessage(war.teamhelpers.teamName(e.getPlayer()), "(TEAM) <" + e.getPlayer().getDisplayName() + "> " + e.getMessage());
			e.setCancelled(true);
		}
	}

}
