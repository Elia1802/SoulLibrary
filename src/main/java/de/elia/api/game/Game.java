package de.elia.api.game;

import de.elia.api.entities.BossEntity;
import org.bukkit.boss.BossBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public interface Game {

  ArrayList<UUID> OLD_GAME_PLAYERS = new ArrayList<>();
  ArrayList<Game> ALL_ACTIVE_GAMES = new ArrayList<>();
  Map<BossEntity, BossBar> bossBossBar = new HashMap<>();

  void kill(String reason, boolean isRestart);

}
