package de.elia.api;

import de.elia.api.logging.PluginLogger;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import static de.elia.api.PluginInfo.API_NAME;
import static de.elia.api.PluginInfo.API_VERSION;
import static de.elia.api.PluginInfo.AUTHOR;
import static de.elia.api.PluginInfo.NAME;
import static de.elia.api.PluginInfo.VERSION;

public class BootsTrapper implements PluginBootstrap {

    private final PluginLogger pluginLogger = new PluginLogger("Soul-Library");

    @Override
    public void bootstrap(@NotNull BootstrapContext context) {
        pluginLogger.logInfo("Boot " + NAME + "...");
        pluginLogger.logInfo("This is an build of " + NAME + "!");
    }

    @Override
    public @NotNull JavaPlugin createPlugin(@NotNull PluginProviderContext context) {
        pluginLogger.logInfo("Information about this Plugin");
        pluginLogger.logInfo("Name: " + NAME);
        pluginLogger.logInfo("API-Name: " + API_NAME);
        pluginLogger.logInfo("API-Version: " + API_VERSION);
        pluginLogger.logInfo("Version: " + VERSION);
        pluginLogger.logInfo("Authors: " + AUTHOR);
        pluginLogger.logInfo("Booting finished!");
        pluginLogger.logInfo("Load Main!");
        return new Main(pluginLogger);
    }
}
