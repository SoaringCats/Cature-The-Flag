package ch.jamiete.war.runnables;

import java.util.Date;
import org.bukkit.entity.Player;
import ch.jamiete.war.War;

public class AFKCheck implements Runnable {
    private final War war;

    public AFKCheck(final War war) {
        this.war = war;
    }

    @Override
    public void run() {
        for (final Player player : this.war.getServer().getOnlinePlayers()) {
            final long seconds = new Date().getTime() - this.war.afk.get(player) / 1000;
            if (seconds <= 300) {
                player.kickPlayer("You were idling. Please rejoin :)");
            }
        }
    }

}
