
package pl.eldzi.realisticexplotion;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import pl.eldzi.realisticexplotion.events.ExplotionEvent;

public class Main extends JavaPlugin {

	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new ExplotionEvent(), this);
	}

	public void onDisable() {
	}
}
