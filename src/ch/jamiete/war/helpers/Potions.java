package ch.jamiete.war.helpers;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ch.jamiete.war.War;

public class Potions {
    @SuppressWarnings("unused")
    private final War war;

    public Potions(final War war) {
        this.war = war;
    }

    public void addEffect(final Player player, final PotionEffectType effect, final int time, final int level) {
        player.addPotionEffect(new PotionEffect(effect, time, level));
    }

    public boolean badPotion(final PotionEffect effect) {
        final PotionEffectType type = effect.getType();
        if (type == PotionEffectType.BLINDNESS || type == PotionEffectType.CONFUSION || type == PotionEffectType.DAMAGE_RESISTANCE || type == PotionEffectType.HARM || type == PotionEffectType.HUNGER || type == PotionEffectType.POISON || type == PotionEffectType.SLOW || type == PotionEffectType.SLOW_DIGGING || type == PotionEffectType.WEAKNESS) {
            return true;
        }
        return false;
    }

    public void clearPotions(final Player player) {
        for (final PotionEffect potion : player.getActivePotionEffects()) {
            player.removePotionEffect(potion.getType());
        }
    }

}
