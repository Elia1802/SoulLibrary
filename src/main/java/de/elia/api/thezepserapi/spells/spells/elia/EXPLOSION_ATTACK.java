package de.elia.api.thezepserapi.spells.spells.elia;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class EXPLOSION_ATTACK {

  private int count;
  private Location position;
  private Vector direction;

  public void spawn(@NotNull Player player, @NotNull Plugin plugin) {
    Collection<Player> players = player.getLocation().getNearbyPlayers(15);
    players.forEach(playerInCollection -> playerInCollection.playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, 0.7f, 0.8f));
    new BukkitRunnable() {
      @Override
      public void run() {
        count = 0;
        var playerLocation = player.getLocation();
        World world = player.getWorld();
        double x = playerLocation.x();
        double y = playerLocation.y();
        double z = playerLocation.z();
        float yaw = playerLocation.getYaw();
        float pitch = playerLocation.getPitch();
        position = new Location(world, x, y, z, yaw, pitch);
        direction = position.getDirection();
        players.forEach(playerInCollection -> player.getWorld().spawnParticle(Particle.ASH, player.getLocation(), 20));
        new BukkitRunnable() {
          @Override
          public void run() {
            if (count < 13) {
              position.add(direction.multiply(1.0));
              position.getWorld().spawnParticle(Particle.ASH, position, 20);
              Collection<LivingEntity> livingEntities = position.getNearbyLivingEntities(1.0);
              livingEntities.forEach(entity -> {
                var effect = PotionEffectType.FIRE_RESISTANCE;
                if (entity.hasPotionEffect(effect)) {
                  if (entity != player) {
                    entity.removePotionEffect(effect);
                    entity.getLocation().getWorld().createExplosion(entity.getLocation(), 3, true);
                  }
                }else {
                  if (entity != player) {
                    entity.getLocation().getWorld().createExplosion(entity.getLocation(), 3, true);
                  }
                }
              });
              count++;
            }else this.cancel();
          }
        }.runTaskTimer(plugin, 0L, 0L);
      }
    }.runTaskLater(plugin, 4L);
  }

}
