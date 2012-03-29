package tk.nekotech.war.events;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.Packet20NamedEntitySpawn;
import net.minecraft.server.Packet29DestroyEntity;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tk.nekotech.war.War;

public class PlayerJoin implements Listener {
	private War war;
	
	public PlayerJoin(War war) {
		this.war = war;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		//TODO: Proper name coloring
		/*for (int b = 0; b < war.blu.size(); b++) {
			Player player = war.blu.get(b);
			Player toSend = e.getPlayer();
			EntityPlayer newPlayer = ((CraftPlayer) player).getHandle();
			newPlayer.name = ChatColor.BLUE + player.getName();
			((CraftPlayer) toSend).getHandle().netServerHandler.sendPacket(new Packet29DestroyEntity(player.getEntityId()));
    		((CraftPlayer) toSend).getHandle().netServerHandler.sendPacket(new Packet20NamedEntitySpawn(newPlayer));
    		newPlayer.name = player.getName();
		}
		
		for (int b = 0; b < war.red.size(); b++) {
			Player player = war.red.get(b);
			Player toSend = e.getPlayer();
			EntityPlayer newPlayer = ((CraftPlayer) player).getHandle();
			newPlayer.name = ChatColor.RED + player.getName();
			((CraftPlayer) toSend).getHandle().netServerHandler.sendPacket(new Packet29DestroyEntity(player.getEntityId()));
    		((CraftPlayer) toSend).getHandle().netServerHandler.sendPacket(new Packet20NamedEntitySpawn(newPlayer));
    		newPlayer.name = player.getName();
		}*/
		
		// have this at top so it can get overriden if required
		double x = war.getConfig().getDouble("spec-spawn-x");
		double y = war.getConfig().getDouble("spec-spawn-y");
		double z = war.getConfig().getDouble("spec-spawn-z");
		float yaw = war.getConfig().getInt("spec-spawn-yaw");
		float pitch = war.getConfig().getInt("spec-spawn-pitch");
		e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), x, y, z, yaw, pitch));
		war.teamhelpers.clearTeams(e.getPlayer());
		war.on++;
		war.online.add(e.getPlayer());
		if (!e.getPlayer().hasPlayedBefore()) {
			e.getPlayer().chat("[AUTO] I am new here! If I break the rules I acknowledge that I WILL be banned!");
		}
		
		if (war.getConfig().getBoolean("has-started")) {
			double sx = war.getConfig().getDouble("spec-spawn-x");
			double sy = war.getConfig().getDouble("spec-spawn-y");
			double sz = war.getConfig().getDouble("spec-spawn-z");
			float syaw = war.getConfig().getInt("spec-spawn-yaw");
			float spitch = war.getConfig().getInt("spec-spawn-pitch");
			e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), sx, sy, sz, syaw, spitch));
		} else {
			if (e.getPlayer().hasPermission("jtwar.admin")) {
				e.setJoinMessage(ChatColor.YELLOW + e.getPlayer().getName() + " is joining as an admin on the new map!");
				e.getPlayer().sendMessage(ChatColor.RED + "[jtWAR] Teleporting you to spawn immediately to setup new map.");
				e.getPlayer().sendMessage(ChatColor.RED + "[jtWAR] Please set new spawn points with /blu and /red");
				e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
				e.getPlayer().setGameMode(GameMode.CREATIVE);
			}
		}
		
		e.setJoinMessage(ChatColor.YELLOW + e.getPlayer().getName() + " joins the war!");
		e.getPlayer().sendMessage(ChatColor.RED + "Welcome to the war! To join type /join");
		e.getPlayer().sendMessage(ChatColor.RED + "For more information say /war");
		
	}

}
