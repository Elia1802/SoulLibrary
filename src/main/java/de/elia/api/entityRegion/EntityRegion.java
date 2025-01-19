package de.elia.api.entityRegion;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class EntityRegion {

  private Location center;
  private double radius;
  private Entity regionOwner;
  private boolean pvp;
  private boolean pvpWithProjectile;

  public EntityRegion(@NotNull Location center, double radius, @NotNull Entity regionOwner, boolean pvp, boolean pvpWithProjectile){
    this.center = center;
    this.radius = radius;
    this.regionOwner = regionOwner;
    this.pvp = pvp;
    this.pvpWithProjectile = pvpWithProjectile;
  }

  @NotNull
  public Location getCenter() {
    return this.center;
  }

  public double getRadius() {
    return this.radius;
  }

  @NotNull
  public Entity getRegionOwner() {
    return this.regionOwner;
  }

  public boolean getPvp(){
    return this.pvp;
  }

  public boolean getPvpWithProjectile(){
    return this.pvpWithProjectile;
  }

  public void delete(){
    center = null;
    radius = 0;
    regionOwner = null;
    pvp = true;
    pvpWithProjectile = true;
  }
}
