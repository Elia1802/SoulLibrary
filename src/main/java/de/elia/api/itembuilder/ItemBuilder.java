package de.elia.api.itembuilder;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Create a new Item
 * @author Elia
 * @since V. 1.0
 */
public class ItemBuilder {

    private final ItemStack itemStack;

    private final ItemMeta itemMeta;

    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    /**
     * Create a new Item
     * @author Elia
     * @since V.1.0.0
     */
    public ItemBuilder(Material material){
        itemStack = new ItemStack(material);
        itemMeta = itemStack.getItemMeta();
    }

    /**
     * Give the Item a Name
     * @author Elia
     * @since V.1.0.0
     * @param name Requires the Name
     * @return {@link ItemBuilder}
     */
    @NotNull
    public ItemBuilder displayName(Component name){
        itemMeta.displayName(name);
        return this;
    }

    /**
     * Give the Item a lore
     * @author Elia
     * @since V.1.0.0
     * @param lore Requires the lore
     * @return {@link ItemBuilder}
     */
    @NotNull
    public ItemBuilder lore(List<Component> lore){
        itemMeta.lore(lore);
        return this;
    }

    /**
     * Give the Item a lore
     * @author Elia
     * @since V.1.0.0
     * @param lore Requires the lore
     * @return {@link ItemBuilder}
     */
    @NotNull
    public ItemBuilder lore(Component... lore){
        itemMeta.lore(Arrays.asList(lore));
        return this;
    }

    /**
     * Set the Amount of Items
     * @author Elia
     * @since V.1.0.0
     * @param amount Requires the amount of Items
     * @return {@link ItemBuilder}
     */
    @NotNull
    public ItemBuilder amount(int amount){
        itemStack.setAmount(amount);
        return this;
    }

    /**
     * Enchanted the Item
     * @author Elia
     * @since V.1.0.0
     * @param enchantment Requires the Enchantment
     * @param level Requires the Level
     * @return {@link ItemBuilder}
     */
    public ItemBuilder enchant(Enchantment enchantment , int level){
        itemStack.addEnchantment(enchantment , level);
        return this;
    }

    /**
     * Set the Item unbreakable
     * @author Elia
     * @since V.1.0.0
     * @param value Requires a true or false
     * @return {@link ItemBuilder}
     */
    @NotNull
    public ItemBuilder unbreakable(boolean value){
        itemMeta.setUnbreakable(value);
        return this;
    }

    /**
     * set the Item flags
     * @author Elia
     * @since V.1.0.0
     * @param itemFlags Requires ItemFlags
     * @return {@link ItemBuilder}
     */
    @NotNull
    public ItemBuilder itemFlag(ItemFlag... itemFlags){
        itemMeta.addItemFlags(itemFlags);
        return this;
    }

    /**
     * Create the Item
     * @author Elia
     * @since V.1.0.0
     * @return {@link ItemStack}
     */
    @NotNull
    public ItemStack build(){
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
