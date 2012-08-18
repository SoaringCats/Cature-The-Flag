package tk.nekotech.war.runnables;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import tk.nekotech.war.War;
import tk.nekotech.war.enums.Team;
import tk.nekotech.war.player.WarPlayer;

public class PlayerMessage implements Runnable {
	private WarPlayer warplayer;
	private War war;
	private int upTo = 0;
	private int runs = 0;
	
	public PlayerMessage(WarPlayer warplayer, War war) {
		this.warplayer = warplayer;
		this.war = war;
	}
	
	public void run() {
		Player player = warplayer.getPlayer();
		for (int i = 0; i < 5; i++) {
			player.sendMessage("");
		}
		player.sendMessage(ChatColor.AQUA + getMOTD());
		player.sendMessage("Red: " + war.getAmount(Team.RED) + " players " + "| Flag: [");
		player.sendMessage("Blue: " + war.getAmount(Team.BLUE) + " players " + "| Flag: [");
		player.sendMessage(((warplayer.getTeam() == Team.BLUE) ? ChatColor.BLUE : ChatColor.RED) + "You're on team " + warplayer.getTeam().toString() + " and are a " + warplayer.getClass().toString());
		player.sendMessage("Kills: " + warplayer.getKills() + " | Deaths: " + warplayer.getDeaths());
		player.sendMessage(ChatColor.GREEN + "This is an announcement.");
	}
	
	private String getMOTD() {
		List<String> list = war.getConfig().getStringList("motd_lines");
		if (upTo >= list.size()) {
			upTo = 0;
		}
		String ret = list.get(upTo);
		runs++;
		if (runs == 5) {
			upTo++;
			runs = 0;
		}
		return ret;
	}

}
