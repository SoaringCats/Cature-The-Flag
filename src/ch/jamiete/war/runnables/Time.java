package ch.jamiete.war.runnables;

import org.bukkit.World;
import ch.jamiete.war.War;


public class Time implements Runnable {
	private War war;
	
	public Time(War war) {
		this.war = war;
	}

	@Override
	public void run() {
		for (World world : war.getServer().getWorlds()) {
			world.setTime(14000);
		}
	}

}
