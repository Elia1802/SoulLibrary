package de.elia.api.thezepserapi.components;

import de.elia.api.thezepserapi.Complex;
import de.elia.api.thezepserapi.TheZepserAPI;
import de.elia.api.thezepserapi.enums.Level;
import de.elia.api.thezepserapi.enums.LevelType;
import de.elia.api.thezepserapi.enums.Type;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class ComplexItem implements Cloneable {
  public static final HashMap<Complex, ComplexItem> SAVED = new HashMap<>();
  private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
  private final ItemStack ITEM;
  private Complex COMPLEX = Complex.NONE;
  public ComplexItem(Material material,  Component DisplayName, List<Component> lore) {
    ItemStack itemStack = new ItemStack(material);
    ItemMeta meta = itemStack.getItemMeta();
    meta.displayName(DisplayName);
    meta.lore(lore);
    itemStack.setItemMeta(meta);
    this.ITEM = itemStack;
  }
  public ItemStack getItem() {
    return this.ITEM;
  }

  public ComplexItem save() {
    SAVED.put(this.COMPLEX, this);
    return this;
  }
  public ComplexItem setCustomModelData(int data) {
    ItemMeta meta = this.ITEM.getItemMeta();
    meta.setCustomModelData(data);
    this.ITEM.setItemMeta(meta);
    return this;
  }

  public ComplexItem setSaturationInfo(double amount) {
    ItemMeta meta = this.ITEM.getItemMeta();
    List<Component> list = meta.lore();
    if (list == null) {
      list = new ArrayList<>();
    }
    List<Component> cash = new ArrayList<>();
    Component component = MINI_MESSAGE.deserialize("<gray>Hunger</gray> <dark_gray>[</dark_gray> <aqua>").append(MINI_MESSAGE.deserialize(String.valueOf(amount))).append(MINI_MESSAGE.deserialize("</aqua> <dark_gray>]</dark_gray>"));
    cash.add(component);
    cash.addAll(list);
    meta.lore(cash);
    this.ITEM.setItemMeta(meta);
    return this;
  }
  public ComplexItem setType(Type type) {
    ItemMeta meta = this.ITEM.getItemMeta();
    List<Component> list = meta.lore();
    if (list == null) {
      list = new ArrayList<>();
    }
    if (type.equals(Type.ARMOR)) {
      Component component = MINI_MESSAGE.deserialize("<gray>Typ:</gray> <aqua>Rüstung</aqua>");
      list.add(component);
    }
    else if (type.equals(Type.OTHER)) {
      Component component = MINI_MESSAGE.deserialize("<gray>Typ:</gray> <aqua>Weiteres</aqua>");
      list.add(component);
    }
    else if (type.equals(Type.MAGIC)) {
      Component component = MINI_MESSAGE.deserialize("<gray>Typ:</gray> <aqua>Magie</aqua>");
      list.add(component);
    }
    else if (type.equals(Type.TOOL)) {
      Component component = MINI_MESSAGE.deserialize("<gray>Typ:</gray> <aqua>Werkzeug</aqua>");
      list.add(component);
    }
    else if (type.equals(Type.WEAPON)) {
      Component component = MINI_MESSAGE.deserialize("<gray>Typ:</gray> <aqua>Waffe</aqua>");
      list.add(component);
    }
    else if (type.equals(Type.FOOD)) {
      Component component = MINI_MESSAGE.deserialize("<gray>Typ:</gray> <aqua>Nahrung</aqua>");
      list.add(component);
    }
    else if (type.equals(Type.MATERIAL)) {
      Component component = MINI_MESSAGE.deserialize("<gray>Typ:</gray> <aqua>Material</aqua>");
      list.add(component);
    }
    else if (type.equals(Type.CURRENCY)) {
      Component component = MINI_MESSAGE.deserialize("<gray>Typ:</gray> <aqua>Währung</aqua>");
      list.add(component);
    }
    else if (type.equals(Type.DROPS)) {
      Component component = MINI_MESSAGE.deserialize("<gray>Typ:</gray> <aqua>Drops</aqua>");
      list.add(component);
    }
    else if (type.equals(Type.NONE)) {
      Component component = MINI_MESSAGE.deserialize("<gray>Typ:</gray> <aqua>NONE</aqua>");
      list.add(component);
    }
    else if (type.equals(Type.SPAWN_EGG)) {
      Component component = MINI_MESSAGE.deserialize("<gray>Typ:</gray> <aqua>Spawn Egg</aqua>");
      list.add(component);
    }
    meta.lore(list);
    this.ITEM.setItemMeta(meta);
    return this;
  }

  public ComplexItem setLevel(Level level, LevelType levelType) {
    ItemMeta meta = this.ITEM.getItemMeta();
    List<Component> list = meta.lore();
    if (list == null) {
      list = new ArrayList<>();
    }
    List<Component> cash = new ArrayList<>();
    if (levelType == LevelType.LEVEL) {
      Component component = MINI_MESSAGE.deserialize("<gray>Stufe:</gray> <aqua>").append(MINI_MESSAGE.deserialize(String.valueOf(level))).append(MINI_MESSAGE.deserialize("</aqua>"));
      cash.add(component);
    }
    else if (levelType == LevelType.PROTECTION) {
      Component component = MINI_MESSAGE.deserialize("<gray>Stufe:</gray> <aqua>").append(MINI_MESSAGE.deserialize(String.valueOf(level))).append(MINI_MESSAGE.deserialize("</aqua>"));
      cash.add(component);
    }
    else if (levelType == LevelType.STRENGTH) {
      Component component = MINI_MESSAGE.deserialize("<gray>Stufe:</gray> <aqua>").append(MINI_MESSAGE.deserialize(String.valueOf(level))).append(MINI_MESSAGE.deserialize("</aqua>"));
      cash.add(component);
    }
    cash.addAll(list);
    meta.lore(cash);
    this.ITEM.setItemMeta(meta);
    return this;
  }
  public ComplexItem setUpgraded() {
    ItemMeta meta = this.ITEM.getItemMeta();
    Component displayName = MINI_MESSAGE.deserialize("<aqua>").append(meta.displayName()).append(MINI_MESSAGE.deserialize("</aqua><dark_gray>(</dark_gray><gray>Verbessert</gray><dark_gray>)</dark_gray>")); //meta.displayName()+"§8 (§7"+color+"Verbessert§8)";
    meta.displayName(displayName);
    this.ITEM.setItemMeta(meta);
    return this;
  }

  public void giveItem(Player player) {
    player.getInventory().addItem(this.ITEM);
  }

  public ComplexItem addAttribute(Attribute attribute, double amount, AttributeModifier.Operation operation, EquipmentSlot slot, @NotNull Plugin plugin) {
    ItemMeta meta = this.ITEM.getItemMeta();
    meta.addAttributeModifier(attribute, new AttributeModifier(new NamespacedKey(plugin, UUID.randomUUID().toString()) , amount, operation, slot.getGroup()));
    this.ITEM.setItemMeta(meta);
    return this;
  }
  public ComplexItem setKey(Complex key, Plugin plugin) {
    ItemMeta meta = this.ITEM.getItemMeta();
    this.COMPLEX = key;
    meta.getPersistentDataContainer().set(new NamespacedKey(plugin, TheZepserAPI.Item.createKey(key)), PersistentDataType.DOUBLE, Math.PI);
    this.ITEM.setItemMeta(meta);
    return this;
  }
  public ComplexItem setAmount(int amount) {
    this.ITEM.setAmount(amount);
    return this;
  }
  public ComplexItem addFlag(ItemFlag flag) {
    ItemMeta meta = this.ITEM.getItemMeta();
    meta.addItemFlags(flag);
    this.ITEM.setItemMeta(meta);
    return this;
  }
  public void drop(Location location) {
    location.getWorld().dropItem(location, this.ITEM);
  }
  public ComplexItem setUnbreakable(boolean unbreakable) {
    ItemMeta meta = this.ITEM.getItemMeta();
    meta.setUnbreakable(unbreakable);
    this.ITEM.setItemMeta(meta);
    return this;
  }
  public ComplexItem addEnchantment(Enchantment enchantment, int level) {
    this.ITEM.addUnsafeEnchantment(enchantment, level);
    return this;
  }
  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

}
