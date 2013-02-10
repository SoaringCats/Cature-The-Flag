package ch.jamiete.war.helpers;

import org.bukkit.ChatColor;

public enum Team {

    BLU(ChatColor.BLUE), RED(ChatColor.RED), SPECTATOR(ChatColor.GRAY);

    private final ChatColor color;

    private Team(final ChatColor color) {
        this.color = color;
    }

    public ChatColor getColor() {
        return this.color;
    }

    public String getColored() {
        return this.color + this.name();
    }

}
