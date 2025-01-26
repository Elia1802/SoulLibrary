package de.elia.api.game.party.connections.join;

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

public class BossFightJoinEvent extends Event {

  private final MessageBuilder messageBuilder = new MessageBuilder();
  private static final HandlerList HANDLER_LIST = new HandlerList();
  private Set<Player> players = new HashSet<>();
  private final Player joinedPlayer;

  public BossFightJoinEvent(@NotNull Set<Player> players, Player joinedPlayer){
    this.players = players;
    this.joinedPlayer = joinedPlayer;
  }

  public void sendJoinMessage(@NotNull Component message){
    this.players.forEach(player -> messageBuilder.message(player, message));
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

  @Nullable
  public Player getJoinedPlayer() {
    return joinedPlayer;
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
