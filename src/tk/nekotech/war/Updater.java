package tk.nekotech.war;

public class Updater {
	private War war;
	
	public Updater(War war) {
		this.war = war;
	}
	
	public void updateConfig() {
		String version = war.getConfig().getString("version");
		// Nothing to update! :D
	}
	
}
