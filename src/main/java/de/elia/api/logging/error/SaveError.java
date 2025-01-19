package de.elia.api.logging.error;

import de.elia.api.configuration.SoulConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Save an exception in a file
 * @author Elia
 * @since 2.0.0
 */
public class SaveError {

    /**
     * Save the exception in the file
     * @author Elia
     * @since 2.0.0
     */
    public static void saveError(@NotNull JavaPlugin main, @NotNull Exception exception, String name){
        SoulConfiguration file = new SoulConfiguration(main, "errors/", name + ".txt");
        try {
            PrintWriter writer = new PrintWriter(file.getFile());
            exception.printStackTrace(writer);
            file.save();
            writer.close();
        }catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
