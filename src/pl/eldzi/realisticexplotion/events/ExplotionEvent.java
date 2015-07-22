
package pl.eldzi.realisticexplotion.events;

import java.util.List;

import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import pl.eldzi.realisticexplotion.utils.LocationUtil;
import pl.eldzi.realisticexplotion.utils.RandomUtil;
import pl.eldzi.realisticexplotion.utils.VectorUtil;

public class ExplotionEvent implements Listener {

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
	public void onExplotion(EntityExplodeEvent e) {
		List<Block> blocks = e.blockList();
		List<Entity> nearbyEntities = LocationUtil.getNearbyEntities(e
		        .getEntity().getLocation(), 5);

		for (Block b : blocks) {
			if (b.getType().equals(Material.TNT)) {
				TNTPrimed tnt = (TNTPrimed) b.getWorld().spawnEntity(
				        b.getLocation(), EntityType.PRIMED_TNT);
				tnt.setFuseTicks(1);
				continue;
			}
			FallingBlock fb = b.getWorld().spawnFallingBlock(b.getLocation(),
			        b.getType(), b.getData());
			while (fb.isOnGround()) {
				b.setType(Material.AIR);
				b.getWorld().playEffect(b.getLocation(), Effect.ITEM_BREAK,
				        b.getTypeId());
				fb.remove();
			}
			boolean drop = RandomUtil.getChance(50);
			fb.setDropItem(drop);
			VectorUtil.pushBlockFromTNT(e.getEntity(), fb, 1.0);

		}

		for (Entity ent : nearbyEntities) {
			if (ent.getType().equals(EntityType.DROPPED_ITEM)
			        || ent.getType().equals(EntityType.FALLING_BLOCK)) {
				continue;
			}
			VectorUtil.pushBlockFromTNT(e.getEntity(), ent, 4.0);
			ent.playEffect(EntityEffect.HURT);
		}
		List<Player> nearbyPlayer = LocationUtil.getPlayersInRadius(e
		        .getEntity().getLocation(), 5);

		for (Player pl : nearbyPlayer) {
			VectorUtil.pushBlockFromTNT(e.getEntity(), pl, 4.0);

			pl.playSound(pl.getLocation(), Sound.FIREWORK_BLAST, 3.0f, 3.0f);
		}
	}
}
