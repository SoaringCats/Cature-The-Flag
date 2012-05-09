package tk.nekotech.war.helpers;

import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import tk.nekotech.war.War;

public class Mob {
	@SuppressWarnings("unused")
	private War war;
	
	public Mob (War war) {
		this.war = war;
	}

	public boolean isAllowed(EntityType type) {
		if (type.equals(EntityType.SPIDER))
			return true;
		if (type.equals(EntityType.CAVE_SPIDER))
			return true;
		if (type.equals(EntityType.SKELETON))
			return true;
		if (type.equals(EntityType.CREEPER))
			return true;
		if (type.equals(EntityType.PIG_ZOMBIE))
			return true;
		if (type.equals(EntityType.ZOMBIE))
			return true;
		return false;
		
	}
	
	public EntityType randoMob() {
		Random rand = new Random();
		int mobID = rand.nextInt(9);
		if (mobID == 0)
			return EntityType.SPIDER;
		if (mobID == 1)
			return EntityType.CAVE_SPIDER;
		if (mobID == 2)
			return EntityType.SKELETON;
		if (mobID == 3)
			return EntityType.CREEPER;
		if (mobID == 4)
			return EntityType.PIG_ZOMBIE;
		if (mobID == 5)
			return EntityType.ZOMBIE;
		if (mobID == 6)
			return EntityType.ZOMBIE;
		if (mobID == 7)
			return EntityType.ZOMBIE;
		if (mobID == 8)
			return EntityType.CAVE_SPIDER;
		return null;
	}
	
	public void killMob(Entity entity) {
		if (entity != null) {
			entity.getWorld().strikeLightningEffect(entity.getLocation());
			entity.remove();
		}
	}
	
}
