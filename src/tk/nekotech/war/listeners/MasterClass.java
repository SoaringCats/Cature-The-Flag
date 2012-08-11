package tk.nekotech.war.listeners;

import org.bukkit.event.Listener;

import tk.nekotech.war.War;

public class MasterClass implements Listener {
	protected War war;
	
	public MasterClass(War war) {
		war.getServer().getPluginManager().registerEvents(this, war);
		this.war = war;
	}

}
