package de.elia.api;

import de.elia.api.thezepserapi.components.DamageBlocked;
import de.elia.api.thezepserapi.components.FlightBlocked;
import de.elia.api.thezepserapi.spells.spells.gravitation.GRAVITATION_SUPER;
import de.elia.api.thezepserapi.spells.spells.weather.WEATHER_DEFENSE;
import de.elia.api.thezepserapi.spells.spells.weather.WEATHER_SUPER;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

public class Initialize {

    public static void init(@NotNull PluginManager pluginManager, @NotNull Plugin plugin){
        pluginManager.registerEvents(new FlightBlocked(), plugin);
        pluginManager.registerEvents(new DamageBlocked(), plugin);
        pluginManager.registerEvents(new GRAVITATION_SUPER(plugin), plugin);
        pluginManager.registerEvents(new WEATHER_SUPER(), plugin);
        pluginManager.registerEvents(new WEATHER_DEFENSE(), plugin);
    }

}
