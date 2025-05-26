package de.elia.api.events.portal;

import de.elia.api.dungeons.Dungeon;
import de.elia.api.dungeons.Portal;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import org.jetbrains.annotations.NotNull;

public class PlayerInteractPortalEvent extends Event implements Cancellable {

  private final static HandlerList HANDLER_LIST = new HandlerList();
  private boolean cancelled = false;
  private final Player player;
  private final Dungeon dungeon;

  public PlayerInteractPortalEvent(Player player, Dungeon dungeon) {
    this.player = player;
    this.dungeon = dungeon;
  }

  public void teleportPlayer(Portal usedPortal) {
    if (usedPortal.portalID().equals(this.dungeon.acceptedPortalID())) {
      this.dungeon.teleportPlayer(this.player, this.dungeon.spawnLocation());
    }
  }

  @NotNull
  public Player getPlayer() {
    return player;
  }

  @NotNull
  public Dungeon getDungeon() {
    return dungeon;
  }

  @Override
  public boolean isCancelled() {
    return cancelled;
  }

  @Override
  public void setCancelled(boolean cancel) {
    this.cancelled = cancel;
  }

  @NotNull
  public static HandlerList getHandlerList() {
    return HANDLER_LIST;
  }

  @Override
  @NotNull
  public HandlerList getHandlers() {
    return HANDLER_LIST;
  }
}
