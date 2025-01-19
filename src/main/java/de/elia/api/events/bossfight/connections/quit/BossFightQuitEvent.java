package de.elia.api.events.bossfight.connections.quit;

import de.elia.api.Main;
import de.elia.api.messages.builder.MessageBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;

public class BossFightQuitEvent extends Event {

  private final MessageBuilder messageBuilder = new MessageBuilder();
  private static final HandlerList HANDLER_LIST = new HandlerList();
  private Set<Player> players = new HashSet<>();
  private final Player quitedPlayer;

  public BossFightQuitEvent(@NotNull Set<Player> players, Player quitedPlayer){
    this.players = players;
    this.quitedPlayer = quitedPlayer;
  }

  public void sendQuitMessage(@NotNull Component message, Component messageForQuitedPlayer) throws NullPointerException{
    this.players.forEach(player -> messageBuilder.message(player, message));
    if (quitedPlayer == null) {
      Main.getSoulLibrary().logger().logError("Message can't send because quitedPlayer is null!");
      return;
    }
    if (messageForQuitedPlayer == null) {
      Main.getSoulLibrary().logger().logError("Message can't send because messageForQuitedPlayer is null!");
      return;
    }
    messageBuilder.message(quitedPlayer, messageForQuitedPlayer);
  }

  public void sendQuitMessage(@NotNull Component message){
    this.players.forEach(player -> MessageBuilder.message(player, message));
  }

  @Nullable
  public Player getQuitedPlayer() {
    return quitedPlayer;
  }

  @Nullable
  public Player getPlayer(String name){
    if (this.players.contains(Bukkit.getPlayer(name))) {
      return Bukkit.getPlayer(name);
    }else return null;
  }

  @Nullable
  public Player getPlayer(UUID uuid){
    if (this.players.contains(Bukkit.getPlayer(uuid))) {
      return Bukkit.getPlayer(uuid);
    }else return null;
  }

  @NotNull
  public Set<Player> getPlayers() {
    return players;
  }

  @NotNull
  public static HandlerList getHandlerList() {
    return HANDLER_LIST;
  }

  @Override
  public @NotNull HandlerList getHandlers() {
    return HANDLER_LIST;
  }
}
