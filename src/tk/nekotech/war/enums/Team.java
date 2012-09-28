package tk.nekotech.war.enums;

import org.bukkit.Location;

import tk.nekotech.war.LocationStore;
import tk.nekotech.war.War;

public enum Team {

	RED, BLUE;
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}
	
	public static Location getSpawn(String config, War war) {
		return (new LocationStore(config, war)).getLocation();
	}
	
}
