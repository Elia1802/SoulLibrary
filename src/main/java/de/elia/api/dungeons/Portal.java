package de.elia.api.dungeons;

public interface Portal {

  Portal createPortal(String name, String world, double x, double y, double z, float yaw, float pitch);

  void teleport(Dungeon dungeon);

  void removePortal(Portal portal);

  String portalID();

}
