package de.elia.api.events.achievement;

import de.elia.api.achievements.Achievements;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * This event is called if a player get an achievement
 * @author Elia
 * @since 4.0.0
 */
public class AchievementGiveEvent extends Event implements Cancellable {

  private static final HandlerList HANDLER_LIST = new HandlerList();
  private final Player player;
  private final Achievements achievement;
  private boolean cancelled = false;

  /**
   * Set the variables for the class
   * @param player Requires the event player
   * @param achievement Requires the event achievement
   */
  public AchievementGiveEvent(@NotNull Player player, @NotNull Achievements achievement){
    this.player = player;
    this.achievement = achievement;
  }

  /**
   * Gets the player from the event
   * @return The player who received the achievement
   */
  @NotNull
  public Player getPlayer(){
    return this.player;
  }

  /**
   * Gets the achievement of the player
   * @return The achievement that the player has received
   */
  @NotNull
  public Achievements getAchievement(){
    return this.achievement;
  }

  /**
   * Get the Handler list
   * @return a list of event Handlers
   */
  public static HandlerList getHandlerList(){
    return HANDLER_LIST;
  }

  /**
   * Get the Handler list
   * @return a list of event Handlers
   */
  @Override
  public @NotNull HandlerList getHandlers() {
    return HANDLER_LIST;
  }

  /**
   * Gets the cancellation state of this event. A cancelled event will not be executed in the server, but will still pass to other plugins
   * @return true if this event is cancelled
   */
  @Override
  public boolean isCancelled() {
    return cancelled;
  }

  /**
   * Sets the cancellation state of this event. A cancelled event will not
   * be executed in the server, but will still pass to other plugins.
   *
   * @param cancel true if you wish to cancel this event
   */
  @Override
  public void setCancelled(boolean cancel) {
    this.cancelled = cancel;
  }
}
