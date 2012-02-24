package tk.nekotech.war;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.server.MobEffect;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class War extends JavaPlugin implements Listener {
	public ArrayList<String> online = new ArrayList<String>();
	public ArrayList<String> pyro = new ArrayList<String>();
	public ArrayList<String> blu = new ArrayList<String>();
	public ArrayList<String> red = new ArrayList<String>();
	public int on = 0;
	public int max = 0;
	public int dead = 0;
	
	public void onEnable() {
		getLogger().info("Enabled!");
		getCommand("war").setExecutor(new Commands(this));
		getCommand("join").setExecutor(new Commands(this));
		getCommand("blu").setExecutor(new Commands(this));
		getCommand("red").setExecutor(new Commands(this));
		getCommand("ready").setExecutor(new Commands(this));
		getCommand("spectate").setExecutor(new Commands(this));
		getCommand("reset").setExecutor(new Commands(this));
		if (!getConfig().getBoolean("has-started")) {
			getLogger().warning("This is the first logged startup event (did you clear your config?");
			getLogger().warning("Please make sure you set the coordinates for the new spawn areas.");
		}
		getLogger().info("Please do not modify the config as it changes often during gameplay.");
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new SpawnManager(this), this);
		max = getServer().getMaxPlayers();
		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				int worlds = 0;
				int arrows = 0;
				for (World w : getServer().getWorlds()) {
					w.setStorm(false);
					w.setTime(14000);
					worlds++;
					for (Entity e : w.getEntities()) {
						if (e instanceof Arrow) {
							e.remove();
							arrows++;
						}
					}
				}
				getLogger().info("Set time to night in " + worlds + " worlds and cleared " + arrows + " arrows up!");
			}
		}, 40L, 4800L);
	}
	
	public void onDisable() {
		getLogger().info("Disabled!");
	}
	
	public ChunkGenerator getDefaultWorldGenerator(String worldname, String uid) {
		return new Gen(this);
	}
	
	public boolean onTeam(Player player) {
		if (blu.contains(player.getName())) {
			return true;
		} else if (red.contains(player.getName())) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isAllowed(EntityType lmao) {
		if (lmao.equals(EntityType.SPIDER)) return true;
		if (lmao.equals(EntityType.CAVE_SPIDER)) return true;
		if (lmao.equals(EntityType.SKELETON)) return true;
		if (lmao.equals(EntityType.CREEPER)) return true;
		if (lmao.equals(EntityType.PIG_ZOMBIE)) return true;
		if (lmao.equals(EntityType.ZOMBIE)) return true;
		return false;
	}
	
	public EntityType randoMob() {
		Random rand = new Random();
		int lmao = rand.nextInt(9);
		if (lmao == 0) return EntityType.SPIDER;
		if (lmao == 1) return EntityType.CAVE_SPIDER;
		if (lmao == 2) return EntityType.SKELETON;
		if (lmao == 3) return EntityType.CREEPER;
		if (lmao == 4) return EntityType.PIG_ZOMBIE;
		if (lmao == 5) return EntityType.ZOMBIE;
		if (lmao == 6) return EntityType.ZOMBIE;
		if (lmao == 7) return EntityType.ZOMBIE;
		if (lmao == 8) return EntityType.CAVE_SPIDER;
		return null;
	}
	
	public void killMob(Entity entity) {
		if (entity != null) {
			entity.getWorld().strikeLightningEffect(entity.getLocation());
			entity.remove();
		}
	}
		
	public void teamSpawn(Player player) {
		if (teamName(player) == 0) {
			double x = getConfig().getDouble("blu-spawn-x");
			double y = getConfig().getDouble("blu-spawn-y");
			double z = getConfig().getDouble("blu-spawn-z");
			float yaw = getConfig().getInt("blu-spawn-yaw");
			float pitch = getConfig().getInt("blu-spawn-pitch");
			player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));
		}
		if (teamName(player) == 1) {
			double x = getConfig().getDouble("red-spawn-x");
			double y = getConfig().getDouble("red-spawn-y");
			double z = getConfig().getDouble("red-spawn-z");
			float yaw = getConfig().getInt("red-spawn-yaw");
			float pitch = getConfig().getInt("red-spawn-pitch");
			player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));
		}
	}
	
	public int teamName(Player player) {
		if (blu.contains(player.getName())) {
			return 0;
		} else if (red.contains(player.getName())) {
			return 1;
		} else {
			return 9;
		}
	}
	
	public void teamMessage(int team, String message) {
		if (team == 0) {
			for (Player p : getServer().getOnlinePlayers()) {
				if (blu.contains(p.getName())) {
					p.sendMessage(message);
				}
			}
			getLogger().info("[BLU] " + message);
		} else if (team == 1) {
			for (Player p : getServer().getOnlinePlayers()) {
				if (red.contains(p.getName())) {
					p.sendMessage(message);
				}
			}
			getLogger().info("[RED] " + message);
		} else {
			getLogger().severe("Unknown team caught in teamMessage! Attempted message:");
			getLogger().severe(message);
		}
	}
	
	public void clearTeams(Player player) {
		if (blu.contains(player.getName())) blu.remove(player.getName());
		if (red.contains(player.getName())) red.remove(player.getName());
		if (pyro.contains(player.getName())) pyro.remove(player.getName());
		player.setDisplayName(ChatColor.GRAY + player.getName() + ChatColor.WHITE);
		player.getInventory().clear();
	}
	
	public void assignPlayer(Player player, int team) {
		if (blu.contains(player.getName())) blu.remove(player.getName());
		if (red.contains(player.getName())) red.remove(player.getName());
		if (pyro.contains(player.getName())) pyro.remove(player.getName());
		if (player.getGameMode() == GameMode.CREATIVE) player.setGameMode(GameMode.SURVIVAL);
		if (team == 0) {
			blu.add(player.getName());
			getServer().broadcastMessage(ChatColor.BLUE + player.getName() + " was auto assigned to team blu. Blu - " + blu.size() + " Red - " + red.size());
			double x = getConfig().getDouble("blu-spawn-x");
			double y = getConfig().getDouble("blu-spawn-y");
			double z = getConfig().getDouble("blu-spawn-z");
			float yaw = getConfig().getInt("blu-spawn-yaw");
			float pitch = getConfig().getInt("blu-spawn-pitch");
			player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));
			player.setDisplayName(ChatColor.BLUE + player.getName() + ChatColor.WHITE);
			if ((player.getName().length() == 14) || (player.getName().length() == 15)) {
				// cut the name down
			} else {
				player.setPlayerListName(ChatColor.BLUE + player.getName() + ChatColor.WHITE);
			}
			assignClass(player);
		} else if (team == 1) {
			red.add(player.getName());
			getServer().broadcastMessage(ChatColor.RED + player.getName() + " was auto assigned to team red. Blu - " + blu.size() + " Red - " + red.size());
			double x = getConfig().getDouble("red-spawn-x");
			double y = getConfig().getDouble("red-spawn-y");
			double z = getConfig().getDouble("red-spawn-z");
			float yaw = getConfig().getInt("red-spawn-yaw");
			float pitch = getConfig().getInt("red-spawn-pitch");
			player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));
			player.setDisplayName(ChatColor.RED + player.getName() + ChatColor.WHITE);
			if ((player.getName().length() == 14) || (player.getName().length() == 15)) {
				// cut the name down
			} else {
				player.setPlayerListName(ChatColor.RED + player.getName() + ChatColor.WHITE);
			}
			assignClass(player);
		} else {
			player.kickPlayer(ChatColor.RED + "Uncaught error, try rejoining. If problem persists contact admin.");
			getLogger().severe("Kicked " + player.getName() + " due to error in random generator!");
			getLogger().severe("If this problem persists contact jamietech on GitHub!");
		}
	}
	
	public void assignClass(final Player player) {
		Random rand = new Random();
		int lols = rand.nextInt(4);
		player.getInventory().clear();
		if (lols == 0) {
			// Heavy!
			getServer().getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {
			    public void run() {
			    	((CraftPlayer) player).getHandle().addEffect(new MobEffect(2, 999999999, 2)); // Slowness
					((CraftPlayer) player).getHandle().addEffect(new MobEffect(11, 999999999, 1)); // Resistance
					player.sendMessage(ChatColor.GOLD + "You are now a heavy!");
					player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
					player.getInventory().getItem(0).addEnchantment(Enchantment.DAMAGE_ALL, 2);
					player.setNoDamageTicks(10);
					armorUp(player);
			    }
			}, 10L);
		}
		if (lols == 1) {
			// Scout!
			getServer().getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {
			    public void run() {
			    	((CraftPlayer) player).getHandle().addEffect(new MobEffect(1, 999999999, 1)); // Speed
					player.sendMessage(ChatColor.GOLD + "You are now a scout!");
					player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
					player.getInventory().getItem(0).addEnchantment(Enchantment.KNOCKBACK, 2);
					player.setNoDamageTicks(10);
					armorUp(player);
			    }
			}, 10L);
		}
		if (lols == 2) {
			// Sniper!
			getServer().getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {
			    public void run() {
			    	((CraftPlayer) player).getHandle().addEffect(new MobEffect(11, 999999999, 2)); // Resistance
					player.sendMessage(ChatColor.GOLD + "You are now a sniper!");
					player.getInventory().addItem(new ItemStack(Material.BOW, 1));
					player.getInventory().addItem(new ItemStack(Material.ARROW, 1));
					player.getInventory().getItem(0).addEnchantment(Enchantment.ARROW_INFINITE, 1);
					player.getInventory().getItem(0).addEnchantment(Enchantment.ARROW_DAMAGE, 2);
					player.setNoDamageTicks(10);
					armorUp(player);
			    }
			}, 10L);
		}
		if (lols == 3) {
			// Pyro!
			getServer().getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {
			    public void run() {
			    	((CraftPlayer) player).getHandle().addEffect(new MobEffect(12, 999999999, 3)); // Fire Resistance
					pyro.add(player.getName());
					player.sendMessage(ChatColor.GOLD + "You are now a pyro!");
					player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
					// "Enchantment" granted with 2s fire bursts against all entities via SpawnManger
					player.setNoDamageTicks(10);
					armorUp(player);
			    }
			}, 10L);
		}
		
	}
	
	public void armorUp(final Player player) {
		getServer().getScheduler().scheduleAsyncDelayedTask(this, new Runnable() {
		    public void run() {
		    	player.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
				player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
				player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
				player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
				Random rand = new Random();
				int lolrandom = rand.nextInt(11);
				if (lolrandom == 10) {
					player.getInventory().addItem(new ItemStack(Material.TNT, 64));
					player.sendMessage(ChatColor.GOLD + "You have special EXPLODING abilities!");
					getServer().broadcastMessage(ChatColor.GOLD + player.getName() + " issSSssSS armed and dangerous! Stay away!");
				}
		    }
		}, 10L);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void holyShitNewMap(PlayerJoinEvent e) {
		if (!getConfig().getBoolean("has-started")) {
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
		double x = getConfig().getDouble("spec-spawn-x");
		double y = getConfig().getDouble("spec-spawn-y");
		double z = getConfig().getDouble("spec-spawn-z");
		float yaw = getConfig().getInt("spec-spawn-yaw");
		float pitch = getConfig().getInt("spec-spawn-pitch");
		e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), x, y, z, yaw, pitch));
		clearTeams(e.getPlayer());
		on++;
		online.add(e.getPlayer().getName());
		if (!e.getPlayer().hasPlayedBefore()) {
			e.getPlayer().chat("[AUTO] I am new here! If I break the rules I acknowledge that I WILL be banned!");
		}
	}
		
	@EventHandler(priority = EventPriority.MONITOR)
	public void omgTagging(PlayerQuitEvent e) {
		on--;
		online.remove(e.getPlayer().getName());
		if (red.contains(e.getPlayer().getName())) red.remove(e.getPlayer().getName());
		if (blu.contains(e.getPlayer().getName())) blu.remove(e.getPlayer().getName());
		if (pyro.contains(e.getPlayer().getName())) pyro.remove(e.getPlayer().getName());
		e.setQuitMessage(ChatColor.YELLOW + e.getPlayer().getName() + " chickened out.");
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void dontTouchThat(BlockBreakEvent e) {
		if (getConfig().getBoolean("ready-to-go")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "The objective of the game is to kill all of the other team");
			e.getPlayer().sendMessage(ChatColor.RED + "To do this you don't need to break blocks!");
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void dontPlaceThat(BlockPlaceEvent e) {
		if (getConfig().getBoolean("ready-to-go")) {
			if (e.getBlockPlaced().getType().equals(Material.TNT)) {
				if (!e.getBlockAgainst().getType().equals(Material.OBSIDIAN)) {
					e.setCancelled(true);
					e.getPlayer().sendMessage(ChatColor.RED + "You can only place TNT on OBSIDIAN!");
				}
			} else {
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChatColor.RED + "The objective of the game is to kill all of the other team");
				e.getPlayer().sendMessage(ChatColor.RED + "To do this you don't need to place blocks!");
			}
		}
	}

}
