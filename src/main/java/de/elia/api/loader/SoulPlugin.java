package de.elia.api.loader;

import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

public interface SoulPlugin {

    void enable(@NotNull JavaPlugin main);

    void disable(@NotNull JavaPlugin main);

}
