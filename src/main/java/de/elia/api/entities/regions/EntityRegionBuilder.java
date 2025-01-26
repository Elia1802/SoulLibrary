package de.elia.api.entityRegion;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class EntityRegionBuilder {

  @NotNull
  public static Collection<Entity> getAllEntitiesInRegion(@Nullable Class<? extends Entity> classOfTheEntity, @NotNull EntityRegion region){
    return region.getCenter().getNearbyEntitiesByType(classOfTheEntity, region.getRadius());
  }

  public static boolean containsEntity(@NotNull Entity entity, @NotNull Location center, double radius){
    if (entity.getLocation().distance(center) < radius) {
      return true;
    }else return false;
  }

  public static void delete(@NotNull EntityRegion region) {
    region.delete();
  }

  @NotNull
  public static EntityRegion create(@NotNull Location center, double radius, @NotNull Entity regionOwner, boolean pvp, boolean pvpWithProjectile){
    return new EntityRegion(center, radius, regionOwner, pvp, pvpWithProjectile);
  }

  public static void createEntityRegionBorder(double radius, @NotNull Location center, Particle particle){
    new EntityRegionBorder(radius, center, particle);
  }

}
