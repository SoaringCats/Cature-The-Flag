package ch.jamiete.war.runnables;

import ch.jamiete.war.War;

public class ArrowRemoval implements Runnable {
	private War war;
	
	public ArrowRemoval(War war) {
		this.war = war;
	}

	@Override
	public void run() {
		for (int i = 0; i < war.arrows.size(); i++) {
			war.arrows.get(i).remove();
		}
	}

}
