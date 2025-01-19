package de.elia.api.thezepserapi.components;

import de.elia.api.thezepserapi.datatypes.ItemRegion;

import org.bukkit.Particle;

public class SpawnCircle {

  public SpawnCircle(ItemRegion itemRegion, Particle particle) {
    for (int degree = 0; degree < 360; degree++) {
      degree++;
      double radians = Math.toRadians(degree);
      double x = itemRegion.getRadius() * Math.cos(radians);
      double z = itemRegion.getRadius() * Math.sin(radians);
      itemRegion.getCenter().add(x, 0, z);
      itemRegion.getCenter().getWorld().spawnParticle(particle, itemRegion.getCenter(), 1);
      itemRegion.getCenter().subtract(x, 0, z);
    }
  }
}
