package tk.nekotech.war;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

public class LocationStore {
	private String string;
	private Plugin plugin;
	
	public LocationStore(String string, Plugin plugin) {
		this.string = string;
		this.plugin = plugin;
	}
	
	public Location getLocation() {
		String[] bits = getArray();
		return new Location(plugin.getServer().getWorlds().get(0), Integer.valueOf(bits[0]), Integer.valueOf(bits[1]), Integer.valueOf(bits[2]), Float.valueOf(bits[3]), Float.valueOf(bits[4]));
	}
	
	/**
	 * Returns the string.
	 * @return the string
	 */
	public String getString() {
		return string;
	}
	
	/**
	 * Returns an array of information.
	 * [0] = x
	 * [1] = y
	 * [2] = z
	 * [3] = pitch
	 * [4] = yaw
	 * @return an array of information
	 */
	public String[] getArray() {
		return string.split("'");
	}

}
