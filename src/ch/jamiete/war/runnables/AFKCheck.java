package ch.jamiete.war.runnables;

import java.util.Date;

import org.bukkit.entity.Player;
import ch.jamiete.war.War;


public class AFKCheck implements Runnable {
	private War war;
	
	public AFKCheck(War war) {
		this.war = war;
	}

	@Override
	public void run() {
		for (Player player : war.getServer().getOnlinePlayers()) {
			long seconds = (new Date()).getTime() - war.afk.get(player) / 1000;
			if (seconds <= 300) {
				player.kickPlayer("You were idling. Please rejoin :)");
			}
		}
	}

}
