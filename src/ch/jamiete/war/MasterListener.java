package ch.jamiete.war;

import org.bukkit.event.Listener;

public class MasterListener implements Listener {
    protected final War war;

    public MasterListener(final War war) {
        this.war = war;
        war.getServer().getPluginManager().registerEvents(this, war);
    }

}
