package tk.nekotech.war.runnables;

import tk.nekotech.war.War;

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
