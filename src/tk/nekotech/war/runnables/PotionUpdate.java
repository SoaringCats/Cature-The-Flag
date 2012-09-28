package tk.nekotech.war.runnables;

import org.bukkit.potion.PotionEffect;

import tk.nekotech.war.War;
import tk.nekotech.war.player.WarPlayer;

public class PotionUpdate implements Runnable {
	private War war;
	
	public PotionUpdate(War war) {
		this.war = war;
		this.war.getServer().getScheduler().scheduleSyncRepeatingTask(war, this, 40L, 40L);
	}

	@Override
	public void run() {
		for (WarPlayer player : war.getPlayers()) {
			for (PotionEffect effect : player.getPotions()) {
				player.getPlayer().addPotionEffect(effect, true);
			}
		}
	}

}
