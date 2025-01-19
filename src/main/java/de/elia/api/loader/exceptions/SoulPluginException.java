package de.elia.api.loader.exceptions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SoulPluginException extends Exception {

    public SoulPluginException(String message) {
        super(message);
    }

    public SoulPluginException(String message, JavaPlugin plugin, boolean shouldDisablePlugin){
        super(message);
        if (shouldDisablePlugin){
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }

}
