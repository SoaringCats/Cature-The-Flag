package ch.jamiete.war.helpers;

import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ch.jamiete.war.War;

public class Armor {
    private final War war;

    public Armor(final War war) {
        this.war = war;
    }

    public void armorUp(final Player player) {
        this.war.getServer().getScheduler().scheduleAsyncDelayedTask(this.war, new Runnable() {
            @Override
            public void run() {
                Armor.this.war.color.setHelmet(player, Armor.this.war.teamhelpers.teamName(player));
                player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                final Random random = new Random();
                final int rand = random.nextInt(11);
                if (rand == 10) {
                    player.getInventory().addItem(new ItemStack(Material.TNT, 64));
                    Armor.this.war.sendMessage(player, ChatColor.GOLD + "You have special EXPLODING abilities!");
                    Armor.this.war.getServer().broadcastMessage(Armor.this.war.getMessage() + ChatColor.GOLD + player.getName() + " might just explode you... stay away!");
                }
            }

        }, 10L);
    }

}
