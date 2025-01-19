package de.elia.api.thezepserapi;

import de.elia.api.Main;
import de.elia.api.messages.prefix.PrefixClass;
import de.elia.api.thezepserapi.datatypes.ItemRegion;
import de.elia.api.thezepserapi.enums.ItemRegionType;
import de.elia.api.thezepserapi.components.ComplexItem;
import de.elia.api.thezepserapi.components.ComplexItemKeyConstructor;
import de.elia.api.thezepserapi.components.SpawnCircle;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Collection;
import java.util.List;


public interface TheZepserAPI {

  Component Prefix= PrefixClass.prefix();
  static void indicator(int number) {
    Main.getSoulLibrary().logger().logInfo("Indicator: " + number);
  }



  class Item_Region {

    public static Collection<LivingEntity> contains(ItemRegion itemRegion) {
      return itemRegion.getCenter().getNearbyLivingEntities(itemRegion.getRadius());
    }

    public static boolean containsObject(LivingEntity entity, ItemRegion itemRegion) {
      if (itemRegion == null) {
        return false;
      }
      else if (entity.getLocation().distance(itemRegion.getCenter()) < itemRegion.getRadius()) {
        return true;
      }
      else {
        return false;
      }
    }

    public static void delete(ItemRegion itemRegion) {
      itemRegion.clear();
    }

    public static void spawnCircle(ItemRegion itemRegion, Particle particle) {
      new SpawnCircle(itemRegion, particle);
    }

    public static ItemRegion create(Location center, double radius, ItemRegionType type, Player owner, boolean pvp) {
      return new ItemRegion(center, radius, type, owner, pvp);
    }
  }



  class Item {
    public static String createKey(Complex item) {
      return new ComplexItemKeyConstructor().createKey(item);
    }

    public static boolean hasKey(ItemStack itemStack, String key, Plugin plugin) {
      if (itemStack == null)return false;
      return new ComplexItemKeyConstructor().hasKey(itemStack, key, plugin);
    }
    public static String getKey(ItemStack itemStack) {
      return new ComplexItemKeyConstructor().getKey(itemStack);
    }
    public static ComplexItem create(Material material, Component displayName, List<Component> lore) {
      return new ComplexItem(material, displayName, lore);
    }
  }
}














