package ch.jamiete.war.listeners;

import java.util.Date;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import ch.jamiete.war.War;


public class PlayerMove implements Listener {
	private War war;
	
	public PlayerMove(War war) {
		this.war = war;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		war.afk.put(event.getPlayer(), (new Date()).getTime());
		Location oldLoc = event.getPlayer().getLocation();
		Location newLoc = oldLoc;
		newLoc.setY(newLoc.getY() + 1);
		if (war.monster.contains(event.getPlayer())) {
			event.getPlayer().getWorld().playEffect(newLoc, Effect.MOBSPAWNER_FLAMES, 1, 100);
			event.getPlayer().getWorld().playEffect(newLoc, Effect.STEP_SOUND, 1, 100);
		}
	}

}
