package de.elia.api.thezepserapi.datatypes;

import de.elia.api.thezepserapi.enums.ItemRegionType;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class ItemRegion {
  private Location center;
  private double radius;
  private World world;
  private Player owner;
  private ItemRegionType type;
  private boolean pvp;

  public ItemRegion(Location center, double radius, ItemRegionType type, Player owner, boolean pvp) {
    this.pvp = pvp;
    this.world = center.getWorld();
    this.center = center;
    this.radius = radius;
    this.owner = owner;
    this.type = type;
  }
  public World getWorld(){
    return world;
  }
  public Location getCenter() {
    return center;
  }
  public double getRadius() {
    return radius;
  }
  public Player getOwner() {
    return owner;
  }
  public ItemRegionType getType() {
    return type;
  }
  public boolean getPvP() {
    return pvp;
  }

  public void clear() {
    this.world=null;
    this.center=null;
    this.radius=0;
    this.owner=null;
    this.type= ItemRegionType.NORMAL;
    this.pvp=false;
  }
}
