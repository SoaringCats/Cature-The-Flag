package tk.nekotech.war.helpers;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.Packet20NamedEntitySpawn;
import net.minecraft.server.Packet29DestroyEntity;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import tk.nekotech.war.War;

public class Color {
	private War war;
	
	public Color(War war) {
		this.war = war;
	}
	
	public void setColor(ColorChoice color, Player player) {
		int len = player.getName().length();
		String name = player.getName();
		if (len == 16) {
			name = player.getName().substring(0, 13);
		} else if (len == 15) {
			name = player.getName().substring(0, 14);
		}
		ChatColor c = ChatColor.GRAY;
		if (color == ColorChoice.BLU) {
			c = ChatColor.BLUE;
		}
		if (color == ColorChoice.RED) {
			c = ChatColor.RED;
		}
		player.setDisplayName(c + player.getName() + ChatColor.WHITE);
		player.setPlayerListName(c + name);
		//setHeadName(player);
	}
	
	public void setHeadName(Player player) {
		// TODO: Proper name coloring
		EntityPlayer newPlayer = ((CraftPlayer) player).getHandle();
        newPlayer.name = player.getPlayerListName();
        for (Player p : war.getServer().getOnlinePlayers()) {
        	if (p != player) {
        		((CraftPlayer) p).getHandle().netServerHandler.sendPacket(new Packet29DestroyEntity(player.getEntityId()));
        		((CraftPlayer) p).getHandle().netServerHandler.sendPacket(new Packet20NamedEntitySpawn(newPlayer));
        	}
        }
        newPlayer.name = player.getName();
	}

}
