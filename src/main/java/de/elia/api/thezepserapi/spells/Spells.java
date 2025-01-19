package de.elia.api.thezepserapi.spells;

import de.elia.api.thezepserapi.spells.spells.elia.EXPLOSION_ATTACK;
import de.elia.api.thezepserapi.spells.spells.fire.FIRE_ATTACK;
import de.elia.api.thezepserapi.spells.spells.fire.FIRE_DEFENSE;
import de.elia.api.thezepserapi.spells.spells.fire.FIRE_SUPER;
import de.elia.api.thezepserapi.spells.spells.gravitation.GRAVITATION_ATTACK;
import de.elia.api.thezepserapi.spells.spells.gravitation.GRAVITATION_DEFENSE;
import de.elia.api.thezepserapi.spells.spells.gravitation.GRAVITATION_SUPER;
import de.elia.api.thezepserapi.spells.spells.weather.WEATHER_ATTACK;
import de.elia.api.thezepserapi.spells.spells.weather.WEATHER_DEFENSE;
import de.elia.api.thezepserapi.spells.spells.weather.WEATHER_SUPER;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface Spells {
  static void FIRE_ATTACK(Player player, boolean pvp, Plugin plugin) {
    new FIRE_ATTACK().spawn(player, pvp, plugin);
  }
  static void FIRE_DEFENSE(Player player, boolean pvp, Plugin plugin) {
    new FIRE_DEFENSE().spawn(player, pvp, plugin);
  }
  static void FIRE_SUPER(Player player, boolean pvp, Plugin plugin) {
    new FIRE_SUPER().spawn(player, pvp, plugin);
  }
  static void WEATHER_ATTACK(Player player, boolean pvp, Plugin plugin) {
    new WEATHER_ATTACK().spawn(player, pvp, plugin);
  }
  static void WEATHER_DEFENSE(Player player, boolean pvp, Plugin plugin) {
    new WEATHER_DEFENSE().spawn(player, pvp, plugin);
  }
  static void WEATHER_SUPER(Player player, Plugin plugin) {
    new WEATHER_SUPER().spawn(player, plugin);
  }
  static void GRAVITATION_ATTACK(Player player, boolean pvp, Plugin plugin) {
    new GRAVITATION_ATTACK().spawn(player, pvp, plugin);
  }
  static void GRAVITATION_DEFENSE(Player player, boolean pvp, Plugin plugin) {
    new GRAVITATION_DEFENSE(player, pvp, plugin);
  }
  static void GRAVITATION_SUPER(Player player, boolean pvp, Plugin plugin) {
    new GRAVITATION_SUPER(plugin).spawn(player, pvp);
  }
  static void EXPLOSION_ATTACK(@NotNull Player player, @NotNull Plugin plugin) {
    new EXPLOSION_ATTACK().spawn(player, plugin);
  }
}
