package tk.nekotech.war.helpers;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import tk.nekotech.war.War;

public class QuickPlayer {
	private War war;
	
	public QuickPlayer(War war) {
		this.war = war;
	}
	
	public void clearAttachments(Player player) {
		war.admins.remove(player);
		war.afk.remove(player);
		war.blu.remove(player);
		war.inventory.remove(player);
		war.medic.remove(player);
		war.monster.remove(player);
		war.pyro.remove(player);
		war.red.remove(player);
	}
	
	public void playerLeave(Player player) {
		if (war.getServer().getOnlinePlayers().length == 1) {
			war.getLogger().info("Performing garbage collection on all worlds.");
			for (World world : war.getServer().getWorlds()) {
				war.getLogger().info("Clearing " + world.getEntities().size() + " entities from " + world.getName() + " for garbage collection!");
				for (Entity entity : world.getEntities()) {
					entity.remove();
				}
			}
			war.getLogger().info("Garbage collection complete.");
		}
	}

}
