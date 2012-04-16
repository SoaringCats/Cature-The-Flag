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
	
}
