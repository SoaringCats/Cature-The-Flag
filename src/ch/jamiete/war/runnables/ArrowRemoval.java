package ch.jamiete.war.runnables;

import ch.jamiete.war.War;

public class ArrowRemoval implements Runnable {
    private final War war;

    public ArrowRemoval(final War war) {
        this.war = war;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.war.arrows.size(); i++) {
            this.war.arrows.get(i).remove();
        }
    }

}
