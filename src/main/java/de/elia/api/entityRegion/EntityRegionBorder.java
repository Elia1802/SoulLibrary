package de.elia.api.entityRegion;

import org.bukkit.Location;
import org.bukkit.Particle;

import org.jetbrains.annotations.NotNull;

public class EntityRegionBorder {

  public EntityRegionBorder(double radius, @NotNull Location center, @NotNull Particle particle) {
    for (int degree = 0; degree < 360; degree ++) {
      degree++;
      double radians = Math.toRadians(degree);
      double x = radius * Math.cos(radians);
      double z = radius * Math.sin(radians);
      center.add(x, 0, z);
      center.getWorld().spawnParticle(particle, center, 1);
      center.subtract(x, 0, z);
    }
  }
}
