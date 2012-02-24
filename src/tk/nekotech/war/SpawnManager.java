package tk.nekotech.war;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class SpawnManager implements Listener {
	private War war;
	
	public SpawnManager(War war) {
		this.war = war;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void omgMobSpawning(CreatureSpawnEvent e) {
		if (!war.getConfig().getBoolean("ready-to-go")) {
			e.setCancelled(true);
		} else {
			if (war.getConfig().getBoolean("mob-spawns")) {
				if (e.getSpawnReason() != SpawnReason.SPAWNER) {
					if (e.getSpawnReason() != SpawnReason.CUSTOM) {
						if (!war.isAllowed(e.getSpawnedType())) {
							e.getEntity().getWorld().spawnCreature(e.getEntity().getLocation(), war.randoMob());
							e.setCancelled(true);
						}
					} else {
						e.setCancelled(true);
					}
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void omgEntityDeath(EntityDeathEvent e) {
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.BLOCK_EXPLOSION) {
			 
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.CONTACT) {
			 e.setDroppedExp(0);
			 e.getDrops().clear();
			 e.getDrops().add(new ItemStack(Material.COOKIE, 1));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.DROWNING) {
			 e.setDroppedExp(0);
			 e.getDrops().clear();
			 e.getDrops().add(new ItemStack(Material.BREAD, 1));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.ENTITY_ATTACK) {
			 e.setDroppedExp(10);
			 e.getDrops().clear();
			 e.getDrops().add(new ItemStack(Material.APPLE, 1));
		 }
		 
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.ENTITY_EXPLOSION) {
			 e.setDroppedExp(0);
			 e.getDrops().clear();
			 e.getDrops().add(new ItemStack(Material.PORK, 1));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.FALL) {
			e.setDroppedExp(10);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.COOKED_BEEF, 1));
		}
		 
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.FIRE) {
			e.setDroppedExp(10);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.COOKED_CHICKEN, 1));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.FIRE_TICK) {
			e.setDroppedExp(10);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.COOKED_FISH, 2));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.LAVA) {
			e.setDroppedExp(10);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.COOKED_FISH, 64));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.LIGHTNING) {
			e.setDroppedExp(10);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.TNT, 1));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.MAGIC) {
			e.setDroppedExp(10);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.TNT, 1));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.POISON) {
			e.setDroppedExp(10);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.TNT, 1));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.PROJECTILE) {
			 e.setDroppedExp(10);
			 e.getDrops().clear();
			 e.getDrops().add(new ItemStack(Material.MELON, 3));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.STARVATION) {
			e.setDroppedExp(10);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.TNT, 1));
		}
		
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.SUFFOCATION) {
			e.setDroppedExp(10);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.TNT, 1));
		}
		 
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.SUICIDE) {
			e.setDroppedExp(0);
			e.getDrops().clear();
			e.getDrops().add(new ItemStack(Material.TNT, 1));
		}
		 
		if (e.getEntity().getLastDamageCause().getCause() == DamageCause.VOID) {
			e.setDroppedExp(0);
			e.getDrops().clear();
		}
		 
		if (e.getEntity() instanceof Player) {
			 Player player = (Player) e.getEntity();
			 war.clearTeams(player);
		 }
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void omgRespawnDolt(PlayerRespawnEvent e) {
		if (war.blu.size() == war.red.size()) {
			Random rand = new Random();
			int decider = rand.nextInt(2);
			war.assignPlayer(e.getPlayer(), decider);
		} else if (war.blu.size() < war.red.size()) {
			war.assignPlayer(e.getPlayer(), 0);
		} else if (war.red.size() < war.blu.size()) {
			war.assignPlayer(e.getPlayer(), 1);
		}
		if (war.teamName(e.getPlayer()) == 0) {
			double x = war.getConfig().getDouble("blu-spawn-x");
			double y = war.getConfig().getDouble("blu-spawn-y");
			double z = war.getConfig().getDouble("blu-spawn-z");
			float yaw = war.getConfig().getInt("blu-spawn-yaw");
			float pitch = war.getConfig().getInt("blu-spawn-pitch");
			e.setRespawnLocation(new Location(e.getPlayer().getWorld(), x, y, z, yaw, pitch));
		} else {
			double x = war.getConfig().getDouble("red-spawn-x");
			double y = war.getConfig().getDouble("red-spawn-y");
			double z = war.getConfig().getDouble("red-spawn-z");
			float yaw = war.getConfig().getInt("red-spawn-yaw");
			float pitch = war.getConfig().getInt("red-spawn-pitch");
			e.setRespawnLocation(new Location(e.getPlayer().getWorld(), x, y, z, yaw, pitch));
		}
	}
	
	@EventHandler
	public void spawnRight(PlayerJoinEvent e) {
		if (war.getConfig().getBoolean("has-started")) {
			double x = war.getConfig().getDouble("spec-spawn-x");
			double y = war.getConfig().getDouble("spec-spawn-y");
			double z = war.getConfig().getDouble("spec-spawn-z");
			float yaw = war.getConfig().getInt("spec-spawn-yaw");
			float pitch = war.getConfig().getInt("spec-spawn-pitch");
			e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), x, y, z, yaw, pitch));
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void omgBlueCreepersWtf(CreeperPowerEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void omgNO(EntityExplodeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void pyroBurning(EntityDamageEvent e) {
		if (e instanceof EntityDamageByEntityEvent) {
			if (((EntityDamageByEntityEvent) e).getDamager() instanceof Player) {
				EntityDamageByEntityEvent subevent = ((EntityDamageByEntityEvent) e);
				Player hi = (Player) subevent.getDamager();
				if (war.pyro.contains(hi.getName())) {
					e.getEntity().setFireTicks(40);
				}
				if (e.getEntity() instanceof Player) {
					Player defender = (Player) e.getEntity();
					if ((!war.blu.contains(defender.getName())) && (!war.red.contains(defender.getName()))) {
						e.setCancelled(true);
						e.getEntity().setFireTicks(0);
						hi.sendMessage(ChatColor.BLUE + "You can't hurt a spectator!");
					}
					if ((war.blu.contains(hi.getName())) && (war.blu.contains(defender.getName()))) {
						e.setCancelled(true);
						e.getEntity().setFireTicks(0);
						hi.sendMessage(ChatColor.BLUE + "You can't hurt your teammate " + defender.getName() + "!");
					} else if ((war.red.contains(hi.getName())) && (war.red.contains(defender.getName()))) {
						e.setCancelled(true);
						hi.sendMessage(ChatColor.BLUE + "You can't hurt your teammate " + defender.getName() + "!");
						e.getEntity().setFireTicks(0);
					}
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void lolTeamChat(PlayerChatEvent e) {
		if (e.getMessage().startsWith(".")) {
			war.teamMessage(war.teamName(e.getPlayer()), "(TEAM) <" + e.getPlayer().getDisplayName() + "> " + e.getMessage());
			e.setCancelled(true);
		}
	}
	
}
