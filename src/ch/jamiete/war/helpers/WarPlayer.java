package ch.jamiete.war.helpers;

import java.util.ArrayList;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class WarPlayer {
    private final Player player;
    private final ArrayList<PotionEffect> effects;
    private Team team;
    private PlayerClass clazz;

    public WarPlayer(final Player player) {
        this.player = player;
        this.effects = new ArrayList<PotionEffect>();
        this.team = Team.SPECTATOR;
    }

    public void addEffect(final PotionEffect effect) {
        this.effects.add(effect);
    }

    public ArrayList<PotionEffect> getEffects() {
        return new ArrayList<PotionEffect>(this.effects);
    }

    public String getName() {
        return this.player.getName();
    }

    public Player getPlayer() {
        return this.player;
    }

    public PlayerClass getPlayerClass() {
        return this.clazz;
    }

    public Team getTeam() {
        return this.team;
    }

    public boolean hasPermission(final String permission) {
        return this.player.hasPermission(permission);
    }

    public boolean isOnTeam() {
        return this.team != Team.SPECTATOR;
    }

    public void sendMessage(final String message) {
        this.player.sendMessage(WarValues.MESSAGE_PREFIX + message);
    }

    public void setPlayerClass(final PlayerClass clazz) {
        this.clazz = clazz;
    }

    public void setTeam(final Team team) {
        this.team = team;
    }

}
