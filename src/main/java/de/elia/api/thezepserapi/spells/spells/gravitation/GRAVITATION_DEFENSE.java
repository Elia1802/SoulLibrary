package de.elia.api.thezepserapi.spells.spells.gravitation;

import de.elia.api.thezepserapi.TheZepserAPI;
import de.elia.api.thezepserapi.datatypes.ItemRegion;
import de.elia.api.thezepserapi.enums.ItemRegionType;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;


public class GRAVITATION_DEFENSE {

  public GRAVITATION_DEFENSE(@NotNull Player player, boolean pvp, Plugin plugin) {
    Collection<Player> players = player.getLocation().getNearbyPlayers(14);
    for (Player player1 : players) {
      player1.playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 0.7f, 0.8f);
      player1.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 0.6f, 0.1f);
    }
    ItemRegion itemRegion = new ItemRegion(player.getLocation(), 200, ItemRegionType.GRAVITATION_DEFENSE, player, pvp);
    TheZepserAPI.Item_Region.spawnCircle(itemRegion, Particle.DRIPPING_OBSIDIAN_TEAR);
    for (LivingEntity entity : TheZepserAPI.Item_Region.contains(itemRegion)) {
      if (!(player == itemRegion.getOwner())) {
        if (pvp) {
          player.playSound(entity.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 0.7f, 0.2f);
          entity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20*4, 1, false, false, false));
          entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 65, 1, false, false, false));
          new BukkitRunnable() {
            public void run() {
              entity.damage(15.0);
            }
          }.runTaskLater(plugin, 80);
        }else {
          player.playSound(entity.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 0.7f, 0.2f);
        }
      } else {
        player.playSound(entity.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 0.7f, 0.2f);
      }
    }
  }
}
