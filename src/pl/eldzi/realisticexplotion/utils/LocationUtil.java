
package pl.eldzi.realisticexplotion.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class LocationUtil {

	public static List<Player> getPlayersInRadius(Location location, int radius) {
		Player[] players = Bukkit.getOnlinePlayers();
		List<Player> plys = new ArrayList<>();
		for (int i = 0; i < players.length; i++) {
			if (players[i].getLocation().distance(location) < radius) {
				plys.add(players[i]);
			}
		}
		return plys;
	}

	public static List<Entity> getNearbyEntities(Location l, int radius) {
		List<Entity> entities = l.getWorld().getEntities();
		List<Entity> plys = new ArrayList<>();
		for (int k = 0; k < entities.size(); k++) {
			if (entities.get(k).getLocation().distance(l) < radius) {
				plys.add(entities.get(k));
			}
		}
		return plys;
	}

}
