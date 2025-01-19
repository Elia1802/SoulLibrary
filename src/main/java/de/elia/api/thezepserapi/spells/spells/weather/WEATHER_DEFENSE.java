package de.elia.api.thezepserapi.spells.spells.weather;

import de.elia.api.thezepserapi.TheZepserAPI;
import de.elia.api.thezepserapi.datatypes.ItemRegion;
import de.elia.api.thezepserapi.enums.ItemRegionType;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;

public class WEATHER_DEFENSE implements Listener {
  private int COUNT;
  private static final Collection<ItemRegion> ITEM_REGIONS = new ArrayList<>();
  private ItemRegion ItemREGION;

  public void spawn(Player player, boolean pvp, Plugin plugin) {
    Location location = player.getLocation();
    ItemRegion itemRegion = TheZepserAPI.Item_Region.create(player.getLocation(), 6, ItemRegionType.WEATHER_DEFENSE, player, pvp);
    ITEM_REGIONS.add(itemRegion);
    Collection<Player> players = player.getLocation().getNearbyPlayers(14);
    for (Player player1 : players) {
      player1.playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 0.7f, 0.8f);
      player1.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.2f, 0.3f);
    }
    COUNT = 0;
    new BukkitRunnable() {
      public void run() {
        if (COUNT < 17) {
          COUNT++;
          for (int degree = 0; degree < 360; degree++) {
            degree++;
            double radians = Math.toRadians(degree);
            double x = 6 * Math.cos(radians);
            double z = 6 * Math.sin(radians);
            location.add(x, 0, z);
            location.getWorld().spawnParticle(Particle.DRIPPING_WATER, location, 1);
            location.subtract(x, 0, z);
          }
        } else {
          ITEM_REGIONS.remove(itemRegion);
          cancel();
        }
      }
    }.runTaskTimer(plugin, 5, 20);
  }
  @EventHandler
  public void onEvent(EntityDamageEvent event) {
    if (event.getEntity() instanceof Player player) {
      if (ITEM_REGIONS != null) {
        for (ItemRegion itemRegion : ITEM_REGIONS) {
          if (itemRegion.getPvP()) {
            if (event.getEntity() == itemRegion.getOwner()) {
              player.getLocation().getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, player.getLocation(), 4);
              event.setDamage(event.getDamage() / 2);
            }
          }
          else {
            if (TheZepserAPI.Item_Region.containsObject(player, itemRegion)) {
              player.getLocation().getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, player.getLocation(), 4);
              event.setDamage(event.getDamage() / 2);
            }
          }
        }
      }
    }

  }
}
