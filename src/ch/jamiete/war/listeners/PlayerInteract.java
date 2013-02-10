package ch.jamiete.war.listeners;

import java.util.Date;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ch.jamiete.war.War;

public class PlayerInteract implements Listener {
    private final War war;

    public PlayerInteract(final War war) {
        this.war = war;
    }

    private void buffPlayer(final Player player) {
        this.war.getServer().broadcastMessage(this.war.getMessage() + ChatColor.AQUA + player.getName() + " got a buff from the Magical Temple!");
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 1));
        player.setExp(player.getExp() + 300F);
        // Once more to update!
        player.setExp(player.getExp());
    }

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        this.war.afk.put(event.getPlayer(), new Date().getTime());
        final Random random = new Random();
        if (event.getClickedBlock() != null) {
            if (event.getClickedBlock().getState() instanceof Sign) {
                final Sign sign = (Sign) event.getClickedBlock().getState();
                final Player player = event.getPlayer();
                if (player.getGameMode() == GameMode.SURVIVAL) {
                    if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        return;
                    }
                }
                if (ChatColor.stripColor(sign.getLine(0)).equals("[BLU]")) {
                    if (this.war.teamhelpers.teamName(player) == 0) {
                        this.buffPlayer(event.getPlayer());
                        this.war.teamhelpers.toSpawn(player, this.war.teamhelpers.teamName(player));
                        this.war.sendMessage(player, ChatColor.AQUA + "Whoosh!");
                    } else {
                        this.war.sendMessage(player, ChatColor.RED + "You can't hit another teams sign! Find your own...");
                    }
                }
                if (ChatColor.stripColor(sign.getLine(0)).equals("[RED]")) {
                    if (this.war.teamhelpers.teamName(player) == 1) {
                        this.buffPlayer(event.getPlayer());
                        this.war.teamhelpers.toSpawn(player, this.war.teamhelpers.teamName(player));
                        this.war.sendMessage(player, ChatColor.AQUA + "Whoosh!");
                    } else {
                        this.war.sendMessage(player, ChatColor.RED + "You can't hit another teams sign! Find your own...");
                    }
                }
                if (ChatColor.stripColor(sign.getLine(0)).equals("[JKS]")) {
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HARM, 600, 1));
                    this.war.teamhelpers.toSpawn(player, this.war.teamhelpers.teamName(player));
                    this.war.sendMessage(player, ChatColor.AQUA + "Whoosh!");
                }
                if (ChatColor.stripColor(sign.getLine(1)).equals("It's good to")) {
                    final Inventory inventory = this.war.getServer().createInventory(event.getPlayer(), 27);
                    final ItemStack[] items = new ItemStack[27];
                    if (random.nextBoolean()) {
                        for (int i = 0; i < 27; i++) {
                            final int r = random.nextInt(27);
                            final int amt = random.nextInt(63) + 1;
                            if (r == 0) {
                                items[i] = new ItemStack(Material.DIAMOND_SWORD, 1);
                                items[i].setDurability((short) 1661);
                            }
                            if (r == 1) {
                                items[i] = new ItemStack(Material.GHAST_TEAR, amt);
                            }
                            if (r == 2) {
                                items[i] = new ItemStack(Material.SLIME_BALL, amt);
                            }
                            if (r == 3) {
                                items[i] = new ItemStack(Material.WOOD_SWORD, 1);
                                items[i].setDurability((short) 25);
                            }
                            if (r == 4) {
                                items[i] = new ItemStack(Material.DIRT, amt);
                            }
                            if (r == 5) {
                                items[i] = new ItemStack(Material.TNT, amt);
                            }
                            if (r == 6) {
                                items[i] = new ItemStack(Material.BRICK, amt);
                            }
                            if (r == 7) {
                                items[i] = new ItemStack(Material.GLASS_BOTTLE, amt);
                            }
                            if (r == 8) {
                                items[i] = new ItemStack(Material.STICK, amt);
                            }
                            if (r == 9) {
                                items[i] = new ItemStack(Material.DIAMOND, amt);
                            }
                            if (r == 10 || r == 11) {
                                // TODO Spawn Experience
                                //event.getPlayer().getLocation().getWorld().spawnCreature(event.getPlayer().getLocation(), EntityType.EXPERIENCE_ORB);
                            }
                        }
                        inventory.setContents(items);
                        event.getPlayer().openInventory(inventory);
                        this.war.inventory.add(event.getPlayer());
                    } else {
                        this.war.teamhelpers.toSpawn(player, this.war.teamhelpers.teamName(player));
                        this.war.sendMessage(player, ChatColor.AQUA + "No prize here.");
                    }
                }
                if (ChatColor.stripColor(sign.getLine(1)).equals("You might find")) {
                    if (random.nextBoolean()) {
                        event.getPlayer().openWorkbench(null, true);
                    } else {
                        event.getPlayer().openEnchanting(null, true);
                    }
                    this.war.inventory.add(event.getPlayer());
                }
            }
        }
    }

}
