package de.elia.api.dungeons;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Map;

public interface Dungeon {

  Location spawnLocation();

  void load();

  void unload();

  Map<Location, Entity> getEntities();

  void spawnEntity(Entity entity, Location location);

  void spawnEntities(Map<Location, Entity> entities);

  void removeEntity(Entity entity);

  void teleportPlayer(Player player, Location location);

  Location worldSpawnLocation();

  String acceptedPortalID();

  String dungeonID();
}
