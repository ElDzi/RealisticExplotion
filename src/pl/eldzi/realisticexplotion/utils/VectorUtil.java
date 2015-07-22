
package pl.eldzi.realisticexplotion.utils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.util.Vector;

public class VectorUtil {
	public static void pushBlockFromTNT(Entity tnt, FallingBlock fBlock,
	        double speed) {
		Vector vector = tnt.getLocation().toVector()
		        .subtract(fBlock.getLocation().toVector()).normalize();
		fBlock.setVelocity(vector.multiply(speed));
	}

	public static void pushBlockFromTNT(Entity tnt, Entity ent, double speed) {
		Vector vector = tnt.getLocation().toVector()
		        .subtract(ent.getLocation().toVector()).normalize();
		ent.setVelocity(vector.multiply(speed));
	}
}
