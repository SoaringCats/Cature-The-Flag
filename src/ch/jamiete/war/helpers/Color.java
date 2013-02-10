package ch.jamiete.war.helpers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ch.jamiete.war.War;

public class Color {
    private final War war;

    public Color(final War war) {
        this.war = war;
    }

    public void setColor(final ColorChoice color, final Player player) {
        final int len = player.getName().length();
        String name = player.getName();
        if (len == 16) {
            name = player.getName().substring(0, 13);
        } else if (len == 15) {
            name = player.getName().substring(0, 14);
        }
        boolean setName = true;
        ChatColor c = null;
        switch (color) {
            case BLU:
                c = ChatColor.BLUE;
                this.setHelmet(player, 0);
                break;
            case RED:
                c = ChatColor.RED;
                this.setHelmet(player, 1);
                break;
            case PURPLE:
                c = ChatColor.LIGHT_PURPLE;
                setName = false;
                this.setHelmet(player, 2);
                break;
            case GRAY:
                c = ChatColor.GRAY;
                this.setHelmet(player, 9);
                break;
        }
        if (setName) {
            player.setDisplayName(c + player.getName() + ChatColor.WHITE);
        }
        player.setPlayerListName(c + name);
        //setHeadName(player, c, name);
    }

    /**
     * Head name <b>requires</b> jamietech's custom Bukkit version
     * @param player
     */
    public void setHeadName(final Player player, final ChatColor color, final String name) {
        player.getMetadata("headname").clear();
        //player.getMetadata("headname").add(color + name);
    }

    public void setHelmet(final Player player) {
        final int teamID = this.war.teamhelpers.teamName(player);
        switch (teamID) {
            case 0:
                player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) 11));
                break;
            case 1:
                player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) 14));
                break;
            case 9:
                player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) 8));
                break;
        }
    }

    public void setHelmet(final Player player, final int id) {
        switch (id) {
            case 0:
                player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) 11));
                break;
            case 1:
                player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) 14));
                break;
            case 9:
                player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) 8));
                break;
            case 2:
                player.getInventory().setHelmet(new ItemStack(Material.WOOL, 1, (byte) 2));
        }
    }

}
